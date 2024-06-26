package com.blog.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.blog.demo.dto.CommentDto;
import com.blog.demo.dto.PostDto;
import com.blog.demo.service.PostService;

@Controller
public class BlogController {
	
	private PostService postService;
	
	public BlogController(PostService postService) {
		this.postService = postService;
	}
	
	@GetMapping("/")
	public String viewBlogPosts(Model model) {
		List<PostDto>postResponse = postService.findAllPosts();
		model.addAttribute("postResponse", postResponse);
		return "blog/view_posts";
		
	}
	
	@GetMapping("/post/{postUrl}")
	private String showPost(@PathVariable("postUrl")String postUrl,
			Model model) {
		
		PostDto post = postService.findPostByUrl(postUrl);
		CommentDto commentDto = new CommentDto();
		model.addAttribute("post", post);
		model.addAttribute("comment", commentDto);
		return "blog/blog_post";
	}
	
	@GetMapping("/page/search")
	public String searchPosts(@RequestParam(value="query") String query,
							Model model) {
		List<PostDto> postResponse = postService.searchPosts(query);
		
		model.addAttribute("postResponse", postResponse);
		return "blog/view_posts";
	}
	
}
