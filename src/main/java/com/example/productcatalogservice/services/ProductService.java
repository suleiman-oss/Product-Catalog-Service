package com.example.productcatalogservice.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;

import com.example.productcatalogservice.clients.FaksestoreAPIClient;
import com.example.productcatalogservice.dtos.FakestoreProductDTO;
import com.example.productcatalogservice.models.Category;
import com.example.productcatalogservice.models.Product;

@Service
public class ProductService implements IProductService {
    @Autowired
    RestTemplateBuilder restTemplateBuilder;

    @Autowired
    FaksestoreAPIClient faksestoreAPIClient;

    @Override
    public Product getProductById(Long id){
        return from(faksestoreAPIClient.getProductById(id));
    }

    @Override
    public List<Product> getAllProducts(){
        List<Product> products=new ArrayList<>();
        FakestoreProductDTO[] fakestoreProductDTOs = faksestoreAPIClient.getAllProducts();
        if (fakestoreProductDTOs==null){
            return null;
        }
        for(FakestoreProductDTO fakestoreProductDTO:fakestoreProductDTOs){
            products.add(from(fakestoreProductDTO));
        }
        return products;
    }

    @Override
    public Product replaceProduct(Long id, Product product){
        FakestoreProductDTO fakestoreProductDTOreq = from(product);
        FakestoreProductDTO fakestoreProductDTO=faksestoreAPIClient.replaceProduct(id, fakestoreProductDTOreq);
        return from(fakestoreProductDTO);
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
    private FakestoreProductDTO from(Product product){
        FakestoreProductDTO fakestoreProductDTO=new FakestoreProductDTO();
        fakestoreProductDTO.setId(product.getId());
        fakestoreProductDTO.setTitle(product.getName());
        fakestoreProductDTO.setImage(product.getImageURL());
        fakestoreProductDTO.setDescription(product.getDescription());
        fakestoreProductDTO.setCategory(product.getCategory().getName());
        fakestoreProductDTO.setPrice(product.getPrice());
        return fakestoreProductDTO;
    }

}
