package com.lx.sell.service.impl;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.lx.sell.dao.OrderDetailDao;
import com.lx.sell.dao.OrderMasterDao;
import com.lx.sell.dto.CartDTO;
import com.lx.sell.dto.OrderDTO;
import com.lx.sell.entity.OrderDetail;
import com.lx.sell.entity.OrderMaster;
import com.lx.sell.entity.ProductInfo;
import com.lx.sell.enums.OrderStatus;
import com.lx.sell.enums.PayStatus;
import com.lx.sell.enums.ProductStatus;
import com.lx.sell.enums.Result;
import com.lx.sell.exception.SellException;
import com.lx.sell.service.OrderService;
import com.lx.sell.service.ProductInfoService;
import com.lx.sell.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Werdio丶
 * @since 2020-05-13 19:16:41
 */
@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ProductInfoService productInfoService;

    @Resource
    private OrderDetailDao orderDetailDao;

    @Resource
    private OrderMasterDao orderMasterDao;

    @Transactional
    @Override
    public OrderDTO create(OrderDTO orderDTO) {
        BigDecimal allAmount = new BigDecimal(0);
        // 生成买家 id
        String orderId = KeyUtil.getUniqureKey();

        // 1. 查询商品（数量，价格）
        for (OrderDetail orderDetail : orderDTO.getOrderDetailList()) {
            ProductInfo productInfo = productInfoService.queryById(orderDetail.getProductId());
            // 商品不存在
            if (productInfo == null){
                throw new SellException(Result.PRODUCT_NOT_EXIST);
            }

            // 2. 计算总价
            allAmount = allAmount.add(productInfo.getProductPrice().multiply(new BigDecimal(orderDetail.getProductQuantity())));

            // 3. 将订单详情写入数据库
            orderDetail.setDetailId(KeyUtil.getUniqureKey());
            orderDetail.setOrderId(orderId);
            BeanUtils.copyProperties(productInfo, orderDetail);
            orderDetailDao.insert(orderDetail);
        }

        // 4. 所有订单总和入库
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderId(orderId);
        orderMaster.setOrderAmount(allAmount);
        orderMaster.setCreateTime(LocalDateTime.now());
        orderMaster.setUpdateTime(LocalDateTime.now());
        orderMasterDao.insert(orderMaster);

        // 拿到每一个订单
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream()
                .map(orderDetail -> new CartDTO(orderDetail.getProductId(), orderDetail.getProductQuantity()))
                .collect(Collectors.toList());
        // 5. 扣库存
        productInfoService.decreaseStock(cartDTOList);

        return orderDTO;
    }

    @Override
    public OrderDTO findOne(String orderId) {
        // 查询订单信息
        OrderMaster orderMaster = orderMasterDao.queryById(orderId);

        // 订单不存在
        if (orderMaster == null){
            throw new SellException(Result.ORDER_NOT_EXIST);
        }

        // 查询订单详情
        List<OrderDetail> orderDetailList = orderDetailDao.queryByOrderId(orderId);

        if (CollectionUtils.isEmpty(orderDetailList)){
            throw new SellException(Result.ORDER_DETAIL_NOT_EXIST);
        }

        // 封装成 orderDTO 对象
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        orderDTO.setOrderDetailList(orderDetailList);

        return orderDTO;
    }

    @Override
    public PageInfo<OrderDTO> findList(String buyerOpenid) {
        // 查询该买家所有的订单
        List<OrderMaster> orderMasterList = orderMasterDao.queryByBuyerOpenId(buyerOpenid);
        // 封装成 dto 对象
        List<OrderDTO> orderDTOList = new ArrayList<>();

        for (OrderMaster orderMaster : orderMasterList) {
            OrderDTO orderDTO = new OrderDTO();
            BeanUtils.copyProperties(orderMaster, orderDTO);
            orderDTOList.add(orderDTO);
        }
        // 返回分页对象
        return new PageInfo<>(orderDTOList);
    }

    @Transactional
    @Override
    public OrderDTO cancel(OrderDTO orderDTO) {
        // 1. 判断订单状态，只有新订单可以取消
        Integer orderStatus = orderDTO.getOrderStatus();
        if (!orderStatus.equals(OrderStatus.NEW.getCode())) {
            log.error("【取消订单】 订单状态不正确 ：orderId = {}, 0 ：新订单， 1：已完成，2：已取消，当前状态：{}", orderDTO.getOrderId(), orderStatus);
            throw new SellException(Result.ORDER_STATUS_ERROR);
        }
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setUpdateTime(LocalDateTime.now());

        // 2. 修改订单状态
        orderMaster.setOrderStatus(OrderStatus.CANCEL.getCode());
        orderDTO.setOrderStatus(OrderStatus.CANCEL.getCode());
        int row = orderMasterDao.update(orderMaster);
        if (row <= 0){
            log.error("【取消订单】修改订单状态失败 ：orderMaster = {}", orderMaster);
            throw new SellException(Result.ORDER_UPDATE_FAIL);
        }
        // 3. 返还库存
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
            log.error("【取消订单】 订单中没有商品，otderDTO = {}", orderDTO);
            throw new SellException(Result.ORDER_PRODUCT_EMPTY);
        }
        // 获取购物车列表
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream()
                .map(orderDetail -> new CartDTO(orderDetail.getProductId(), orderDetail.getProductQuantity()))
                .collect(Collectors.toList());
        // 加库存
        productInfoService.increaseStock(cartDTOList);
        // 4. 如果已经支付，需要退款
        if (orderDTO.getPayStatus().equals(PayStatus.SUCCESS.getCode())){
            log.info("【取消订单】已经支付");
            // TODO:退款
        }
        return orderDTO;
    }

    @Transactional
    @Override
    public OrderDTO finish(OrderDTO orderDTO) {
        // 1. 判断订单状态，只有新订单可以完结
        Integer orderStatus = orderDTO.getOrderStatus();
        if (!orderStatus.equals(OrderStatus.NEW.getCode())) {
            log.error("【完结订单】 订单状态不正确 ：orderId = {}, 0 ：新订单， 1：已完成，2：已取消，当前状态：{}", orderDTO.getOrderId(), orderStatus);
            throw new SellException(Result.ORDER_STATUS_ERROR);
        }
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setUpdateTime(LocalDateTime.now());

        // 2. 修改订单状态
        orderMaster.setOrderStatus(OrderStatus.FINISHED.getCode());
        orderDTO.setOrderStatus(OrderStatus.FINISHED.getCode());

        int row = orderMasterDao.update(orderMaster);
        if (row <= 0){
            log.error("【完结订单】修改订单状态失败 ：orderMaster = {}", orderMaster);
            throw new SellException(Result.ORDER_UPDATE_FAIL);
        }

        return orderDTO;
    }

    @Transactional
    @Override
    public OrderDTO paid(OrderDTO orderDTO) {
        // 1. 判断订单状态，只有新订单可以支付
        Integer orderStatus = orderDTO.getOrderStatus();
        if (!orderStatus.equals(OrderStatus.NEW.getCode())) {
            log.error("【订单支付】 订单状态不正确 ：orderId = {}, 0 ：新订单， 1：已完成，2：已取消，当前状态：{}", orderDTO.getOrderId(), orderStatus);
            throw new SellException(Result.ORDER_STATUS_ERROR);
        }

        // 判断支付状态
        Integer payStatus = orderDTO.getPayStatus();

        if (!payStatus.equals(PayStatus.WAIT.getCode())){
            log.error("【订单支付】支付状态不正确 orderId = {}，0 ：未支付，1 ： 已支付，当前状态 ：{}", orderDTO.getOrderId(), payStatus);
            throw new SellException(Result.ORDER_PAY_STATUS_FAIL);
        }
        orderDTO.setPayStatus(PayStatus.SUCCESS.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setUpdateTime(LocalDateTime.now());

        // 3. 修改支付状态
        int row = orderMasterDao.update(orderMaster);
        if (row <= 0){
            log.error("【订单支付】修改订单支付状态失败 ：orderMaster = {}", orderMaster);
            throw new SellException(Result.ORDER_PAY_UPDATE_FAIL);
        }

        return orderDTO;
    }
}
