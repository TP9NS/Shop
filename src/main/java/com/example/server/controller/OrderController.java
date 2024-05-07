package com.example.server.controller;

import java.util.List;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.server.DTOREUQUEST.CacelOrderDTO;
import com.example.server.DTOREUQUEST.OrderDTO;
import com.example.server.DTOREUQUEST.updateOrderDTO;
import com.example.server.repository.OrderRepository;
import com.example.server.service.CustomerService;
import com.example.server.service.OrderService;

import org.springframework.data.domain.Page;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import entity.Cart;
import entity.Customer;
import entity.Order;
import entity.Product;
import lombok.RequiredArgsConstructor;
@Controller
@RequiredArgsConstructor
public class OrderController {
	private final OrderService orderService;
	private final CustomerService customerService;
	private final OrderRepository orderRepository;
	@PostMapping("/order")    // name값을 requestparam에 담아온다
    public String save(@RequestBody OrderDTO orderDTO) {
       
       String a = orderDTO.getAddress();
       String b= a.replace("\"", "");
       orderDTO.setAddress(a);
        orderService.save(orderDTO);

        return "redirect:/main2";
    }
	@PostMapping("/cart/order")    // name값을 requestparam에 담아온다
    public String cartsave(@RequestBody List<OrderDTO> orderDTO) {
       
       for(OrderDTO orderDTO1 : orderDTO) {
        orderService.save(orderDTO1);
       }
        return "redirect:/main2";
    }
	@PostMapping("/cancelOrder")    // name값을 requestparam에 담아온다
    public String cancelOrder(@RequestBody CacelOrderDTO cod) {
       orderService.delete(cod);
       
        return "redirect:/main2";
    }
	@GetMapping("/mypage")
	public String mypage(@RequestParam("customer") Long customerId, Model model) {
		Customer customer = customerService.findCustomerById(customerId);
	    List<Order> order = orderRepository.findByCustomer(customer);
	    model.addAttribute("customer", customer);
	    model.addAttribute("orders", order);
	    return "mypage";
	}
	@GetMapping("/viewOrders")
	public String viewOrders(Model model,@RequestParam(value="page", defaultValue="0") int page) {
		Page<Order> paging = this.orderService.getList(page);
        model.addAttribute("paging", paging);
	    return "order";
	}
	@PostMapping("/updateStatus")    // name값을 requestparam에 담아온다
    public void updateStatus(@RequestBody updateOrderDTO uod) {
       orderService.updateStatus(uod);
    }
	@GetMapping("/status")
    public String getcategoryProductss(@RequestParam("id") String status, Model model,@RequestParam(value="page", defaultValue="0") int page) {
    	if(status.equals("주문완료")) {
    		String status1="주문 완료";
    		Page<Order> paging = this.orderService.getStatusList(status1,page);
    		 model.addAttribute("paging", paging);
    		return "order_1";
    	}else if(status.equals("배송준비")){
    		String status1="배송 준비";
    		Page<Order> paging = this.orderService.getStatusList(status1,page);
   		 model.addAttribute("paging", paging);
   		return "order_2";
    	}else if(status.equals("배송중")){
    		String status1="배송 중";
    		Page<Order> paging = this.orderService.getStatusList(status1,page);
      		 model.addAttribute("paging", paging);
      		return "order_3";
    	}else if(status.equals("배송완료")){
    		String status1="배송 완료";
    		Page<Order> paging = this.orderService.getStatusList(status1,page);
      		 model.addAttribute("paging", paging);
      		return "order_4";
    	}else {
    		return "redirect:/main2";
    	}
    }
}
