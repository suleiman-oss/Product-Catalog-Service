package com.example.productcatalogservice.controllers;

import com.example.productcatalogservice.dtos.CategoryDTO;
import com.example.productcatalogservice.dtos.ProductDTO;
import com.example.productcatalogservice.models.Product;
import com.example.productcatalogservice.services.IProductService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    IProductService productService;
    
    @GetMapping
    public List<ProductDTO> getAllProducts() {
        return new ArrayList<ProductDTO>();
    }
    

    @GetMapping("{id}")
    public ProductDTO getProduct(@PathVariable("id") Long productId){
        Product product= productService.getProductById(productId);
        return from(product);
    }
    @PostMapping
    public ProductDTO createProduct(@RequestBody ProductDTO productdDto){
        return productdDto;
    }
    @PutMapping("{id}")
    public ProductDTO replaceProduct(@PathVariable Long id,@RequestBody ProductDTO productDTO){
        return productDTO;
    }

    private ProductDTO from(Product product){
        ProductDTO productDTO=new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setDescription(product.getDescription());
        productDTO.setName(product.getName());
        productDTO.setPrice(product.getPrice());
        productDTO.setImageURL(product.getImageURL());
        CategoryDTO categoryDTO =new CategoryDTO();
        categoryDTO.setId(product.getCategory().getId());
        categoryDTO.setDescription(product.getCategory().getDescription());
        categoryDTO.setName(product.getCategory().getName());
        productDTO.setCategory(categoryDTO);
        return productDTO;
    }
}
