package com.blog.demo.service;

import com.blog.demo.dto.CommentDto;

public interface CommentService {
	
	void createComment(String postUrl,CommentDto commentDto);

}
