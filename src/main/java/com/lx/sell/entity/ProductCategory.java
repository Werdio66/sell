package com.lx.sell.entity;

import lombok.Data;

import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * 商品类目信息
 *
 * @author Werdio丶
 * @since 2020-05-12 12:39:26
 */
@Data
public class ProductCategory implements Serializable {
    private static final long serialVersionUID = -85436894658051504L;
    
    private Integer categoryId;
    /**
    * 类目名字
    */
    private String categoryName;
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
}