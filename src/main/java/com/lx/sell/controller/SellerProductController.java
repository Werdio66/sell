package com.lx.sell.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lx.sell.constant.UrlConstant;
import com.lx.sell.converter.ProductForm2ProductInfoConverter;
import com.lx.sell.dto.OrderDTO;
import com.lx.sell.entity.ProductCategory;
import com.lx.sell.entity.ProductInfo;
import com.lx.sell.form.ProductForm;
import com.lx.sell.service.ProductCategoryService;
import com.lx.sell.service.ProductInfoService;
import com.lx.sell.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Objects;

/**
 * @author Werdio丶
 * @since 2020-05-24 20:18:59
 */
@Slf4j
@RequestMapping("/seller/product")
@Controller
public class SellerProductController {

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private ProductCategoryService productCategoryService;

    @GetMapping("/list")
    public String list(@RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                       @RequestParam(name = "pageSize", required = false, defaultValue = "3") Integer pageSize,
                       Model model){

        log.info("【查询商品】pageNum = {}, pageSize = {}", pageNum, pageSize);

        PageHelper.startPage(pageNum, pageSize);
        PageInfo<ProductInfo> pageInfo = productInfoService.queryAll();

        log.info("【查询商品】pageInfo = {}", pageInfo);
        log.info("【查询商品】product = {}", pageInfo.getList());

        model.addAttribute("productInfoPage", pageInfo);
        return "/product/list";
    }

    @PostMapping("/save")
    public String save(@Valid ProductForm productForm, BindingResult bindingResult, Model model){
        log.info("【保存商品】productForm = {}", productForm);
        model.addAttribute("url", UrlConstant.PRODUCT_LIST);

        if (bindingResult.hasErrors()){
            log.error("{}, ", Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
            model.addAttribute("msg", bindingResult.getFieldError().getDefaultMessage());

            return "/common/error";
        }

        ProductInfo productInfo = ProductForm2ProductInfoConverter.converter(productForm);

        if (StringUtils.isEmpty(productInfo.getProductId())){
            ProductInfo insert = productInfoService.insert(productInfo);
        }else {
            ProductInfo update = productInfoService.update(productInfo);
        }

        return "/common/success";
    }

    @GetMapping("/index")
    public String index(String productId, Model model){
        log.info("【跳转到保存商品页面】productId = {}", productId);
        if (! StringUtils.isEmpty(productId)){
            ProductInfo productInfo = productInfoService.queryById(productId);
            log.info("【商品信息】productInfo = {}", productInfo);
            model.addAttribute("productInfo", productInfo);
        }

        List<ProductCategory> categoryList = productCategoryService.queryAll();
        log.info("【商品类目信息】categoryList = {}", categoryList);

        model.addAttribute("categoryList", categoryList);

        return "/product/index";
    }

    @GetMapping("/on_sale")
    public String onSale(@NotEmpty String productId, Model model){
        log.info("【上架】productId = {}", productId);

        ProductInfo productInfo = productInfoService.onSave(productId);

        model.addAttribute("url", UrlConstant.PRODUCT_LIST);

        return "/common/success";
    }

    @GetMapping("/off_sale")
    public String offSale(@NotEmpty String productId, Model model){
        log.info("【下架】productId = {}", productId);
        ProductInfo productInfo = productInfoService.offSave(productId);
        model.addAttribute("url", UrlConstant.PRODUCT_LIST);

        return "/common/success";
    }
}
