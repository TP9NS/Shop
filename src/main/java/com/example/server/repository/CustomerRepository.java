package com.example.server.repository;

import entity.Customer;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	
	Optional<Customer> findByEmail(String Email);
	Optional<Customer> findByUsername(String Username);
	Customer findByCustomerId(Long customerId);
	 Customer findByNameAndBirthdateAndEmail(String name, LocalDate birthdate, String email);
	 Customer findByUsernameAndNameAndBirthdateAndEmail(String Username,String name, LocalDate birthdate, String email);
	 
	@Modifying
	@Query("UPDATE Customer c SET c.address = :newAddress WHERE c.customerId = :customerId")
	void updateAddressByCustomerId(@Param("customerId") Long customerId, @Param("newAddress") String newAddress);
}