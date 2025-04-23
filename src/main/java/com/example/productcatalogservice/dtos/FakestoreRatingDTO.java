package com.example.productcatalogservice.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FakestoreRatingDTO {
    private Double rate;
    private Long count;
}
