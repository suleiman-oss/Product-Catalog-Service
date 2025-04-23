package com.example.productcatalogservice.services;

import java.util.List;

import com.example.productcatalogservice.models.Product;

public interface IProductService {

    Product getProductById(Long id);

    List<Product> getAllProducts();

    Product createProduct(Product product);

}