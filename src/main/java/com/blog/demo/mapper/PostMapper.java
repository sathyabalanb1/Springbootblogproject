package com.blog.demo.mapper;

import com.blog.demo.dto.PostDto;
import com.blog.demo.entity.Post;

public class PostMapper {
	
	//map Post entity to PostDto
	
	public static PostDto mapToPostDto(Post post) {
		return PostDto.builder()
							.id(post.getId())
							.title(post.getTitle())
							.url(post.getUrl())
							.content(post.getContent())
							.shortDescription(post.getShortDescription())
							.createdOn(post.getCreatedOn())
							.updatedOn(post.getUpdatedOn())
							.build();
	}
	
	// map Postdto to Post entity
	
	public static Post mapToPost(PostDto postDto) {
		
		return Post.builder()
				.id(postDto.getId())
				.title(postDto.getTitle())
				.content(postDto.getUrl())
				.shortDescription(postDto.getShortDescription())
				.createdOn(postDto.getCreatedOn())
				.updatedOn(postDto.getUpdatedOn())
				.build();
	}

}
