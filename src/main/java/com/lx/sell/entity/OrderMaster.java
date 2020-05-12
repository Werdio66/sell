package com.lx.sell.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * (OrderMaster)实体类
 *
 * @author Werdio丶
 * @since 2020-05-12 12:39:26
 */
public class OrderMaster implements Serializable {
    private static final long serialVersionUID = -36779549667824587L;
    
    private String orderId;
    /**
    * 买家名字
    */
    private String buyerName;
    /**
    * 买家电话
    */
    private String buyerPhone;
    /**
    * 买家地址
    */
    private String buyerAddress;
    /**
    * 买家微信openid
    */
    private String buyerOpenid;
    /**
    * 订单总金额
    */
    private Double orderAmount;
    /**
    * 订单状态, 默认为新下单
    */
    private Object orderStatus;
    /**
    * 支付状态, 默认未支付
    */
    private Object payStatus;
    /**
    * 创建时间
    */
    private LocalDateTime createTime;
    /**
    * 修改时间
    */
    private LocalDateTime updateTime;


    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getBuyerPhone() {
        return buyerPhone;
    }

    public void setBuyerPhone(String buyerPhone) {
        this.buyerPhone = buyerPhone;
    }

    public String getBuyerAddress() {
        return buyerAddress;
    }

    public void setBuyerAddress(String buyerAddress) {
        this.buyerAddress = buyerAddress;
    }

    public String getBuyerOpenid() {
        return buyerOpenid;
    }

    public void setBuyerOpenid(String buyerOpenid) {
        this.buyerOpenid = buyerOpenid;
    }

    public Double getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(Double orderAmount) {
        this.orderAmount = orderAmount;
    }

    public Object getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Object orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Object getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Object payStatus) {
        this.payStatus = payStatus;
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