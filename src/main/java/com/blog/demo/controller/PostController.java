package com.blog.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.blog.demo.dto.PostDto;
import com.blog.demo.service.PostService;
@Controller
public class PostController {
	
	private PostService postService;
//	spring team recommend to use interface for injecting the dependency to achieve loose coupling
	
	public PostController(PostService postService) {
		this.postService = postService;
	}
	
	@GetMapping("/admin/posts")
	public String posts(Model model) {
		List<PostDto> posts = postService.findAllPosts();
		model.addAttribute("posts", posts);
		return "/admin/posts";
		/*
		There are two ways of adding bootstrap

		1. Using Bootstrap remote client links
		2. download the bootstrap library from the official website, i can add that library to our project and then we can provide a reference link to that bootstrap library

		There are three types of bootstrap container classes

		1. container
		2. container fluid
		3. container-custom
		*/
		/*
		Thymeleaf layout

		1. Include-style layouts
		2. Hierarchical-style layouts
		*/
		
		/*
		 Bootstrap
		 Bootstrap 5 navbar
		 */
		 
	}
	
	@GetMapping("/admin/posts/newpost")
	public String newPostForm(Model model) {

		PostDto postDto = new PostDto();
		
		model.addAttribute("post", postDto);
		
		return "admin/create_post";

	}
	
	@PostMapping("/admin/posts")
	public String createPost(@ModelAttribute PostDto postDto) {
		postDto.setUrl(getUrl(postDto.getTitle()));
		postService.createPost(postDto);
		return "redirect:/admin/posts";
	}
	
	private static String getUrl (String postTitle) {
		// OOPS Concepts Explained in Java
		// oops-concepts-explained-in-java
		
		String title = postTitle.trim().toLowerCase();
		String url = title.replaceAll("\\s+","-");
		url = url.replaceAll("[^A-Za-z0-9]", "-");
		return url;
		
	}

}
