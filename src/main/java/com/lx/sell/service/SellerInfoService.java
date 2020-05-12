package com.lx.sell.service;

import com.lx.sell.entity.SellerInfo;
import java.util.List;

/**
 * 卖家信息表(SellerInfo)表服务接口
 *
 * @author Werdio丶
 * @since 2020-05-12 12:39:26
 */
public interface SellerInfoService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SellerInfo queryById(String id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<SellerInfo> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param sellerInfo 实例对象
     * @return 实例对象
     */
    SellerInfo insert(SellerInfo sellerInfo);

    /**
     * 修改数据
     *
     * @param sellerInfo 实例对象
     * @return 实例对象
     */
    SellerInfo update(SellerInfo sellerInfo);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

}