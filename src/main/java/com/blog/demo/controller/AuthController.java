package com.blog.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.blog.demo.dto.RegistrationDto;
import com.blog.demo.entity.User;
import com.blog.demo.service.UserService;

import jakarta.validation.Valid;

@Controller
public class AuthController {
	
	private UserService userService;
	
	public AuthController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
		RegistrationDto user = new RegistrationDto();
		model.addAttribute("user",user);
		return "register";
	}
	
	@PostMapping("/register/save")
	public String register(@Valid @ModelAttribute("user") RegistrationDto user,
				BindingResult result,
				Model model) {
		
		User existingUser = userService.findByEmail(user.getEmail());
		
		if(existingUser != null && existingUser.getEmail() !=null && !existingUser.getEmail().isEmpty()) {
			result.rejectValue("email", null,"There is already a user with same mail id");
		}
		
		if(result.hasErrors()) {
			model.addAttribute("user", user);
			return "register";
		}
		
		userService.saveUser(user);
		
		return "redirect:/register?success";
	}
	
	// Spring boot provides a spring-boot-starter-security starter that aggregates Spring Security related dependencies together.
	
	@GetMapping("/login")
	public String loginPage() {
		return "login";
	}
}
