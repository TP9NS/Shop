package com.example.server.repository;

import entity.Customer;
import entity.Product;
import entity.Review;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
	List<Review> findByProduct(Product product);
}