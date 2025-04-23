package com.example.productcatalogservice.dtos;


import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDTO {
    private Long id;
     private String name;
    private String imageURL;
    private String description;
    private Double price;
    private CategoryDTO category;
}
