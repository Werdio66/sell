package com.lx.sell.service.impl;

import com.lx.sell.dto.OrderDTO;
import com.lx.sell.service.PayService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Werdio丶
 * @since 2020-05-23 10:28:18
 */
@SpringBootTest
class PayServiceImplTest {

    @Autowired
    private PayService payService;

    @Test
    void create() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId("fe67f80b");
        orderDTO.setBuyerName("啦啦啦");
        orderDTO.setBuyerAddress("买家地址");
        orderDTO.setBuyerPhone("123456789012");
        orderDTO.setBuyerOpenid("11111");
        orderDTO.setOrderAmount(new BigDecimal("0.01"));

        payService.create(orderDTO);
    }
}