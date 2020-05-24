package com.lx.sell.service;

import com.lx.sell.dto.OrderDTO;

/**
 * @author Werdio丶
 * @since 2020-05-23 10:17:52
 */
public interface PayService {

    /**
     *  创建支付订单
     * @param orderDTO
     */
    void create(OrderDTO orderDTO);
}
