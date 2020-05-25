package com.lx.sell.form;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.math.BigDecimal;

/**
 * 表单中的商品信息
 *
 * @author Werdio丶
 * @since 2020-05-25 08:06:29
 */
@Data
public class ProductForm {

    private String productId;
    /**
     * 商品名称
     */
    @NotBlank(message = "商品名称不能为空")
    private String productName;
    /**
     * 单价
     */
    @NotBlank(message = "商品价格不能为空")
    private String productPrice;
    /**
     * 库存
     */
    @Min(value = 0, message = "商品库存不能小于 0")
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
     * 类目编号
     */
    private Integer categoryType;
}
