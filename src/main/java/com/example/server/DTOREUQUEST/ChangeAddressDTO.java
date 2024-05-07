package com.example.server.DTOREUQUEST;


import java.time.LocalDate;

import entity.Customer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//lombok dependency추가
@Getter
@Setter
@NoArgsConstructor
@ToString
public class ChangeAddressDTO { //회원 정보를 필드로 정의
    private Long userId;
    private String newAddress;
    private String newAddress_1;
    private String newPostcode;
    //lombok 어노테이션으로 getter,setter,생성자,toString 메서드 생략 가능
    
    public static CustomerDTO toCustomerDTO(Customer CustomerEntitiy){
    	CustomerDTO customerDTO = new CustomerDTO();
    	customerDTO.setCustomerId(CustomerEntitiy.getCustomerId());
    	customerDTO.setUsername(CustomerEntitiy.getUsername());
    	customerDTO.setEmail(CustomerEntitiy.getEmail());
    	customerDTO.setName(CustomerEntitiy.getName());
    	customerDTO.setPassword(CustomerEntitiy.getPassword());
    	customerDTO.setBirthdate(CustomerEntitiy.getBirthdate());
    	customerDTO.setAddress(CustomerEntitiy.getAddress());
    	customerDTO.setPhone(CustomerEntitiy.getPhone());
    	customerDTO.setPermission(CustomerEntitiy.getPermission());
        return customerDTO;
    }
}