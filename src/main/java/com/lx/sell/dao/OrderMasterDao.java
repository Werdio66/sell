package com.lx.sell.dao;

import com.lx.sell.entity.OrderMaster;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (OrderMaster)表数据库访问层
 *
 * @author Werdio丶
 * @since 2020-05-12 12:39:26
 */
public interface OrderMasterDao {

    /**
     * 通过ID查询单条数据
     *
     * @param orderId 主键
     * @return 实例对象
     */
    OrderMaster queryById(String orderId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<OrderMaster> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param orderMaster 实例对象
     * @return 对象列表
     */
    List<OrderMaster> queryAll(OrderMaster orderMaster);

    /**
     * 新增数据
     *
     * @param orderMaster 实例对象
     * @return 影响行数
     */
    int insert(OrderMaster orderMaster);

    /**
     * 修改数据
     *
     * @param orderMaster 实例对象
     * @return 影响行数
     */
    int update(OrderMaster orderMaster);

    /**
     * 通过主键删除数据
     *
     * @param orderId 主键
     * @return 影响行数
     */
    int deleteById(String orderId);

}