package com.example.productcatalogservice.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product extends BaseModel {
    private String name;
    private String imageURL;
    private String description;
    private Double price;
    private Category category;
}
