package com.lx.sell.dao;

import com.lx.sell.entity.ProductCategory;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * (ProductCategory)表数据库访问层
 *
 * @author Werdio丶
 * @since 2020-05-12 12:39:26
 */
public interface ProductCategoryDao {

    /**
     * 通过ID查询单条数据
     *
     * @param categoryId 主键
     * @return 实例对象
     */
    ProductCategory queryById(Integer categoryId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<ProductCategory> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param productCategory 实例对象
     * @return 对象列表
     */
    List<ProductCategory> queryAll(ProductCategory productCategory);

    /**
     * 新增数据
     *
     * @param productCategory 实例对象
     * @return 影响行数
     */
    int insert(ProductCategory productCategory);

    /**
     * 修改数据
     *
     * @param productCategory 实例对象
     * @return 影响行数
     */
    int update(ProductCategory productCategory);

    /**
     * 通过主键删除数据
     *
     * @param categoryId 主键
     * @return 影响行数
     */
    int deleteById(Integer categoryId);

}