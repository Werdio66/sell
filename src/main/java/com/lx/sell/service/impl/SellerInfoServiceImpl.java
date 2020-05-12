package com.lx.sell.service.impl;

import com.lx.sell.entity.SellerInfo;
import com.lx.sell.dao.SellerInfoDao;
import com.lx.sell.service.SellerInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 卖家信息表(SellerInfo)表服务实现类
 *
 * @author Werdio丶
 * @since 2020-05-12 12:39:26
 */
@Service("sellerInfoService")
public class SellerInfoServiceImpl implements SellerInfoService {
    @Resource
    private SellerInfoDao sellerInfoDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SellerInfo queryById(String id) {
        return this.sellerInfoDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<SellerInfo> queryAllByLimit(int offset, int limit) {
        return this.sellerInfoDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param sellerInfo 实例对象
     * @return 实例对象
     */
    @Override
    public SellerInfo insert(SellerInfo sellerInfo) {
        this.sellerInfoDao.insert(sellerInfo);
        return sellerInfo;
    }

    /**
     * 修改数据
     *
     * @param sellerInfo 实例对象
     * @return 实例对象
     */
    @Override
    public SellerInfo update(SellerInfo sellerInfo) {
        this.sellerInfoDao.update(sellerInfo);
        return this.queryById(sellerInfo.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.sellerInfoDao.deleteById(id) > 0;
    }
}