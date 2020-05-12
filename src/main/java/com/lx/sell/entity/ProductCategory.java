package com.lx.sell.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * (ProductCategory)实体类
 *
 * @author Werdio丶
 * @since 2020-05-12 12:39:26
 */
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


    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
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