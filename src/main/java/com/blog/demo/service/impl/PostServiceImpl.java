package com.blog.demo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.blog.demo.dto.PostDto;
import com.blog.demo.entity.Post;
import com.blog.demo.mapper.PostMapper;
import com.blog.demo.repository.PostRepository;
import com.blog.demo.service.PostService;

@Service
public class PostServiceImpl implements PostService {
	
	private PostRepository postRepository;
	
	public PostServiceImpl(PostRepository postRepository) {
		this.postRepository = postRepository;
	}
	
	public List<PostDto> findAllPosts(){

		List<Post>posts = postRepository.findAll();
		
	//	return posts.stream().map((post) -> PostMapper.mapToPostDto(post))
	//			.collect(Collectors.toList());
		
		return posts.stream().map(PostMapper::mapToPostDto)
				.collect(Collectors.toList());
		/*
		we going to use list of posts to list of postdto using stream api

		stream provides a map method

		map method basically takes a functional interface

		we can use lambda expression to implement this functional function interface.

		(post) -> PostMapper.mapToPostDto(post)) lambda expression will convert post entity to postdto

		we can simply replace the lambda expression with method reference
		
		*/
	}
	
	@Override
	public void createPost(PostDto postDto) {
		Post post = PostMapper.mapToPost(postDto);
		postRepository.save(post);
	}
	
	@Override
	public PostDto findPostById(Long postId) {
		Post post = postRepository.findById(postId).get();
		return PostMapper.mapToPostDto(post);
	}
	
	@Override
	public void updatePost(PostDto postDto) {
		Post post = PostMapper.mapToPost(postDto);
		postRepository.save(post);
	}
	
	@Override
	public void deletePost(Long postId) {
		postRepository.deleteById(postId);
	}
}
