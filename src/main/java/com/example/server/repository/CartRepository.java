package com.example.server.repository;

import entity.Cart;
import entity.Customer;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
	List <Cart> findByCustomer(Customer customer);
	
}