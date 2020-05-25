package com.lx.sell.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author Werdio丶
 * @since 2020-05-25 14:40:56
 */
@Data
public class CategoryForm {

    private Integer categoryId;
    /**
     * 类目名字
     */
    @NotBlank(message = "类目名称不能为空")
    private String categoryName;
    /**
     * 类目编号
     */
    @NotNull(message = "类目编号不能为空")
    private Integer categoryType;

}
