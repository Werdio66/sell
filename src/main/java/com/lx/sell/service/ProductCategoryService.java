package com.lx.sell.service;

import com.lx.sell.entity.ProductCategory;
import java.util.List;
import java.util.Set;

/**
 * (ProductCategory)表服务接口
 *
 * @author Werdio丶
 * @since 2020-05-12 12:39:26
 */
public interface ProductCategoryService {

    /**
     * 通过ID查询单条数据
     *
     * @param categoryId 主键
     * @return 实例对象
     */
    ProductCategory queryById(Integer categoryId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<ProductCategory> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param productCategory 实例对象
     * @return 实例对象
     */
    ProductCategory insert(ProductCategory productCategory);

    /**
     * 修改数据
     *
     * @param productCategory 实例对象
     * @return 实例对象
     */
    ProductCategory update(ProductCategory productCategory);

    /**
     * 通过主键删除数据
     *
     * @param categoryId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer categoryId);

    /**
     *  查询所有的商品类目信息
     * @param categoryTypeList      类目编号
     * @return  所有的商品类目信息
     */
    List<ProductCategory> findByCategoryTypeIn(Set<Integer> categoryTypeList);
}