package com.zq.springcloud.productdataservice.controller;

import com.zq.springcloud.productdataservice.pojo.Product;
import com.zq.springcloud.productdataservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    ProductService productService;

    @RequestMapping("/products")
    public Object products(){
        List<Product> ps=productService.listProducts();
        return ps;
    }
}
