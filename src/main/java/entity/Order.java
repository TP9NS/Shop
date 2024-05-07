package entity;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;


@Entity
@Table(name = "orders")
@Getter
@Setter
public class Order {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "count")
    private Integer count;

    @Column(name = "total_price")
    private BigDecimal totalPrice;

    @Column(name = "order_date")
    private LocalDate orderDate;
    @Column(name = "postcode", length = 10)
    private String postcode;
    @Column(name = "address", length = 200)
    private String address;
    @Column(name = "address_1", length = 200)
    private String address_1;

    @Column(name = "status", length = 50)
    private String status;

    
}
