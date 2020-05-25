package com.lx.sell.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 商品状态
 */
@AllArgsConstructor
@Getter
public enum ProductStatus implements CodeEnum {

    UP(0, "上架"),
    DOWN(1, "下架")
    ;

    private final Integer code;

    private final String message;
}
