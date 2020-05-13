package com.lx.sell.service;

import com.github.pagehelper.PageInfo;
import com.lx.sell.dto.OrderDTO;

/**
 * 订单
 *
 * @author Werdio丶
 * @since 2020-05-13 17:47:47
 */
public interface OrderService {

    /**
     *  创建订单
     * @param orderDTO      订单信息
     * @return              创建好的订单
     */
    OrderDTO create(OrderDTO orderDTO);

    /**
     * 查询单个订单
     * @param orderId       订单 id
     * @return              订单
     */
    OrderDTO findOne(String orderId);

    /**
     * 查询订单列表
     * @param buyerOpenid       买家 openid
     * @return                  所有订单
     */
    PageInfo<OrderDTO> findList(String buyerOpenid);

    /**
     * 取消订单
     * @param orderDTO
     * @return
     */
    OrderDTO cancel(OrderDTO orderDTO);

    /**
     * 完结订单
     * @param orderDTO
     * @return
     */
    OrderDTO finish(OrderDTO orderDTO);

    /**
     * 支付订单
     * @param orderDTO
     * @return
     */
    OrderDTO paid(OrderDTO orderDTO);

    /**
     *  查询订单列表
     * @return
     */
    PageInfo<OrderDTO> findList();
}
