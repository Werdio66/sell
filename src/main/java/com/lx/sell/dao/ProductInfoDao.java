package com.lx.sell.dao;

import com.lx.sell.entity.ProductInfo;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (ProductInfo)表数据库访问层
 *
 * @author Werdio丶
 * @since 2020-05-12 12:39:26
 */
public interface ProductInfoDao {

    /**
     * 通过ID查询单条数据
     *
     * @param productId 主键
     * @return 实例对象
     */
    ProductInfo queryById(String productId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<ProductInfo> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param productInfo 实例对象
     * @return 对象列表
     */
    List<ProductInfo> queryAll(ProductInfo productInfo);

    /**
     * 新增数据
     *
     * @param productInfo 实例对象
     * @return 影响行数
     */
    int insert(ProductInfo productInfo);

    /**
     * 修改数据
     *
     * @param productInfo 实例对象
     * @return 影响行数
     */
    int update(ProductInfo productInfo);

    /**
     * 通过主键删除数据
     *
     * @param productId 主键
     * @return 影响行数
     */
    int deleteById(String productId);

    /**
     * 查询所有上架的商品
     * @return      所有上架的商品
     */
    List<ProductInfo> findUpAll();
}