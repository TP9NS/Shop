package com.example.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.server.DTOREUQUEST.ReviewDTO;
import com.example.server.repository.CustomerRepository;
import com.example.server.repository.ProductRepository;
import com.example.server.service.OrderService;
import com.example.server.service.ReviewService;

import entity.Customer;
import entity.Product;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ReviewController {
	
    @Autowired
    private CustomerRepository customerRepository; // Customer 엔티티를 다루는 Repository
    @Autowired
    private ReviewService reviewservice;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderService orderService;
    @PostMapping("/submitReview")
    public ResponseEntity<String> submitReview(@RequestBody ReviewDTO review, HttpSession session) {
        if (orderService.ispurchase(review.getCustomer(), review.getProduct())) {
            reviewservice.saveReview(review);
            
            return ResponseEntity.ok("리뷰가 등록되었습니다.");
        } else {
            // 서버에 실패 보내기
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("상품을 구매하신 고객만 후기 작성이 가능합니다.");
        }
    }
    @PostMapping("/deleteReview")
    public ResponseEntity<String> deleteReview(@RequestBody ReviewDTO review, HttpSession session) {
        reviewservice.deleteReview(review.getReviewId());
        return ResponseEntity.ok("리뷰가 등록되었습니다.");
    }
    @PostMapping("/editReview")
    public ResponseEntity<String> editReview(@RequestBody ReviewDTO review, HttpSession session) {
        reviewservice.modifyReview(review);
        return ResponseEntity.ok("리뷰가 등록되었습니다.");
    }
    }