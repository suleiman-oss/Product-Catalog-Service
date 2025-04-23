package com.example.productcatalogservice.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.productcatalogservice.dtos.FakestoreProductDTO;
import com.example.productcatalogservice.models.Category;
import com.example.productcatalogservice.models.Product;

@Service
public class ProductService implements IProductService {
    @Autowired
    RestTemplateBuilder restTemplateBuilder;
    @Override
    public Product getProductById(Long id){
        RestTemplate restTemplate=restTemplateBuilder.build();
        FakestoreProductDTO fakestoreProductDTO= restTemplate.getForEntity("https://fakestoreapi.com/products/{id}",FakestoreProductDTO.class,id).getBody();
        return null;
    }

    @Override
    public List<Product> getAllProducts(){
        return new ArrayList<Product>();
    }

    @Override
    public Product createProduct(Product product){
        return product;
    }

    private Product from(FakestoreProductDTO fakestoreProductDTO){
        Product product=new Product();
        product.setId(fakestoreProductDTO.getId());
        product.setImageURL(fakestoreProductDTO.getImage());
        product.setDescription(fakestoreProductDTO.getDescription());
        product.setPrice(fakestoreProductDTO.getPrice());
        Category category=new Category();
        category.setDescription(fakestoreProductDTO.getCategory());
        category.setName(fakestoreProductDTO.getCategory());
        product.setCategory(category);
        return product;
    }

}
