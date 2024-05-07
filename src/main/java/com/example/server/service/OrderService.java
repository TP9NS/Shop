package com.example.server.service;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.server.DTOREUQUEST.CacelOrderDTO;
import com.example.server.DTOREUQUEST.OrderDTO;
import com.example.server.DTOREUQUEST.updateOrderDTO;
import com.example.server.repository.CustomerRepository;
import com.example.server.repository.OrderRepository;
import com.example.server.repository.ProductRepository;

import entity.Customer;
import entity.Order;
import entity.Product;
import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor 
public class OrderService {
	
	 @Autowired
	    private CustomerRepository customerRepository;

	    @Autowired
	    private ProductRepository productRepository;
	    @Autowired
	    private OrderRepository orderRepository;
	    
	    public Order toOrder(OrderDTO orderDTO) {
	        Order order = new Order();
	       
	        order.setCount(orderDTO.getCount());
	        order.setTotalPrice(orderDTO.getTotalPrice());
	        order.setOrderDate(orderDTO.getOrderDate());
	        System.out.println(orderDTO.getOrderDate());
	        order.setAddress(orderDTO.getAddress());
	        order.setStatus("주문 완료");
	        order.setAddress_1(orderDTO.getAddress_1());
	        order.setPostcode(orderDTO.getPostcode());
	        Long customerId = orderDTO.getCustomer();
	        System.out.println(customerId);
	        Customer customer = customerRepository.findById(customerId).orElse(null); 
	        order.setCustomer(customer);

	        Long productId = orderDTO.getProduct();
	        Product product = productRepository.findById(productId).orElse(null);
	        order.setProduct(product);

	        return order;
	    }
	    public void save(OrderDTO orderDTO) {
	        // repsitory의 save 메서드 호출
	    	Order order = toOrder(orderDTO);
	    	
	    	orderRepository.save(order);
	        //Repository의 save메서드 호출 (조건. entity객체를 넘겨줘야 함)

	    }
	    public void delete(CacelOrderDTO ocd) {
	    	Long a= ocd.getOrderId();
	    	orderRepository.deleteById(a);
	    }
	    public void updateStatus(updateOrderDTO ocd) {
	    	Order order = orderRepository.findById(ocd.getOrderId()).orElse(null);;
	    	order.setStatus(ocd.getNewStatus());
	    	orderRepository.save(order);
	    }
	    
	    public boolean ispurchase(Long customerId, Long productId){
	    	  // OrderRepository의 findByCustomerAndProduct 메소드를 사용하여 주문 기록을 조회
	        List<Order> orders = orderRepository.findByCustomerAndProduct(customerRepository.findById(customerId).orElse(null),
	                                                                    productRepository.findById(productId).orElse(null));
	        // 검색 결과 확인
	        return !orders.isEmpty();
	    }
	    public Page<Order> getList(int page) {
	        Pageable pageable = PageRequest.of(page, 10);
	        return this.orderRepository.findAll(pageable);
	    }
	    public Page<Order> getStatusList(String status,int page) {
	        Pageable pageable = PageRequest.of(page, 10);
	        return this.orderRepository.findByStatus(status, pageable);
	    }
}
