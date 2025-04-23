package com.example.productcatalogservice.controllers;

import com.example.productcatalogservice.dtos.CategoryDTO;
import com.example.productcatalogservice.dtos.ProductDTO;
import com.example.productcatalogservice.models.Product;
import com.example.productcatalogservice.services.IProductService;

import java.rmi.NoSuchObjectException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
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
        List<Product> products=productService.getAllProducts();
        List<ProductDTO> productDTOs=new ArrayList<>();
        for(Product product:products){
            productDTOs.add(from(product));
        }
        return productDTOs;
    }
    

    @GetMapping("{id}")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable("id") Long productId){
        Product product= productService.getProductById(productId);
        ProductDTO productDTO= from(product);
        MultiValueMap<String,String> headers=new LinkedMultiValueMap<>();
        headers.add("Called By", "Postman");
        if (product==null || productDTO==null){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<ProductDTO>(productDTO, headers, HttpStatus.OK);
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
