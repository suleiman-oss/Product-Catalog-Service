package com.example.productcatalogservice.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.productcatalogservice.models.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product,Long>{
    Optional<Product> findById(Long id);
}
