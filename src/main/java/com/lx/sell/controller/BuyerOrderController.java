package com.lx.sell.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lx.sell.converter.OrderForm2OrderDTOConverter;
import com.lx.sell.dto.OrderDTO;
import com.lx.sell.enums.Result;
import com.lx.sell.exception.SellException;
import com.lx.sell.form.OrderForm;
import com.lx.sell.service.BuyerService;
import com.lx.sell.service.OrderService;
import com.lx.sell.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

/**
 * 买家订单
 *
 * @author Werdio丶
 * @since 2020-05-16 17:40:03
 */
@Validated
@Slf4j
@RequestMapping("/buyer/order")
@RestController
public class BuyerOrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private BuyerService buyerService;

    /**
     * 创建订单
     */
    @PostMapping("/create")
    public ResultVO<?> create(@Valid OrderForm orderForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("【创建订单】表单参数错误, orderForm = {}", orderForm);
            throw new SellException(Result.PARAM_ERROR.getMsg() + " : " + Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
        }
        log.info("【创建订单】");
        // 将 form 对象转换成 dto
        OrderDTO orderDTO = OrderForm2OrderDTOConverter.converter(orderForm);

        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
            log.error("【创建订单】购物车不能为空");
            throw new SellException(Result.CART_EMPTY);
        }

        OrderDTO result = orderService.create(orderDTO);
        return ResultVO.ok("成功", result.getOrderId());
    }

    /**
     *  用户订单列表
     */
    @GetMapping("/list")
    public ResultVO<?> list(@NotBlank(message = "微信 openid 不能为空") String openid,
                            @RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
                            @RequestParam(name = "size", required = false, defaultValue = "10") Integer size){

        log.info("【用户订单列表】");
        log.info("【用户订单列表】 openid = {}， pageNum = {}， pageSize = {}", openid, page, size);

        PageHelper.startPage(page, size);
        PageInfo<OrderDTO> pageInfo = orderService.findList(openid);
        log.info("【用户订单列表】 ：查询的订单列表 = {}", pageInfo.getList());

        return ResultVO.ok("成功", pageInfo.getList());
    }

    /**
     *  订单详情
     */
    @GetMapping("/detail")
    public ResultVO<?> detail(@NotBlank(message = "微信 openid 不能为空") String openid,
                              @NotBlank(message = "订单 id 不能为空") String orderId){

        log.info("【订单详情】");
        log.info("【订单详情】openid = {}， orderid = {}", openid, orderId);

        OrderDTO orderDTO = buyerService.findOrderOne(openid, orderId);
        return ResultVO.ok("成功", orderDTO);
    }

    /**
     *  取消订单
     */
    @PostMapping("/cancel")
    public ResultVO<?> cancel(@NotBlank(message = "微信 openid 不能为空") String openid,
                              @NotBlank(message = "订单 id 不能为空") String orderId){

        log.info("【取消订单】");
        log.info("【取消订单】openid = {}， orderid = {}", openid, orderId);

        buyerService.cancelOrder(openid, orderId);

        return ResultVO.ok("成功");
    }
}
