package com.lx.sell.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 购物车
 *
 * @author Werdio丶
 * @since 2020-05-14 14:30:09
 */
@Data
@AllArgsConstructor
public class CartDTO {

    /**
     *  商品 id
     */
    private String productId;

    /**
     *  购买数量
     */
    private Integer productQuantity;
}
