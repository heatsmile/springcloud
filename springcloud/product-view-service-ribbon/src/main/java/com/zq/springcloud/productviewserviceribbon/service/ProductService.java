package com.zq.springcloud.productviewserviceribbon.service;

import com.zq.springcloud.productviewserviceribbon.client.ProductClientRibbon;
import com.zq.springcloud.productviewserviceribbon.pojo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductClientRibbon productClientRibbon;
    public List<Product> listProducts(){
        return productClientRibbon.listProducts();
    }
}
