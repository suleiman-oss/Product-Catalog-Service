package com.example.productcatalogservice.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
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
    @Test
    @DisplayName("ID 0 being passed would result in Invalid ID exception")
    public void Test_GetPorductByInvalidId_ThrowsException(){
       Exception ex= assertThrows(IllegalArgumentException.class,()->productController.getProduct(0L));
        assertEquals("Invalid ID", ex.getMessage());
        verify(productService,times(0)).getProductById(0L);
    }
    @Test
    public void Test_GetProduductById_ProductServiceThrowsException(){
        when(productService.getProductById(any(Long.class))).thenThrow(new RuntimeException("anonymous exception"));
        assertThrows(RuntimeException.class,()->productController.getProduct(0L));
    }
}
