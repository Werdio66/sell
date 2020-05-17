package com.lx.sell.service.impl;

import com.lx.sell.dto.OrderDTO;
import com.lx.sell.enums.Result;
import com.lx.sell.exception.SellException;
import com.lx.sell.service.BuyerService;
import com.lx.sell.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Werdio丶
 * @since 2020-05-17 12:39:23
 */
@Slf4j
@Service
public class BuyerServiceImpl implements BuyerService {

    @Autowired
    private OrderService orderService;

    @Override
    public OrderDTO findOrderOne(String openid, String orderId) {
       return check(openid, orderId);
    }

    @Override
    public void cancelOrder(String openid, String orderId) {
        OrderDTO orderDTO = check(openid, orderId);
        if (orderDTO == null){
            throw new SellException(Result.ORDER_NOT_EXIST);
        }
    }

    private OrderDTO check(String openid, String orderId){
        OrderDTO orderDTO = orderService.findOne(orderId);
        if (orderDTO == null){
            return null;
        }
        if (!openid.equalsIgnoreCase(orderDTO.getBuyerOpenid())){
            log.error("【查询订单】当前用户没有该权限");
            throw new SellException(Result.ORDER_OWNER_ERROR);
        }
        return orderDTO;
    }
}
