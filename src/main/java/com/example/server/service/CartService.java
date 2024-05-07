package com.example.server.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.server.DTOREUQUEST.CartDTO;
import com.example.server.repository.CartRepository;
import com.example.server.repository.CustomerRepository;
import com.example.server.repository.OrderRepository;
import com.example.server.repository.ProductRepository;

import entity.Cart;
import entity.Customer;
import entity.Order;
import entity.Product;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor 
public class CartService {
	
	 @Autowired
	    private CustomerRepository customerRepository;

	    @Autowired
	    private ProductRepository productRepository;
	    @Autowired
	    private CartRepository cartRepository;
	    @Autowired
	    private OrderRepository orderRepository;
	    
	    public Cart toCart(CartDTO cartDTO) {
	    	Cart cart = new Cart();
	       
	    	cart.setCount(cartDTO.getCount());
	    
	        Long customerId = cartDTO.getCustomer();
	        System.out.println(customerId);
	        Customer customer = customerRepository.findById(customerId).orElse(null);
	        cart.setCustomer(customer);

	        Long productId = cartDTO.getProduct();
	        Product product = productRepository.findById(productId).orElse(null);
	        cart.setProduct(product);

	        return cart;
	    }
	    public void save(CartDTO cartDTO) {
	        // repsitory의 save 메서드 호출
	    	Cart cart = toCart(cartDTO);
	    	
	    	cartRepository.save(cart);
	        //Repository의 save메서드 호출 (조건. entity객체를 넘겨줘야 함)

	    }
	    public void remove(Long cartId) {
	    	@NonNull
	        Optional<Cart> cartOptional = cartRepository.findById(cartId);
	        cartOptional.ifPresent(cart -> {
	            System.out.println(cartId + " 카트 아이디이이이");
	            if(cart.getCartId()!=null) {
	            cartRepository.delete(cart);
	            }
	        });
	    }
	    public void purchase(Long cartId, Long customerId) {
	        Optional<Cart> cartOptional = cartRepository.findById(cartId);
	        cartOptional.ifPresent(cart -> {
	            Product product = cart.getProduct();
	            Order order = new Order();
	            Customer customer = cart.getCustomer();
	           
	            order.setAddress(customer.getAddress());
	            order.setAddress_1(customer.getAddress_1());
	            order.setPostcode(customer.getPostcode());
	            order.setCustomer(customer);
	            order.setCount(cart.getCount());
	            order.setProduct(product);
	            order.setStatus("주문 완료");
	            
	            BigDecimal productPrice = BigDecimal.valueOf(product.getPrice());
	            BigDecimal totalPrice = productPrice.multiply(BigDecimal.valueOf(cart.getCount()));
	            order.setTotalPrice(totalPrice);

	            // 오늘 날짜를 사용하여 LocalDate로 주문일 설정
	            LocalDate orderDate = LocalDate.now();
	            order.setOrderDate(orderDate);
	            orderRepository.save(order);
	            if (cart.getCartId() != null) {
	                cartRepository.delete(cart);
	            }
	        });
	    }

	    public List<Cart> getCartItemsByCustomer(Long customerId) {
	    	Customer customer = customerRepository.findById(customerId).orElse(null);
	        List<Cart> cartItems = cartRepository.findByCustomer(customer);
	       return cartItems;
	    }
}
