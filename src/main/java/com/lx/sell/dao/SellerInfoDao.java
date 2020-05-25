package com.lx.sell.dao;

import com.lx.sell.entity.SellerInfo;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 卖家信息表(SellerInfo)表数据库访问层
 *
 * @author Werdio丶
 * @since 2020-05-12 12:39:26
 */
public interface SellerInfoDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SellerInfo queryById(String id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<SellerInfo> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param sellerInfo 实例对象
     * @return 对象列表
     */
    List<SellerInfo> queryAll(SellerInfo sellerInfo);

    /**
     * 新增数据
     *
     * @param sellerInfo 实例对象
     * @return 影响行数
     */
    int insert(SellerInfo sellerInfo);

    /**
     * 修改数据
     *
     * @param sellerInfo 实例对象
     * @return 影响行数
     */
    int update(SellerInfo sellerInfo);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);

    /**
     *  通过 openid 查询卖家信息
     */
    SellerInfo findByOpenid(String openid);
}