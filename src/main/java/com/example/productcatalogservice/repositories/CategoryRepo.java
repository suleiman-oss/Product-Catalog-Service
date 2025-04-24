package com.example.productcatalogservice.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.productcatalogservice.models.Category;


@Repository
public interface CategoryRepo extends JpaRepository<Category,Long>{
    Optional<Category> findById(Long id);
}
