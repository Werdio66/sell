package com.lx.sell.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * 返回给前端的产品对象
 */
@Data
public class ProductVO {

    /**
     * 产品类别名称
     */
    @JsonProperty("name")
    private String categoryName;

    /**
     * 分类的类型
     */
    @JsonProperty("type")
    private Integer categoryType;

    /**
     * 每一个分类的商品信息
     */
    @JsonProperty("foods")
    private List<ProductInfoVO> foods;
}
