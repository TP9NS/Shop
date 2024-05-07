package com.example.server.DTOREUQUEST;

import java.time.LocalDate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class editProductDTO { //회원 정보를 필드로 정의
   private Long productId;
	private String productName;
    private double price;
    private String manufacturer;
    private String productImage1;
    private String productImage2;
    private String productImage3;
    private String productImage4;
    private String description;
    private String category;
    
    }