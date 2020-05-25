package com.lx.sell.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lx.sell.constant.UrlConstant;
import com.lx.sell.dto.OrderDTO;
import com.lx.sell.service.OrderService;
import com.lx.sell.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotEmpty;

/**
 * 卖家订单列表
 *
 * @author Werdio丶
 * @since 2020-05-24 12:27:10
 */
@Validated
@Slf4j
@RequestMapping("/seller/order")
@Controller
public class SellerOrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/list")
    public String list(@RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                       @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize,
                       Model model){
        log.info("【查询订单】pageNum = {}, pageSize = {}", pageNum, pageSize);

        PageHelper.startPage(pageNum, pageSize);
        PageInfo<OrderDTO> pageInfo = orderService.findListAll();
        log.info("【查询订单】pageInfo = {}", JsonUtil.toJson(pageInfo));
        log.info("【查询订单】orderDTO = {}", JsonUtil.toJson(pageInfo.getList()));
        model.addAttribute("orderDTOPage", pageInfo);
        return "/order/list";
    }

    @GetMapping("/detail")
    public String detail(@NotEmpty String orderId, Model model){
        log.info("【查询订单详情】orderId = {}", orderId);

        OrderDTO orderDTO = orderService.findOne(orderId);
        log.info("【查询订单详情】orderDTO = {}", orderDTO);

        model.addAttribute("orderDTO", orderDTO);
        return "/order/detail";
    }

    @GetMapping("/finish")
    public String finish(@NotEmpty String orderId, Model model){
        log.info("【完结订单】orderId = {}", orderId);

        OrderDTO finish = orderService.finish(orderService.findOne(orderId));
        log.info("【完结订单】finishOrder = {}", finish);

        Integer pageNum = 2;

        model.addAttribute("url", "/sell/seller/order/list?pageNum=" + pageNum);

        return "/common/success";
    }

    @GetMapping("/cancel")
    public String cancel(@NotEmpty String orderId, Model model){
        log.info("【取消订单】orderId = {}", orderId);

        OrderDTO cancel = orderService.cancel(orderService.findOne(orderId));
        log.info("【取消订单】finishOrder = {}", cancel);

        Integer pageNum = 2;

        model.addAttribute("url", "/sell/seller/order/list?pageNum=" + pageNum);

        return "/common/success";
    }
}
