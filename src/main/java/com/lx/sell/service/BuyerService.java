package com.lx.sell.service;

import com.lx.sell.dto.OrderDTO;

/**
 * 买家
 *
 * @author Werdio丶
 * @since 2020-05-17 12:36:53
 */
public interface BuyerService {

    /**
     * 查询单个订单
     *
     * @param openid  微信 openid
     * @param orderId 订单 id
     * @return 查询到的订单
     */
    OrderDTO findOrderOne(String openid, String orderId);

    /**
     * 取消订单
     *
     * @param openid  微信 openid
     * @param orderId 订单 id
     */
    void cancelOrder(String openid, String orderId);

}
