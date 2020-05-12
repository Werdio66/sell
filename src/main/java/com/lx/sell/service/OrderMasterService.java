package com.lx.sell.service;

import com.lx.sell.entity.OrderMaster;
import java.util.List;

/**
 * (OrderMaster)表服务接口
 *
 * @author Werdio丶
 * @since 2020-05-12 12:39:26
 */
public interface OrderMasterService {

    /**
     * 通过ID查询单条数据
     *
     * @param orderId 主键
     * @return 实例对象
     */
    OrderMaster queryById(String orderId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<OrderMaster> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param orderMaster 实例对象
     * @return 实例对象
     */
    OrderMaster insert(OrderMaster orderMaster);

    /**
     * 修改数据
     *
     * @param orderMaster 实例对象
     * @return 实例对象
     */
    OrderMaster update(OrderMaster orderMaster);

    /**
     * 通过主键删除数据
     *
     * @param orderId 主键
     * @return 是否成功
     */
    boolean deleteById(String orderId);

}