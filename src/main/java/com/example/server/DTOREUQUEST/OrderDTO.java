package com.example.server.DTOREUQUEST;

import java.math.BigDecimal;
import java.time.LocalDate;

import entity.Order;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDTO {
    
    private Long customer;
    private Long product;
    private Integer count;
    private BigDecimal totalPrice;
    private LocalDate orderDate;
    private String address;
    private String address_1;
    private String postcode;

    

    public static OrderDTO toOrderDTO(Order orderEntity) {
        OrderDTO orderDTO = new OrderDTO();
        
        orderDTO.setCustomer(orderEntity.getCustomer().getCustomerId());
        orderDTO.setProduct(orderEntity.getProduct().getProductId());
        orderDTO.setCount(orderEntity.getCount());
        orderDTO.setTotalPrice(orderEntity.getTotalPrice());
        orderDTO.setOrderDate(orderEntity.getOrderDate());
        orderDTO.setAddress(orderEntity.getAddress());
       
        return orderDTO;
    }
}