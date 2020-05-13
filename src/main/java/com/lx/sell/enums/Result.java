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

    PRODUCT_NOT_EXIST(11, "商品不存在")
    ;

    private final Integer code;

    private final String msg;
}
