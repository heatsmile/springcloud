package com.zq.spring.productviewservicefeign.service;

import com.zq.spring.productviewservicefeign.client.ProductClientFeign;
import com.zq.spring.productviewservicefeign.pojo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductClientFeign productClientFeign;
    public List<Product> listProducts(){
        return productClientFeign.listProducts();
    }
}
