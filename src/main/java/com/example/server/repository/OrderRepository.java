package com.example.server.repository;

import entity.Cart;
import entity.Customer;
import entity.Order;
import entity.Product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
	List <Order> findByCustomer(Customer customer);
	List<Order> findByCustomerAndProduct(Customer customer, Product product);
	 Page<Order> findAll(Pageable pageable);
	 Page<Order> findByStatus(String category, Pageable pageable);
}