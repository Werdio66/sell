package com.lx.sell.controller;

import com.lx.sell.dto.OrderDTO;
import com.lx.sell.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 微信支付
 * @author Werdio丶
 * @since 2020-05-23 09:41:28
 */
@Slf4j
@RequestMapping("/pay")
@Controller
public class PayController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/create")
    public String cteate(String orderId, String returnUrl){
        log.info("【微信支付】");
        log.info("【微信支付】orderId = {}, returnUrl = {}", orderId, returnUrl);

        OrderDTO orderDTO = orderService.findOne(orderId);



        return "";
    }
}
