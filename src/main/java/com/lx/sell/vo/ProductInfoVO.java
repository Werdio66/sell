package com.lx.sell.vo;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ProductInfoVO {

    private String id;

    /**
     *  商品名称
     */
    private String name;

    /**
     * 商品价格
     */
    private BigDecimal price;

    /**
     * 描述信息
     */
    private String description;

    /**
     * 图标
     */
    private String icon;
}
