package com.lx.sell.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lx.sell.converter.CategoryForm2ProductCategoryConverter;
import com.lx.sell.entity.ProductCategory;
import com.lx.sell.entity.ProductInfo;
import com.lx.sell.form.CategoryForm;
import com.lx.sell.service.ProductCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

/**
 * @author Werdio丶
 * @since 2020-05-25 14:18:08
 */
@Slf4j
@RequestMapping("/seller/category")
@Controller
public class SellerCategoryController {

    @Autowired
    private ProductCategoryService productCategoryService;

    @GetMapping("/list")
    public String list(Model model){

        log.info("【查询类目】");

        List<ProductCategory> productCategoryList = productCategoryService.queryAll();

        log.info("【查询类目】productCategoryList = {}", productCategoryList);

        model.addAttribute("categoryList", productCategoryList);

        return "/category/list";
    }

    @GetMapping("/index")
    public String index(String categoryId, Model model){
        log.info("【跳转到修改类目页面】categoryId = {}", categoryId);

        if (!StringUtils.isEmpty(categoryId)){
            ProductCategory category = productCategoryService.queryById(Integer.parseInt(categoryId));
            log.info("category = {}", category);
            model.addAttribute("category", category);
        }

        return "/category/index";
    }

    @PostMapping("/save")
    public String save(@Valid CategoryForm categoryForm, BindingResult bindingResult, Model model){

        log.info("【保存类目信息】categoryForm = {}", categoryForm);
        model.addAttribute("url", "/sell/seller/category/index");

        if (bindingResult.hasErrors()){
            log.error("{}, ", Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
            model.addAttribute("msg", bindingResult.getFieldError().getDefaultMessage());

            return "/common/error";
        }

        ProductCategory category = CategoryForm2ProductCategoryConverter.converter(categoryForm);
        log.info("category = {}", category);

        if (StringUtils.isEmpty(category.getCategoryId())){
            ProductCategory insert = productCategoryService.insert(category);
        }else {
            ProductCategory update = productCategoryService.update(category);
        }

        return "/common/success";
    }
}
