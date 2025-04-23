package com.example.productcatalogservice.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.example.productcatalogservice.models.Product;
import com.example.productcatalogservice.repositories.ProductRepo;

@Service
@Primary
public class StorageProductService implements IProductService {
    @Autowired
    ProductRepo productRepo;
    @Override
    public Product getProductById(Long id) {
        Optional<Product> optionalProduct=productRepo.findById(id);
        if (optionalProduct.isPresent()){
            return optionalProduct.get();
        }
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
       List<Product> products=productRepo.findAll();
       return products;
    }

    @Override
    public Product createProduct(Product product) {
        Product res=productRepo.save(product);
        return res;
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        return productRepo.save(product);
    }
}