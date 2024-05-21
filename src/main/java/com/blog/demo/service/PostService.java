package com.blog.demo.service;

import java.util.List;

import com.blog.demo.dto.PostDto;

public interface PostService {
	
	List<PostDto> findAllPosts();
	
	void createPost(PostDto postDto);

}
