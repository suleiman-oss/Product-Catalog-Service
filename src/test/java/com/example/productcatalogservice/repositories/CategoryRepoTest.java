package com.example.productcatalogservice.repositories;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.productcatalogservice.models.Category;
import com.example.productcatalogservice.models.Product;

import jakarta.transaction.Transactional;

@SpringBootTest
public class CategoryRepoTest {
    
    @Autowired
    CategoryRepo categoryRepo;
    
    @Test
    @Transactional
    void testFetchTypes() {
        Optional<Category> cOptional=categoryRepo.findById(1L);
        System.out.println(cOptional.get().getName());
        for(Product p: cOptional.get().getProducts()){
            System.out.println(p.getName());
        }
    }
}
