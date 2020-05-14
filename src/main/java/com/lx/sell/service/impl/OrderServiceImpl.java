package com.lx.sell.service.impl;

import com.github.pagehelper.PageInfo;
import com.lx.sell.dao.OrderDetailDao;
import com.lx.sell.dao.OrderMasterDao;
import com.lx.sell.dto.CartDTO;
import com.lx.sell.dto.OrderDTO;
import com.lx.sell.entity.OrderDetail;
import com.lx.sell.entity.OrderMaster;
import com.lx.sell.entity.ProductInfo;
import com.lx.sell.enums.Result;
import com.lx.sell.exception.SellException;
import com.lx.sell.service.OrderService;
import com.lx.sell.service.ProductInfoService;
import com.lx.sell.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Werdio丶
 * @since 2020-05-13 19:16:41
 */
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
        return null;
    }

    @Override
    public PageInfo<OrderDTO> findList(String buyerOpenid) {
        return null;
    }

    @Override
    public OrderDTO cancel(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO finish(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO paid(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public PageInfo<OrderDTO> findList() {
        return null;
    }
}
