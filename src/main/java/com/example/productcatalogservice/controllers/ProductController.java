package com.example.productcatalogservice.controllers;

import com.example.productcatalogservice.models.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
    @GetMapping("/producst/{id}")
    public Product getProduct(@PathVariable("id") Long productId){
        Product product=new Product();
        product.setId(productId);
        return product;
    }
}
