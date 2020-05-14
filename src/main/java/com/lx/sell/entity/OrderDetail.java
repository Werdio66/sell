package com.lx.sell.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * 订单详情（具体订单中的商品信息）
 *
 * @author Werdio丶
 * @since 2020-05-12 12:39:26
 */
@Data
public class OrderDetail implements Serializable {
    private static final long serialVersionUID = -94344828838062554L;
    
    private String detailId;
    
    private String orderId;
    
    private String productId;
    /**
    * 商品名称
    */
    private String productName;
    /**
    * 当前价格,单位分
    */
    private BigDecimal productPrice;
    /**
    * 数量
    */
    private Integer productQuantity;
    /**
    * 小图
    */
    private String productIcon;
    /**
    * 创建时间
    */
    private LocalDateTime createTime;
    /**
    * 修改时间
    */
    private LocalDateTime updateTime;
}