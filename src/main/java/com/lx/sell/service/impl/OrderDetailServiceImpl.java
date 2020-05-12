package com.lx.sell.service.impl;

import com.lx.sell.entity.OrderDetail;
import com.lx.sell.dao.OrderDetailDao;
import com.lx.sell.service.OrderDetailService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (OrderDetail)表服务实现类
 *
 * @author Werdio丶
 * @since 2020-05-12 12:39:26
 */
@Service("orderDetailService")
public class OrderDetailServiceImpl implements OrderDetailService {
    @Resource
    private OrderDetailDao orderDetailDao;

    /**
     * 通过ID查询单条数据
     *
     * @param detailId 主键
     * @return 实例对象
     */
    @Override
    public OrderDetail queryById(String detailId) {
        return this.orderDetailDao.queryById(detailId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<OrderDetail> queryAllByLimit(int offset, int limit) {
        return this.orderDetailDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param orderDetail 实例对象
     * @return 实例对象
     */
    @Override
    public OrderDetail insert(OrderDetail orderDetail) {
        this.orderDetailDao.insert(orderDetail);
        return orderDetail;
    }

    /**
     * 修改数据
     *
     * @param orderDetail 实例对象
     * @return 实例对象
     */
    @Override
    public OrderDetail update(OrderDetail orderDetail) {
        this.orderDetailDao.update(orderDetail);
        return this.queryById(orderDetail.getDetailId());
    }

    /**
     * 通过主键删除数据
     *
     * @param detailId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String detailId) {
        return this.orderDetailDao.deleteById(detailId) > 0;
    }
}