package com.example.productcatalogservice.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        ResponseEntity<FakestoreProductDTO> fakestoreProductDTOResponseEntity= restTemplate.getForEntity("https://fakestoreapi.com/products/{id}",FakestoreProductDTO.class,id);
        if(fakestoreProductDTOResponseEntity.getStatusCode().equals(HttpStatus.OK)){
            return from(fakestoreProductDTOResponseEntity.getBody());
        }
        return null;
    }

    @Override
    public List<Product> getAllProducts(){
        List<Product> products=new ArrayList<>();
        RestTemplate restTemplate=restTemplateBuilder.build();
        FakestoreProductDTO[] fakestoreProductDTOs = restTemplate.getForEntity("https://fakestoreapi.com/products", FakestoreProductDTO[].class).getBody();
        if (fakestoreProductDTOs==null){
            return null;
        }
        for(FakestoreProductDTO fakestoreProductDTO:fakestoreProductDTOs){
            products.add(from(fakestoreProductDTO));
        }
        return products;
    }

    @Override
    public Product createProduct(Product product){
        return product;
    }

    private Product from(FakestoreProductDTO fakestoreProductDTO){
        Product product=new Product();
        product.setId(fakestoreProductDTO.getId());
        product.setName(fakestoreProductDTO.getTitle());
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
