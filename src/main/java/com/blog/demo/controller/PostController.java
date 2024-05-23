package com.blog.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.blog.demo.dto.PostDto;
import com.blog.demo.service.PostService;

import jakarta.validation.Valid;
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
	public String createPost(@Valid @ModelAttribute("post") PostDto postDto,
			BindingResult result,
			Model model) {
		
		if(result.hasErrors()) {
			model.addAttribute("post", postDto);
			return "admin/create_post";
		}
		
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
	/*
	spring-boot-starter-validation

	whenever you want to implement validation in your spring boot project, then make sure that you use spring-boot-starter-validation dependency.

	Well, this starter dependency internally provides bean validation library with hibernate validator as its implementation.

	Well, java basically provides a bean validation standard library and it is just a specification and hibernate validator is the implementation of bean validation library.

	Well, spring boot starter validation dependency provides all the libraries all the libraries that are required to implement a validation.

	if you go inside a spring boot starter validation dependency, it internally provides hibernate validator dependency.

	hibernate validation dependency implements bean validation API.

	jakarta validation api is a standard java api for validation and hibernate validator is a implementation of jakarta validation API.


	1. We have to add spring-boot-starter-validation
	2. we need to add validation annotations to java bean
	3. We need to enable validation in a handler method
	4. use BindingResult to check errors and Return to UI
	5. We need to add the error message in the form
	
	*/
	@GetMapping("/admin/posts/{postId}/edit")
	public String editPostForm(@PathVariable("postId") Long postId,
			Model model) {
		
		PostDto postDto = postService.findPostById(postId);
		model.addAttribute("post", postDto);
		return "admin/edit_post";
	}
	
	@PostMapping("/admin/posts/{postId}")
	public String updatePost(@PathVariable("postId") Long postId,
		@Valid	@ModelAttribute("post") PostDto post,
		BindingResult result,
		Model model) {
		
		if(result.hasErrors()) {
			model.addAttribute("post",post);
			return "admin/edit_post";
		}
		
		post.setId(postId);
		postService.updatePost(post);
		return "redirect:/admin/posts";
	}
	
	@GetMapping("/admin/posts/{postId}/delete")
	public String deletePost(@PathVariable("postId") Long postId) {
	
		postService.deletePost(postId);
		return "redirect:/admin/posts";
	}
	
	@GetMapping("/admin/posts/{postUrl}/view")
	public String viewPost(@PathVariable("postUrl") String postUrl,
			Model model) {
	
		PostDto postDto = postService.findPostByUrl(postUrl);
		model.addAttribute("post",postDto);
		return "admin/view_post";
		
		
	}
	
	// localhost:8080/admin/posts/search?query=java
	
	@GetMapping("/admin/posts/search")
	public String searchPosts(@RequestParam(value= "query") String query, Model model) {
		
		List<PostDto> posts = postService.searchPosts(query);
		
		model.addAttribute("posts", posts);
		return "admin/posts";
	}




}
