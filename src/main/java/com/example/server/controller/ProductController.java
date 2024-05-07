package com.example.server.controller;

import entity.Product;
import entity.Review;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.server.DTOREUQUEST.ProductDTO;
import com.example.server.repository.ReviewRepository;
import com.example.server.service.ProductService;
import com.example.server.service.ReviewService;

import java.util.List;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.domain.Page;
@Controller
@RequiredArgsConstructor
public class ProductController {
	@Autowired
	private final ReviewRepository reviewRepository;
    private final ProductService productService;
    private final ReviewService reviewService;
    
    @GetMapping("/products")
    public String getAllProducts(Model model) {
    	System.out.println("asdfasdf");
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "index"; 
    }
    @GetMapping("/main2")
    public String getAllProductss(Model model,@RequestParam(value="page", defaultValue="0") int page) {
    	Page<Product> paging = this.productService.getList(page);
        model.addAttribute("paging", paging);
        return "main"; 
    }
    @GetMapping("/category")
    public String getcategoryProductss(@RequestParam("id") String category, Model model,@RequestParam(value="page", defaultValue="0") int page) {

    	if(category.equals("shoes")) {
    		String kcategory="신발";
    		Page<Product> paging = this.productService.getCategoryList(kcategory,page);
    		 model.addAttribute("paging", paging);
    		return "shoespage";
    	}else if(category.equals("outer")){
    		String kcategory="아우터";
    		Page<Product> paging = this.productService.getCategoryList(kcategory,page);
   		 model.addAttribute("paging", paging);
    		return "outerpage";
    	}else if(category.equals("down")){
    		String kcategory="하의";
    		Page<Product> paging = this.productService.getCategoryList(kcategory,page);
      		 model.addAttribute("paging", paging);
    		return "downpage";
    	}else if(category.equals("up")){
    		String kcategory="상의";
    		Page<Product> paging = this.productService.getCategoryList(kcategory,page);
      		 model.addAttribute("paging", paging);
       		return "uppage";
    	}else {
    		return "redirect:/main2";
    	}
    }
    // 다른 요청에 따른 메서드를 추가할 수 있음
    @GetMapping("/productDetail")
    public String getProductDetail(@RequestParam("id") Long productId, Model model) {
        Product product =  productService.getProductById(productId);
        
        List<Review> review = reviewService.getReviewsByProduct(product);
    	model.addAttribute("product", product);
    	model.addAttribute("reviews",review);
        return "product"; // 상품 상세 정보 페이지로 이동
    }
    @GetMapping("/addProduct")
    public String addProduct(Model model) {
        
    	model.addAttribute("product");
        return "addproduct"; // 상품 상세 정보 페이지로 이동
    }
    
    @PostMapping("/addProduct")
    public String addProduct1(@ModelAttribute ProductDTO productDTO,Model model) {
    	model.addAttribute("product");
    	productService.saveProduct(productDTO);
        return "redirect:/main2"; // 상품 상세 정보 페이지로 이동
    }
    
    @GetMapping("/editProduct")
    public String editProduct(@RequestParam("id") Long productId, Model model) {
        Product product =  productService.getProductById(productId);
    	model.addAttribute("product", product);
        return "editproduct"; // 상품 상세 정보 페이지로 이동
    }
    @GetMapping("/deleteProduct")
    public String deleteProduct(@RequestParam("id") Long productId, Model model) {
        
    	productService.remove(productId);
    	return "redirect:/main2";
    }
    @PostMapping("/editProduct")
    public String editProduct1(@ModelAttribute ProductDTO product,Model model) {
    	Long productId = product.getProductId();
    	productService.editProduct(productId,product);
    	return "redirect:/main2";
    }
}