package com.lx.sell.converter;

import com.lx.sell.entity.ProductCategory;
import com.lx.sell.form.CategoryForm;
import org.springframework.beans.BeanUtils;

/**
 * @author Werdio丶
 * @since 2020-05-25 14:44:36
 */
public class CategoryForm2ProductCategoryConverter {

    public static ProductCategory converter(CategoryForm categoryForm){
        ProductCategory category = new ProductCategory();
        BeanUtils.copyProperties(categoryForm, category);

        return category;
    }
}
