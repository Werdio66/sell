package com.lx.sell.service;

import com.lx.sell.entity.OrderDetail;
import java.util.List;

/**
 * (OrderDetail)表服务接口
 *
 * @author Werdio丶
 * @since 2020-05-12 12:39:26
 */
public interface OrderDetailService {

    /**
     * 通过ID查询单条数据
     *
     * @param detailId 主键
     * @return 实例对象
     */
    OrderDetail queryById(String detailId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<OrderDetail> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param orderDetail 实例对象
     * @return 实例对象
     */
    OrderDetail insert(OrderDetail orderDetail);

    /**
     * 修改数据
     *
     * @param orderDetail 实例对象
     * @return 实例对象
     */
    OrderDetail update(OrderDetail orderDetail);

    /**
     * 通过主键删除数据
     *
     * @param detailId 主键
     * @return 是否成功
     */
    boolean deleteById(String detailId);

}