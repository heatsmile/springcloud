package com.zq.spring.productviewservicefeign.controller;

import com.zq.spring.productviewservicefeign.pojo.Product;
import com.zq.spring.productviewservicefeign.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class ProductController {
    @Autowired
    ProductService productService;
    @RequestMapping("/products")
    public Object listProducts(Model m){
        List<Product> ps= productService.listProducts();
        m.addAttribute("ps",ps);
        return "products";
    }
}
