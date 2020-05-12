package com.lx.sell.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * (OrderDetail)实体类
 *
 * @author Werdio丶
 * @since 2020-05-12 12:39:26
 */
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
    private Double productPrice;
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


    public String getDetailId() {
        return detailId;
    }

    public void setDetailId(String detailId) {
        this.detailId = detailId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public Integer getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(Integer productQuantity) {
        this.productQuantity = productQuantity;
    }

    public String getProductIcon() {
        return productIcon;
    }

    public void setProductIcon(String productIcon) {
        this.productIcon = productIcon;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

}