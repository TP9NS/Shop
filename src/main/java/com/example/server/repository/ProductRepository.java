package com.example.server.repository;

import entity.Product;
import entity.Review;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	//List<Product> findByCategory(String category);
	Page<Product> findByCategory(String category, Pageable pageable);
	Page<Product> findAll(Pageable pageable);
}