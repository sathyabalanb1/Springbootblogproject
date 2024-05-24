package com.blog.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.blog.demo.dto.RegistrationDto;

@Controller
public class AuthController {
	
	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
		RegistrationDto user = new RegistrationDto();
		model.addAttribute("user",user);
		return "register";
	}
	
	

}
