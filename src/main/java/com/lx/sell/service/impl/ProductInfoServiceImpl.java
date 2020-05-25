package com.lx.sell.service.impl;

import com.github.pagehelper.PageInfo;
import com.lx.sell.dto.CartDTO;
import com.lx.sell.entity.ProductInfo;
import com.lx.sell.dao.ProductInfoDao;
import com.lx.sell.enums.ProductStatus;
import com.lx.sell.enums.Result;
import com.lx.sell.exception.SellException;
import com.lx.sell.service.ProductInfoService;
import com.lx.sell.utils.KeyUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
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
        ProductInfo productInfo = productInfoDao.queryById(productId);
        if (productInfo == null){
            throw new SellException(Result.PRODUCT_NOT_EXIST);
        }
        return productInfo;
    }

    /**
     * 查询所有的上架的商品信息
     * @return 对象列表
     */
    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoDao.findUpAll();
    }

    @Override
    public PageInfo<ProductInfo> queryAll() {
        List<ProductInfo> productInfoList = productInfoDao.queryWithType();
        return new PageInfo<>(productInfoList);
    }

    @Override
    public void increaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO : cartDTOList) {
            // 根据商品 id 查出商品
            ProductInfo productInfo = productInfoDao.queryById(cartDTO.getProductId());
            if (productInfo == null){
                throw new SellException(Result.PRODUCT_NOT_EXIST);
            }
            // 将增加后的库存设置回去
            productInfo.setProductStock(productInfo.getProductStock() + cartDTO.getProductQuantity());
            // 更新商品
            productInfoDao.update(productInfo);
        }
    }

    @Override
    public void decreaseStock(List<CartDTO> cartDTOList) {

        for (CartDTO cartDTO : cartDTOList) {
            // 根据商品 id 查出商品
            ProductInfo productInfo = productInfoDao.queryById(cartDTO.getProductId());
            if (productInfo == null){
                throw new SellException(Result.PRODUCT_NOT_EXIST);
            }
            // 计算减后的库存
            int result = productInfo.getProductStock() - cartDTO.getProductQuantity();

            if (result < 0){
                throw new SellException(Result.PRODUCT_STOCK_ERROR.getMsg() + " : " + productInfo.getProductName());
            }
            // 将减后的库存设置回去
            productInfo.setProductStock(result);
            // 更新商品
            productInfoDao.update(productInfo);
        }
    }

    @Override
    public ProductInfo onSave(String productId) {
        ProductInfo productInfo = productInfoDao.queryById(productId);
        if (productInfo == null){
            throw new SellException(Result.PRODUCT_NOT_EXIST);
        }

        if (productInfo.getProductStatusEnum().getCode().equals(ProductStatus.UP.getCode())) {
            throw new SellException("商品已经上架");
        }

        // 设置上架状态
        productInfo.setProductStatus(ProductStatus.UP.getCode());

        int row = productInfoDao.update(productInfo);
        if (row <= 0){
            throw new SellException(Result.PRODUCT_UPDATE_ERROR);
        }
        return productInfo;
    }

    @Override
    public ProductInfo offSave(String productId) {
        ProductInfo productInfo = productInfoDao.queryById(productId);
        if (productInfo == null){
            throw new SellException(Result.PRODUCT_NOT_EXIST);
        }

        if (productInfo.getProductStatusEnum().getCode().equals(ProductStatus.DOWN.getCode())) {
            throw new SellException("商品已经下架");
        }

        // 设置下架状态
        productInfo.setProductStatus(ProductStatus.DOWN.getCode());

        int row = productInfoDao.update(productInfo);
        if (row <= 0){
            throw new SellException(Result.PRODUCT_UPDATE_ERROR);
        }
        return productInfo;
    }

    /**
     * 新增数据
     *
     * @param productInfo 实例对象
     * @return 实例对象
     */
    @Override
    public ProductInfo insert(ProductInfo productInfo) {
        productInfo.setProductId(KeyUtil.getUniqureKey());
        productInfo.setCreateTime(LocalDateTime.now());
        productInfo.setUpdateTime(LocalDateTime.now());
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
        productInfo.setUpdateTime(LocalDateTime.now());
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