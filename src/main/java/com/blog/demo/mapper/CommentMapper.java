package com.blog.demo.mapper;

import com.blog.demo.dto.CommentDto;
import com.blog.demo.entity.Comment;

public class CommentMapper {

	//convert comment entity to comment dto
	
	public static CommentDto mapToCommentDto(Comment comment) {
		return CommentDto.builder()
				.id(comment.getId())
				.name(comment.getName())
				.email(comment.getEmail())
				.content(comment.getContent())
				.createdOn(comment.getCreatedOn())
				.updatedOn(comment.getUpdateOn())
				.build();
	}
	
	// convert comment dto to comment entity
	
	public static Comment mapToComment(CommentDto commentDto) {
			
		return Comment.builder()
				.id(commentDto.getId())
				.name(commentDto.getName())
				.email(commentDto.getEmail())
				.content(commentDto.getContent())
				.createdOn(commentDto.getCreatedOn())
				.updateOn(commentDto.getUpdatedOn())
				.build();
			
	}
}
