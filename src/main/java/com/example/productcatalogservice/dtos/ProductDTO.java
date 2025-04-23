package com.example.productcatalogservice.dtos;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {
    private Long id;
     private String name;
    private String imageURL;
    private String description;
    private Double price;
    private CategoryDTO category;
}
