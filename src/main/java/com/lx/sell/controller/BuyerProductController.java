package com.lx.sell.controller;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.lx.sell.entity.ProductCategory;
import com.lx.sell.entity.ProductInfo;
import com.lx.sell.service.ProductCategoryService;
import com.lx.sell.service.ProductInfoService;
import com.lx.sell.vo.ProductInfoVO;
import com.lx.sell.vo.ProductVO;
import com.lx.sell.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 买家商品
 *
 * @author Werdio丶
 * @since 2020-05-12 13:33:53
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {
    /**
     * 服务对象
     */
    @Resource
    private ProductInfoService productInfoService;

    @Autowired
    private ProductCategoryService productCategoryService;

    /**
     *  查询所有的商品
     */
    @GetMapping("/list")
    public ResultVO<?> list(){

        // 查询所有已经上架的商品信息
        List<ProductInfo> productInfoList = productInfoService.findUpAll();
        // 获取所有的商品类目编号
        Set<Integer> categoryTypeList = productInfoList.stream()
                .map(ProductInfo::getCategoryType)
                .collect(Collectors.toSet());

        // 查询所有的商品类目信息
        List<ProductCategory> productCategoryList = productCategoryService.findByCategoryTypeIn(categoryTypeList);

        // key 是类目编号，value 是 所有的商品信息集合
        Multimap<Integer, ProductInfoVO> multimap = ArrayListMultimap.create();

        // 将每一个类目的商品存放在一个 list 中
        for (ProductInfo productInfo : productInfoList) {
            ProductInfoVO productInfoVO = ProductInfoVO.builder()
                    .id(productInfo.getProductId())
                    .name(productInfo.getProductName())
                    .price(productInfo.getProductPrice())
                    .description(productInfo.getProductDescription())
                    .icon(productInfo.getProductIcon())
                    .build();
            multimap.put(productInfo.getCategoryType(), productInfoVO);
        }
        // 商品分类和商品具体信息
        List<ProductVO> productVOList = new ArrayList<>();

        // 组合
        for (ProductCategory productCategory : productCategoryList) {
            // 创建商品对象
            ProductVO productVO = new ProductVO();
            productVO.setCategoryName(productCategory.getCategoryName());
            productVO.setCategoryType(productCategory.getCategoryType());

            // 具体的商品列表
            productVO.setFoods(((List<ProductInfoVO>) multimap.get(productCategory.getCategoryType())));
            productVOList.add(productVO);
        }

        return ResultVO.ok(productVOList);
    }
}