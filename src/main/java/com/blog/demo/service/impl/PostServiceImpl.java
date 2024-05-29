package com.blog.demo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.stereotype.Service;

import com.blog.demo.dto.PostDto;
import com.blog.demo.entity.Post;
import com.blog.demo.entity.User;
import com.blog.demo.mapper.PostMapper;
import com.blog.demo.repository.PostRepository;
import com.blog.demo.repository.UserRepository;
import com.blog.demo.service.PostService;
import com.blog.demo.util.SecurityUtils;

@Service
public class PostServiceImpl implements PostService {
	
	private PostRepository postRepository;
	
	private UserRepository userRepository;
	
	public PostServiceImpl(PostRepository postRepository,
							UserRepository userRepository) {
		this.postRepository = postRepository;
		this.userRepository = userRepository;
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
		
		String email = SecurityUtils.getCurrentUser().getUsername();
		User user = userRepository.findByEmail(email);
		Post post = PostMapper.mapToPost(postDto);
		post.setCreatedBy(user);
		postRepository.save(post);
	}
	
	@Override
	public PostDto findPostById(Long postId) {
		Post post = postRepository.findById(postId).get();
		return PostMapper.mapToPostDto(post);
	}
	
	@Override
	public void updatePost(PostDto postDto) {
		
		String email = SecurityUtils.getCurrentUser().getUsername();
		User createdBy = userRepository.findByEmail(email);
		Post post = PostMapper.mapToPost(postDto);
		post.setCreatedBy(createdBy);
		postRepository.save(post);
	}
	
	@Override
	public void deletePost(Long postId) {
		postRepository.deleteById(postId);
	}
	
	@Override
	public PostDto findPostByUrl(String postUrl) {
		Post post = postRepository.findByUrl(postUrl).get();
		return PostMapper.mapToPostDto(post);
	}
	
	@Override
	public List<PostDto> searchPosts(String query){
		List<Post>posts = postRepository.searchPosts(query);
		return posts.stream().map(PostMapper::mapToPostDto)
				.collect(Collectors.toList());
	}

	@Override
	public List<PostDto> findPostsByUser() {
		
		String email = SecurityUtils.getCurrentUser().getUsername();
		User createdBy = userRepository.findByEmail(email);
		Long userId = createdBy.getId();
		List<Post>posts = postRepository.findPostsByUser(userId);
		return posts.stream()
				.map((post) -> PostMapper.mapToPostDto(post))
				.collect(Collectors.toList());
		
		/*
		So post this basic list, it has a stream method and then let's call its map method.

		All right, and let's use the lambda expression to implement function interface.

		So let's have a object that is post jpa entity object and then lambda symbol and let's call post mapper

		 it has mapToPostDto method and then pass post object as parameter

		And finally let's call collect method to collect the result.
		*/
	}
}
