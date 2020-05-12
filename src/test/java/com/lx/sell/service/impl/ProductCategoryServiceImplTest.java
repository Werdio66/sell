package com.lx.sell.service.impl;

import com.lx.sell.entity.ProductCategory;
import com.lx.sell.service.ProductCategoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductCategoryServiceImplTest {

    @Autowired
    private ProductCategoryService productCategoryService;

    @Test
    void queryById() {
        ProductCategory productCategory = productCategoryService.queryById(1);
        System.out.println(productCategory);
    }

    @Test
    void queryAllByLimit() {
    }

    @Test
    void insert() {
        ProductCategory productCategory = productCategoryService.queryById(1);
        productCategoryService.insert(productCategory);
    }

    @Test
    void update() {
    }

    @Test
    void deleteById() {
    }
}