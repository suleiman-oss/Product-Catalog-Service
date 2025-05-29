package com.example.productcatalogservice.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.productcatalogservice.models.Category;
import com.example.productcatalogservice.models.Product;
import com.example.productcatalogservice.services.IProductService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(ProductController.class)
public class ProductControllerMVCTest {
    @Autowired
    private MockMvc mockMVC;
    @MockBean
    private IProductService productService;
    @Autowired
    private ObjectMapper objMapper;
    
    @Test
    public void Test_GetAllProducts_RunsSuccessfully() throws Exception{
        List<Product> productList=new ArrayList<>();
        Product product1=new Product();
        product1.setId(1L);
        product1.setName("Iphone 16");
        product1.setPrice(1000.0);
        product1.setCategory(new Category());
        Product product2=new Product();
        product2.setId(2L);
        product2.setName("Iphone 16 Pro");
        product2.setPrice(1150.0);
        product2.setCategory(new Category());
        productList.add(product1);
        productList.add(product2);

        when(productService.getAllProducts()).thenReturn(productList);
        mockMVC.perform(get("/products")).andExpect(status().isOk()).andExpect(content().string(objMapper.writeValueAsString(productList)));
    }
}
