package entity;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import java.time.LocalDate;

import com.example.server.DTOREUQUEST.CustomerDTO;

@Entity
@Table(name = "customer")
@Getter
@Setter
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "id", unique = true, length = 30, nullable = false)
    private String username;

    @Column(name = "password", length = 30, nullable = false)
    private String password;

    @Column(name = "name", length = 20, nullable = false)
    private String name;

    @Column(name = "birthdate", nullable = false)
    private LocalDate birthdate;

    @Column(name = "email", length = 40)
    private String email;

    @Column(name = "phone", length = 20)
    private String phone;

    @Column(name = "postcode", length = 200)
    private String postcode;
    
    @Column(name = "address", length = 200)
    private String address;
    
    @Column(name = "address_1", length = 200)
    private String address_1;
    
    @Column(name = "permission")
    private Integer permission;

    public static Customer toCustomer(CustomerDTO customerDTO){
    	Customer customer = new Customer();
    	customer.setUsername(customerDTO.getUsername());
    	customer.setEmail(customerDTO.getEmail());
    	customer.setName(customerDTO.getName());
    	customer.setPassword(customerDTO.getPassword());
    	customer.setBirthdate(customerDTO.getBirthdate());
    	customer.setPostcode(customerDTO.getPostcode());
    	customer.setAddress(customerDTO.getAddress());
    	customer.setAddress_1(customerDTO.getAddress_1());
    	customer.setPhone(customerDTO.getPhone());
    	customer.setPermission(customerDTO.getPermission());
        return customer;
    }
}