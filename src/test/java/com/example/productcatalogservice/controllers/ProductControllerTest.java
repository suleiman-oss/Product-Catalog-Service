package com.example.productcatalogservice.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import com.example.productcatalogservice.dtos.ProductDTO;
import com.example.productcatalogservice.models.Category;
import com.example.productcatalogservice.models.Product;
import com.example.productcatalogservice.services.IProductService;

@SpringBootTest
public class ProductControllerTest {
    @Autowired
    private ProductController productController;
    @MockBean
    private IProductService productService;
    @Test
    public void Test_GetProductBYId_WithValidId_ReturnsProductSuccessfully(){
        Category c=new Category();
        c.setId(2L);
        c.setName("Desktops");
        Long id=1L;
        Product product=new Product();
        product.setId(id);
        product.setName("Dell Desktop");
        product.setCategory(c);
        when(productService.getProductById(id)).thenReturn(product);

        ResponseEntity<ProductDTO> response= productController.getProduct(id);
        assertNotNull(response.getBody());
        assertEquals(id, response.getBody().getId());
        assertEquals("Dell Desktop", response.getBody().getName());
    }
}
