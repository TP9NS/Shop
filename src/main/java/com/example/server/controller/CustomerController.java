package com.example.server.controller;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.server.DTOREUQUEST.ChangeAddressDTO;
import com.example.server.repository.CustomerRepository;
import com.example.server.service.CustomerService;

import entity.Customer;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class CustomerController {
	
    @Autowired
    private CustomerRepository customerRepository; // Customer 엔티티를 다루는 Repository
    @Autowired
    private CustomerService customerservice;
    
    @PostMapping("/changeAddress") // session : 로그인 유지
    public String changeAddress(@RequestBody ChangeAddressDTO customer ,HttpSession session) {
    	
    	customerservice.changeAddress(customer);
    	
            return "redirect:/main2";

}
    }