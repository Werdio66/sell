package com.lx.sell.service.impl;

import com.lx.sell.entity.ProductCategory;
import com.lx.sell.dao.ProductCategoryDao;
import com.lx.sell.service.ProductCategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

/**
 * (ProductCategory)表服务实现类
 *
 * @author Werdio丶
 * @since 2020-05-12 12:39:26
 */
@Service("productCategoryService")
public class ProductCategoryServiceImpl implements ProductCategoryService {
    @Resource
    private ProductCategoryDao productCategoryDao;

    /**
     * 通过ID查询单条数据
     *
     * @param categoryId 主键
     * @return 实例对象
     */
    @Override
    public ProductCategory queryById(Integer categoryId) {
        return this.productCategoryDao.queryById(categoryId);
    }

    /**
     * 查询多条数据
     *
     * @return 对象列表
     */
    @Override
    public List<ProductCategory> queryAll() {
        return this.productCategoryDao.queryAll(null);
    }

    /**
     * 新增数据
     *
     * @param productCategory 实例对象
     * @return 实例对象
     */
    @Override
    public ProductCategory insert(ProductCategory productCategory) {
        productCategory.setCreateTime(LocalDateTime.now());
        productCategory.setUpdateTime(LocalDateTime.now());
        this.productCategoryDao.insert(productCategory);
        return productCategory;
    }

    /**
     * 修改数据
     *
     * @param productCategory 实例对象
     * @return 实例对象
     */
    @Override
    public ProductCategory update(ProductCategory productCategory) {
        productCategory.setUpdateTime(LocalDateTime.now());
        this.productCategoryDao.update(productCategory);
        return this.queryById(productCategory.getCategoryId());
    }

    /**
     * 通过主键删除数据
     *
     * @param categoryId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer categoryId) {
        return this.productCategoryDao.deleteById(categoryId) > 0;
    }

    @Override
    public List<ProductCategory> findByCategoryTypeIn(Set<Integer> categoryTypeList) {
        return productCategoryDao.findByCategoryTypeIn(categoryTypeList);
    }
}