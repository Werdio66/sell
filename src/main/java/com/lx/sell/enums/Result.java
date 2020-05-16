package com.lx.sell.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 返回值
 * @author Werdio丶
 * @since 2020-05-13 19:10:09
 */

@Getter
@AllArgsConstructor
public enum Result {

    PRODUCT_NOT_EXIST(11, "商品不存在"),

    PRODUCT_STOCK_ERROR(12, "商品库存不足"),

    ORDER_NOT_EXIST(13, "订单不存在"),

    ORDER_DETAIL_NOT_EXIST(14, "订单详情不存在"),

    ORDER_STATUS_ERROR(15, "订单状态不正确"),

    ORDER_UPDATE_FAIL(16, "订单更新失败"),

    ORDER_PRODUCT_EMPTY(17, "订单中商品为空"),

    ORDER_PAY_UPDATE_FAIL(18, "订单支付更新失败"),

    ORDER_PAY_STATUS_FAIL(19, "订单支付状态不正确"),
    ;

    private final Integer code;

    private final String msg;
}
