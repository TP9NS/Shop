package com.example.server.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.server.DTOREUQUEST.ChangeAddressDTO;
import com.example.server.DTOREUQUEST.CustomerDTO;
import com.example.server.DTOREUQUEST.findDTO;
import com.example.server.repository.CustomerRepository;

import entity.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;

@Service //스프링이 관리해주는 객체 == 스프링 빈
@RequiredArgsConstructor //controller와 같이. final 멤버변수 생성자 만드는 역할 
@Transactional
public class CustomerService {

    private final CustomerRepository customerRepository; // 먼저 jpa, mysql dependency 추가

    public void save(CustomerDTO customerDTO) {
        // repsitory의 save 메서드 호출
        Customer memberEntity = Customer.toCustomer(customerDTO);
        customerRepository.save(memberEntity);
        //Repository의 save메서드 호출 (조건. entity객체를 넘겨줘야 함)

    }
    @PersistenceContext
    private EntityManager entityManager;
    public void changeAddress(ChangeAddressDTO cad) {
    	Customer cust = customerRepository.findById(cad.getUserId()).orElse(null);
    	cust.setPostcode(cad.getNewPostcode());
    	cust.setAddress(cad.getNewAddress());
    	cust.setAddress_1(cad.getNewAddress_1());
    }
    public Customer findCustomerById(Long customerId) {
        return entityManager.find(Customer.class, customerId);
    }
    public CustomerDTO login(CustomerDTO memberDTO){ //entity객체는 service에서만
        Optional<Customer> byUsername = customerRepository.findByUsername(memberDTO.getUsername());
        if(byUsername.isPresent()){
            // 조회 결과가 있다
            Customer customer = byUsername.get(); // Optional에서 꺼냄
            if(customer.getPassword().equals(memberDTO.getPassword())) {
                //비밀번호 일치
                //entity -> dto 변환 후 리턴
                CustomerDTO dto = CustomerDTO.toCustomerDTO(customer);
                return dto;
            } else {
                //비밀번호 불일치
                return null;
            }
        } else {
            // 조회 결과가 없다
            return null;
        }
    }
    public String findID(findDTO findDTO) {
    	
    	Customer customer = customerRepository.findByNameAndBirthdateAndEmail(findDTO.getName(),findDTO.getBirth(),findDTO.getEmail());
    	
    	return customer.getUsername();
    }
 public String findPASS(findDTO findDTO) {
    	
    	Customer customer = customerRepository.findByUsernameAndNameAndBirthdateAndEmail(findDTO.getId(),findDTO.getName(),findDTO.getBirth(),findDTO.getEmail());
    	
    	return customer.getPassword();
    }
 public boolean checkid(findDTO findDTO) {
 	
 	Customer customer = customerRepository.findByUsername(findDTO.getId()).orElse(null);
 	if(customer==null) {
 		return false;
 	}
 	return true;
 }
}