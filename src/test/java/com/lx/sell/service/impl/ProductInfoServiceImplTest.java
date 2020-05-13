package com.lx.sell.service.impl;

import com.lx.sell.service.ProductInfoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductInfoServiceImplTest {

    @Autowired
    private ProductInfoService productInfoService;

    @Test
    void findUpAll() {
        productInfoService.findUpAll().forEach(System.out::println);
    }
}