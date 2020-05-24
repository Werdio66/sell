package com.lx.sell.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lx.sell.dto.OrderDTO;
import com.lx.sell.entity.OrderDetail;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Werdio丶
 * @since 2020-05-14 15:02:28
 */
@SpringBootTest
class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Test
    void create() {
        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setBuyerName("wd");
        orderDTO.setBuyerAddress("买家地址");
        orderDTO.setBuyerPhone("123456789012");
        orderDTO.setBuyerOpenid("11111");

        //购物车
        List<OrderDetail> orderDetailList = new ArrayList<>();
        OrderDetail o1 = new OrderDetail();
        o1.setProductId("1");
        o1.setProductQuantity(1);

        OrderDetail o2 = new OrderDetail();
        o2.setProductId("2");
        o2.setProductQuantity(1);

        orderDetailList.add(o1);
        orderDetailList.add(o2);

        orderDTO.setOrderDetailList(orderDetailList);

        OrderDTO dto = orderService.create(orderDTO);
        System.out.println(dto);
    }

    @Test
    void findOne() {
        OrderDTO orderDTO = orderService.findOne("fe67f80b");
        System.out.println(orderDTO);
    }

    @Test
    void findList() {
        PageHelper.startPage(1, 1);
        PageInfo<OrderDTO> pageInfo = orderService.findList("11111");
        System.out.println(pageInfo);
        System.out.println(pageInfo.getList());
        System.out.println(pageInfo.getList().size());
    }

    @Test
    void cancel() {
        OrderDTO orderDTO = orderService.findOne("fe67f80b");
        OrderDTO cancel = orderService.cancel(orderDTO);
        System.out.println(cancel);
    }

    @Test
    void finish() {
        OrderDTO orderDTO = orderService.findOne("fe67f80b");
        OrderDTO cancel = orderService.finish(orderDTO);
        System.out.println(cancel);
    }

    @Test
    void paid() {
        OrderDTO orderDTO = orderService.findOne("fe67f80b");
        OrderDTO cancel = orderService.paid(orderDTO);
        System.out.println(cancel);
    }

    @Test
    void findListAll(){
        PageHelper.startPage(5, 5);
        PageInfo<OrderDTO> pageInfo = orderService.findListAll();
        System.out.println("是否为首页 ：" + pageInfo.isIsFirstPage());
        System.out.println("是否为尾页 ：" + pageInfo.isIsLastPage());
        System.out.println("是否有上一页 ：" + pageInfo.isHasPreviousPage());
        System.out.println("是否有下一页 ：" + pageInfo.isHasNextPage());
        System.out.println(pageInfo);
    }
}