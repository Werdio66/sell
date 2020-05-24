package com.lx.sell.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 支付状态
 */
@AllArgsConstructor
@Getter
public enum PayStatus implements CodeEnum {

    WAIT(0, "未支付"),
    SUCCESS(1, "支付完成");

    private final Integer code;

    private final String message;
}
