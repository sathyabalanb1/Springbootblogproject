package com.blog.demo.service;

import java.util.List;

import com.blog.demo.dto.PostDto;

public interface PostService {
	
	List<PostDto> findAllPosts();
	
	void createPost(PostDto postDto);
	
	PostDto findPostById(Long posId);
	
	void updatePost(PostDto postDto);
	
	void deletePost(Long postId);
	
	PostDto findPostByUrl(String postUrl);
	
	List<PostDto> searchPosts(String query);

}
