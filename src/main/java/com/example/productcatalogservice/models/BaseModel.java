package com.example.productcatalogservice.models;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@MappedSuperclass
public abstract class BaseModel {
    @Id
    private Long id;
    private Date createdAt;
    private Date lastUpdatedAt;
    private State state;
}
