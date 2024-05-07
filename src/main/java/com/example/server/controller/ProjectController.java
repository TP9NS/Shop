package com.example.server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProjectController {
	
    
    @GetMapping("/main")
    public String main(Model model) {
        model.addAttribute("message", "프로젝트 페이지입니다.");
        return "main";  // 뷰의 이름을 "project"로 설정
    }
    
    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("message", "프로젝트 페이지입니다.");
        return "signup";  // 뷰의 이름을 "project"로 설정
    }
}