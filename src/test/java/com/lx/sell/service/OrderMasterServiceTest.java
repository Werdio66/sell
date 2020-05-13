package com.lx.sell.service;

import com.github.pagehelper.PageInfo;
import com.lx.sell.entity.OrderMaster;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Werdio丶
 * @since 2020-05-13 13:03:30
 */
@SpringBootTest
class OrderMasterServiceTest {

    @Autowired
    private OrderMasterService orderMasterService;

    @Test
    void queryByBuyerOpenId() {
        PageInfo<OrderMaster> pageInfo = orderMasterService.queryByBuyerOpenId("11111");
        List<OrderMaster> orderMasterList = pageInfo.getList();
        orderMasterList.forEach(System.out::println);
    }
}