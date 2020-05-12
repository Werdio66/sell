package com.lx.sell.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * (ProductInfo)实体类
 *
 * @author Werdio丶
 * @since 2020-05-12 12:39:26
 */
public class ProductInfo implements Serializable {
    private static final long serialVersionUID = -63259813941770776L;
    
    private String productId;
    /**
    * 商品名称
    */
    private String productName;
    /**
    * 单价
    */
    private Double productPrice;
    /**
    * 库存
    */
    private Integer productStock;
    /**
    * 描述
    */
    private String productDescription;
    /**
    * 小图
    */
    private String productIcon;
    /**
    * 商品状态,0正常1下架
    */
    private Object productStatus;
    /**
    * 类目编号
    */
    private Integer categoryType;
    /**
    * 创建时间
    */
    private LocalDateTime createTime;
    /**
    * 修改时间
    */
    private LocalDateTime updateTime;


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

    public Integer getProductStock() {
        return productStock;
    }

    public void setProductStock(Integer productStock) {
        this.productStock = productStock;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductIcon() {
        return productIcon;
    }

    public void setProductIcon(String productIcon) {
        this.productIcon = productIcon;
    }

    public Object getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(Object productStatus) {
        this.productStatus = productStatus;
    }

    public Integer getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(Integer categoryType) {
        this.categoryType = categoryType;
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