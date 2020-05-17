package com.lx.sell.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 前台传递的订单表单数据
 *
 * @author Werdio丶
 * @since 2020-05-16 17:43:41
 */
@Data
public class OrderForm {
    /**
     * 买家姓名
     */
    @NotBlank(message = "买家姓名不能为空")
    private String name;

    /**
     * 买家电话
     */
    @NotBlank(message = "买家电话不能为空")
    private String phone;

    /**
     * 地址
     */
    @NotBlank(message = "买家地址不能为空")
    private String address;

    /**
     * 微信 openid
     */
    @NotBlank(message = "买家微信 openid 不能为空")
    private String openid;

    /**
     *  购物车
     */
    @NotBlank(message = "买家购物车不能为空")
    private String items;
}
