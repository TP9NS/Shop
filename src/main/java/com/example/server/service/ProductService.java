package com.example.server.service;

import entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.server.DTOREUQUEST.ProductDTO;
import com.example.server.repository.ProductRepository;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
    	List<Product> products = productRepository.findAll();
        
        System.out.println("Products: " + products);
        return products;
    }
    public Page<Product> getList(int page) {
        Pageable pageable = PageRequest.of(page, 16);
        return this.productRepository.findAll(pageable);
    }
    public List<Product> getCategoryProducts(String category) {
    	//List<Product> products = productRepository.findByCategory(category);
        
        //System.out.println("Products: " + products);
        //return products;
    	return null;
    }
    public Page<Product> getCategoryList(String category,int page) {
        Pageable pageable = PageRequest.of(page, 8);
        return this.productRepository.findByCategory(category,pageable);
    }
    public Product getProductById(Long productId) {
        // ProductRepository를 사용하여 해당 ID에 해당하는 제품을 찾아 반환합니다.
        return productRepository.findById(productId).orElse(null);
    }
    public void saveProduct(ProductDTO productDTO) {
    	Product product = new Product();
    	product.setProductName(productDTO.getProductName());
    	product.setPrice(productDTO.getPrice());
    	product.setSize(productDTO.getSize());
    	product.setManufacturer(productDTO.getManufacturer());
    	product.setProductImage1(productDTO.getProductImage1());
    	product.setProductImage2(productDTO.getProductImage2());
    	product.setProductImage3(productDTO.getProductImage3());
    	product.setProductImage4(productDTO.getProductImage4());
    	product.setProductDescription(productDTO.getDescription());
    	product.setCategory(productDTO.getCategory());
    	
    	productRepository.save(product);
    }
    public void editProduct(Long productId, ProductDTO product) {
        // ID를 기반으로 기존 제품을 찾습니다.
        Product existingProduct = productRepository.findById(product.getProductId()).orElse(null);

        if (existingProduct != null) {
            // 새로운 데이터로 기존 제품을 업데이트합니다.
            existingProduct.setProductName(product.getProductName());
            existingProduct.setPrice(product.getPrice());
            existingProduct.setSize(product.getSize());
            existingProduct.setManufacturer(product.getManufacturer());
            existingProduct.setProductImage1(product.getProductImage1());
            existingProduct.setProductImage2(product.getProductImage2());
            existingProduct.setProductImage3(product.getProductImage3());
            existingProduct.setProductImage4(product.getProductImage4());
            existingProduct.setProductDescription(product.getDescription());
            existingProduct.setCategory(product.getCategory());

            // 업데이트된 제품을 저장합니다.
            productRepository.save(existingProduct);
        } 
    }
    public void remove(Long productId) {
    	Product product = productRepository.findById(productId).orElse(null);
    	productRepository.delete(product);
    }
    
}