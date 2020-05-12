package com.lx.sell.service.impl;

import com.lx.sell.entity.OrderMaster;
import com.lx.sell.dao.OrderMasterDao;
import com.lx.sell.service.OrderMasterService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (OrderMaster)表服务实现类
 *
 * @author Werdio丶
 * @since 2020-05-12 12:39:26
 */
@Service("orderMasterService")
public class OrderMasterServiceImpl implements OrderMasterService {
    @Resource
    private OrderMasterDao orderMasterDao;

    /**
     * 通过ID查询单条数据
     *
     * @param orderId 主键
     * @return 实例对象
     */
    @Override
    public OrderMaster queryById(String orderId) {
        return this.orderMasterDao.queryById(orderId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<OrderMaster> queryAllByLimit(int offset, int limit) {
        return this.orderMasterDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param orderMaster 实例对象
     * @return 实例对象
     */
    @Override
    public OrderMaster insert(OrderMaster orderMaster) {
        this.orderMasterDao.insert(orderMaster);
        return orderMaster;
    }

    /**
     * 修改数据
     *
     * @param orderMaster 实例对象
     * @return 实例对象
     */
    @Override
    public OrderMaster update(OrderMaster orderMaster) {
        this.orderMasterDao.update(orderMaster);
        return this.queryById(orderMaster.getOrderId());
    }

    /**
     * 通过主键删除数据
     *
     * @param orderId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String orderId) {
        return this.orderMasterDao.deleteById(orderId) > 0;
    }
}