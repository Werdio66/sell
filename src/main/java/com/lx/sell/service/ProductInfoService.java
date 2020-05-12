package com.lx.sell.service;

import com.lx.sell.entity.ProductInfo;
import java.util.List;

/**
 * (ProductInfo)表服务接口
 *
 * @author Werdio丶
 * @since 2020-05-12 12:39:26
 */
public interface ProductInfoService {

    /**
     * 通过ID查询单条数据
     *
     * @param productId 主键
     * @return 实例对象
     */
    ProductInfo queryById(String productId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<ProductInfo> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param productInfo 实例对象
     * @return 实例对象
     */
    ProductInfo insert(ProductInfo productInfo);

    /**
     * 修改数据
     *
     * @param productInfo 实例对象
     * @return 实例对象
     */
    ProductInfo update(ProductInfo productInfo);

    /**
     * 通过主键删除数据
     *
     * @param productId 主键
     * @return 是否成功
     */
    boolean deleteById(String productId);

}