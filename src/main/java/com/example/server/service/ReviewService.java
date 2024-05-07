package com.example.server.service;


import entity.Customer;
import entity.Product;
import entity.Review;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.server.DTOREUQUEST.ReviewDTO;
import com.example.server.repository.CustomerRepository;
import com.example.server.repository.ProductRepository;
import com.example.server.repository.ReviewRepository;

import java.time.LocalDate;
import java.util.List;

@Service //스프링이 관리해주는 객체 == 스프링 빈

@Transactional
public class ReviewService {
	@Autowired
	private final ReviewRepository reviewRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private CustomerRepository customerRepository; 
    @Autowired
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public List<Review> getReviewsByProduct(Product product) {
        return reviewRepository.findByProduct(product);
    }
    public void saveReview(ReviewDTO review) {
    	Long customerID = review.getCustomer();
    	Long ProductID = review.getProduct();
    	Customer customer = customerRepository.findById(customerID).orElse(null);
    	Product product = productRepository.findById(ProductID).orElse(null);
    	 LocalDate Date = LocalDate.now();
    	Review saveReview = new Review();
    	saveReview.setContent(review.getContent());
    	saveReview.setTitle(review.getTitle());
    	saveReview.setCustomer(customer);
    	saveReview.setProduct(product);
    	saveReview.setCreatedAt(Date);
    	
    	reviewRepository.save(saveReview);
    }
    public void deleteReview(Long reviewId) {
    	Review review = reviewRepository.getById(reviewId);
    	reviewRepository.delete(review);
    }
    public void modifyReview(ReviewDTO review) {
    	Review reviewEntity = reviewRepository.getById(review.getReviewId());
    	reviewEntity.setTitle(review.getTitle());
    	reviewEntity.setContent(review.getContent());
    	LocalDate Date = LocalDate.now();
    	reviewEntity.setModifyAt(Date);
    	reviewRepository.save(reviewEntity);
    }
}