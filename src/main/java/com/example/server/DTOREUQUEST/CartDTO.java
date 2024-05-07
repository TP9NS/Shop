package com.example.server.DTOREUQUEST;


import java.math.BigDecimal;
import java.time.LocalDate;

import entity.Cart;
import entity.Order;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartDTO {
    
    private Long customer;
    private Long product;
    private Integer count;
  
    

    public static CartDTO toOrderDTO(Cart cartEntity) {
        CartDTO cartDTO = new CartDTO();
        
        cartDTO.setCustomer(cartEntity.getCustomer().getCustomerId());
        cartDTO.setProduct(cartEntity.getProduct().getProductId());
        cartDTO.setCount(cartEntity.getCount());
              
        return cartDTO;
    }
}