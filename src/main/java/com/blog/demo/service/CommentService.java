package com.blog.demo.service;

import java.util.List;

import com.blog.demo.dto.CommentDto;

public interface CommentService {
	
	void createComment(String postUrl,CommentDto commentDto);

	List<CommentDto> findAllComments();
	
	void deleteComment(Long commentId);
	
	List<CommentDto> findCommentsByPost();

}
