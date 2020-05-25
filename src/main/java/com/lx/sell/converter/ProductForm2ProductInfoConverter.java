package com.lx.sell.converter;

import com.lx.sell.entity.ProductInfo;
import com.lx.sell.enums.ProductStatus;
import com.lx.sell.form.ProductForm;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;

/**
 * @author Werdio丶
 * @since 2020-05-25 08:22:06
 */
public class ProductForm2ProductInfoConverter {

    public static ProductInfo converter(ProductForm productForm){
        ProductInfo productInfo = new ProductInfo();
        BeanUtils.copyProperties(productForm, productInfo);
        productInfo.setProductPrice(new BigDecimal(productForm.getProductPrice()));
        productInfo.setProductStatus(ProductStatus.UP.getCode());
        return productInfo;
    }
}
