package com.lx.sell.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lx.sell.enums.ProductStatus;
import com.lx.sell.utils.EnumUtil;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * 商品信息
 *
 * @author Werdio丶
 * @since 2020-05-12 12:39:26
 */
@Data
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
    private BigDecimal productPrice;
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
     * 商品状态,
     * 0 正常
     * 1 下架
     */
    private Integer productStatus = ProductStatus.UP.getCode();
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

    @JsonIgnore
    public ProductStatus getProductStatusEnum(){
        return EnumUtil.getMsgByCode(productStatus, ProductStatus.class);
    }
}