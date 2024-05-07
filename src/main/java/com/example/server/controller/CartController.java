package com.example.server.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.server.DTOREUQUEST.CartDTO;
import com.example.server.DTOREUQUEST.CartRequest;
import com.example.server.service.CartService;

import entity.Cart;
import entity.Product;
import lombok.RequiredArgsConstructor;
@Controller
@RequiredArgsConstructor
public class CartController {
	private final CartService cartService;
	@PostMapping("/cart")    // name값을 requestparam에 담아온다
    public String save(@RequestBody CartDTO cartDTO) {
        
        cartService.save(cartDTO);
        return "redirect:/main2";
    }
	@PostMapping("/viewcart")    // name값을 requestparam에 담아온다
    public String viewCart(@RequestParam("customer") Long customerId, Model model) {
		System.out.println(customerId+"이거 받아쪄");
		List <Cart> cartItem = cartService.getCartItemsByCustomer(customerId);
		model.addAttribute("cartItem", cartItem);
        return "cart";
    }
	@GetMapping("/viewcart")
	public String viewCart1(@RequestParam("customer") Long customerId, Model model) {
	    System.out.println(customerId + "이거 받아쪄");
	    List<Cart> cartItem = cartService.getCartItemsByCustomer(customerId);
	    if(cartItem.size()!=0) {
	    Long a = cartItem.get(0).getCustomer().getCustomerId();
	    model.addAttribute("cartcid",a);
	    }
	    model.addAttribute("cartItems", cartItem);
	    return "cart";
	}
	@PostMapping("/deletecart")
	public String deleteCart(@RequestBody  CartRequest cartRequest, Model model) {
		Long customerId = cartRequest.getCustomerId();
	    List<Long> cartIds = cartRequest.getCartIds();
	    System.out.println(cartIds.size());
	    cartIds.removeIf(Objects::isNull);
	    for (Long cartId : cartIds) {
	        System.out.println("cartId: " + cartId);
	        cartService.remove(cartId);
	    }
	    List<Cart> cartItem = cartService.getCartItemsByCustomer(customerId);
	    model.addAttribute("cartItems", cartItem);
	    return "cart";
	}
	@PostMapping("/purchasecart")
	public String purchasecart(@RequestBody  CartRequest cartRequest, Model model) {
		Long customerId = cartRequest.getCustomerId();
	    List<Long> cartIds = cartRequest.getCartIds();
	    System.out.println(cartIds.size());
	    cartIds.removeIf(Objects::isNull);
	    for (Long cartId : cartIds) {
	        System.out.println("cartId: " + cartId);
	        cartService.purchase(cartId,customerId);
	    }
	return "cart";    
	}
}
