package com.lx.sell.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 产品订购状态
 */
@AllArgsConstructor
@Getter
public enum OrderStatus implements CodeEnum {

    NEW(0, "新订单"),
    FINISHED(1, "完成"),
    CANCEL(2, "取消");

    private final Integer code;

    private final String message;
}
