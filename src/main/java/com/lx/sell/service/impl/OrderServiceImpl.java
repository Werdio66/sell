package com.lx.sell.service.impl;

import com.github.pagehelper.PageInfo;
import com.lx.sell.dto.OrderDTO;
import com.lx.sell.entity.OrderDetail;
import com.lx.sell.entity.ProductInfo;
import com.lx.sell.enums.Result;
import com.lx.sell.exception.SellException;
import com.lx.sell.service.OrderService;
import com.lx.sell.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author Werdio丶
 * @since 2020-05-13 19:16:41
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ProductInfoService productInfoService;

    @Override
    public OrderDTO create(OrderDTO orderDTO) {
        BigDecimal allAmouant = new BigDecimal(0);

        // 1. 查询商品（数量，价格）
        for (OrderDetail orderDetail : orderDTO.getOrderDetailList()) {
            ProductInfo productInfo = productInfoService.queryById(orderDetail.getProductId());
            // 商品不存在
            if (productInfo == null){
                throw new SellException(Result.PRODUCT_NOT_EXIST);
            }
            // 2. 计算总价
            allAmouant = allAmouant.add(orderDetail.getProductPrice().multiply(new BigDecimal(orderDetail.getProductQuantity())));
        }
        // 3. 将订单写入数据库
        // 4. 扣库存
        return null;
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
