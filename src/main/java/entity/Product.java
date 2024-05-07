package entity;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;

import jakarta.persistence.Column;
@Getter
@Setter
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(name = "price", nullable = false, columnDefinition = "decimal(10,2)")
    private Double price;
    
    @Column(name = "size")
    private int size;
    
    @Column(name = "manufacturer")
    private String manufacturer;

    @Column(name = "product_image1")
    private String productImage1;

    @Column(name = "product_image2")
    private String productImage2;

    @Column(name = "product_image3")
    private String productImage3;

    @Column(name = "product_image4")
    private String productImage4;

    @Column(name = "product_description", columnDefinition = "TEXT")
    private String productDescription;

    @Column(name = "category")
    private String category;

    // Constructors and other methods
}