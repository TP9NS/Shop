package com.example.server.controller;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.server.DTOREUQUEST.CustomerDTO;
import com.example.server.DTOREUQUEST.findDTO;
import com.example.server.repository.CustomerRepository;
import com.example.server.service.CustomerService;

import entity.Customer;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LoginController {
	private final CustomerService customerService;
    @Autowired
    private CustomerRepository customerRepository; // Customer 엔티티를 다루는 Repository
    
    @GetMapping("/login")
    public String loginForm(){
        return "project";
    }
    @PostMapping("/login") // session : 로그인 유지
    public String login(@ModelAttribute CustomerDTO customerDTO, HttpSession session) {
    	CustomerDTO loginResult = customerService.login(customerDTO);
        if (loginResult != null) {
            // login 성공
            session.setAttribute("loginUsername", loginResult.getUsername());
            session.setAttribute("loginCustomerId", loginResult.getCustomerId());
            session.setAttribute("loginPostcode", loginResult.getPostcode());
            session.setAttribute("loginAddress", loginResult.getAddress());
            session.setAttribute("loginAddress_1", loginResult.getAddress_1());
            session.setAttribute("loginPermission",loginResult.getPermission());
            return "redirect:/main2";
        } else {
            
            return "project";
        }
    }
    @PostMapping("/signup")    // name값을 requestparam에 담아온다
    public String save(@ModelAttribute CustomerDTO customerDTO) {
        System.out.println("MemberController.save");
        System.out.println("memberDTO = " + customerDTO);
        System.out.println(customerDTO.getPostcode());
        customerDTO.setPermission(1);
       customerService.save(customerDTO);

        return "redirect:/main2";
    }
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // 세션에서 사용자 정보 삭제
        session.removeAttribute("loginUsername");
        session.removeAttribute("loginCustomerId");
        session.removeAttribute("loginAddress");
        session.removeAttribute("loginPermission");
        return "redirect:/main2";
    }
    @GetMapping("/findID")
    public String findID() {

        return "findID";
    }
    @GetMapping("/findPASS")
    public String findPASS() {

        return "findPASS";
    }
    @PostMapping("/findID")
    public ResponseEntity<Map<String, String>> findID(@RequestBody findDTO findDTO) {
        String id = customerService.findID(findDTO);

        Map<String, String> response = new HashMap<>();
        if (id != null) {
            response.put("id", id);
            response.put("message", "ID 찾기 성공");
        } else {
            response.put("message", "ID를 찾을 수 없습니다.");
        }

        return ResponseEntity.ok(response);
    }
    @PostMapping("/findPASS")
    public ResponseEntity<Map<String, String>> findPASS(@RequestBody findDTO findDTO) {
        String password = customerService.findPASS(findDTO);

        Map<String, String> response = new HashMap<>();
        if (password != null) {
            response.put("password", password);
            response.put("message", "PASSWORD 찾기 성공");
        } else {
            response.put("message", "PASSWORD 를 찾을 수 없습니다.");
        }

        return ResponseEntity.ok(response);
    }
    @PostMapping("/checkid")
    public ResponseEntity<Map<String, String>> checkid(@RequestBody findDTO findDTO) {
        boolean check = customerService.checkid(findDTO);
        String checkmessage = "중복입니다";
        if(check) {
        	checkmessage = "중복입니다";
        }else {
        	checkmessage = "중복이 아닙니다";
        }
        Map<String, String> response = new HashMap<>();
       
            response.put("check", checkmessage);
            response.put("message", "중복확인성공");
       

        return ResponseEntity.ok(response);
    }
}
