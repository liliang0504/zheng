package com.zheng.shop.web.controller;

import com.zheng.common.base.BaseController;
import com.zheng.shop.dao.model.ShopProduct;
import com.zheng.shop.dao.model.ShopProductExample;
import com.zheng.shop.rpc.api.ShopProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * 首页控制器
 */
@Controller
public class IndexController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private ShopProductService shopProductService;


    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(Model model) {
        // 所有系统
        ShopProductExample shopProductExample = new ShopProductExample();
        List<ShopProduct> products = shopProductService.selectByExample(shopProductExample);
        model.addAttribute("products", products);
        return thymeleaf("/index");
    }

}