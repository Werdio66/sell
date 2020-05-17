package com.lx.sell.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lx.sell.dto.OrderDTO;
import com.lx.sell.entity.OrderDetail;
import com.lx.sell.enums.Result;
import com.lx.sell.exception.SellException;
import com.lx.sell.form.OrderForm;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * 将 orderForm 对象转换为 orderDTO 对象
 *
 * @author Werdio丶
 * @since 2020-05-17 07:45:11
 */
@Slf4j
public class OrderForm2OrderDTOConverter {

    public static OrderDTO converter(OrderForm orderForm){
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());

        Gson gson = new Gson();
        List<OrderDetail> orderDetailList;
        try {
            orderDetailList = gson.fromJson(orderForm.getItems(), new TypeToken<List<OrderDetail>>() {
            }.getType());
        }catch (Exception e){
            log.error("【对象转换】错误, json = {}", orderForm.getItems());
            throw new SellException(Result.PARAM_ERROR);
        }
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }
}
