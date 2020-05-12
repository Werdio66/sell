package com.lx.sell.service.impl;

import com.lx.sell.entity.ProductInfo;
import com.lx.sell.dao.ProductInfoDao;
import com.lx.sell.service.ProductInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (ProductInfo)表服务实现类
 *
 * @author Werdio丶
 * @since 2020-05-12 12:39:26
 */
@Service("productInfoService")
public class ProductInfoServiceImpl implements ProductInfoService {
    @Resource
    private ProductInfoDao productInfoDao;

    /**
     * 通过ID查询单条数据
     *
     * @param productId 主键
     * @return 实例对象
     */
    @Override
    public ProductInfo queryById(String productId) {
        return this.productInfoDao.queryById(productId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<ProductInfo> queryAllByLimit(int offset, int limit) {
        return this.productInfoDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param productInfo 实例对象
     * @return 实例对象
     */
    @Override
    public ProductInfo insert(ProductInfo productInfo) {
        this.productInfoDao.insert(productInfo);
        return productInfo;
    }

    /**
     * 修改数据
     *
     * @param productInfo 实例对象
     * @return 实例对象
     */
    @Override
    public ProductInfo update(ProductInfo productInfo) {
        this.productInfoDao.update(productInfo);
        return this.queryById(productInfo.getProductId());
    }

    /**
     * 通过主键删除数据
     *
     * @param productId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String productId) {
        return this.productInfoDao.deleteById(productId) > 0;
    }
}