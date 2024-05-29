package com.blog.demo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.blog.demo.dto.CommentDto;
import com.blog.demo.entity.Comment;
import com.blog.demo.entity.Post;
import com.blog.demo.entity.User;
import com.blog.demo.mapper.CommentMapper;
import com.blog.demo.repository.CommentRepository;
import com.blog.demo.repository.PostRepository;
import com.blog.demo.repository.UserRepository;
import com.blog.demo.service.CommentService;
import com.blog.demo.util.SecurityUtils;

@Service
public class CommentServiceImpl implements CommentService {
	
	private CommentRepository commentRepository;
	private PostRepository postRepository;
	private UserRepository userRepository;
	
	public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository,UserRepository userRepository) {
		this.commentRepository=commentRepository;
		this.postRepository=postRepository;
		this.userRepository=userRepository;
	}
	
	@Override
	public void createComment(String postUrl,CommentDto commentDto) {
		
		Post post = postRepository.findByUrl(postUrl).get();
		
		Comment comment = CommentMapper.mapToComment(commentDto);
		comment.setPost(post);
		commentRepository.save(comment);
	}
	
	@Override
	public List<CommentDto> findAllComments(){
		
		List<Comment>comments = commentRepository.findAll();
		
		return comments.stream()
				.map(CommentMapper::mapToCommentDto)
				.collect(Collectors.toList());
	}
	
	@Override
	public void deleteComment(Long commentId) {
		
		commentRepository.deleteById(commentId);
	}

	@Override
	public List<CommentDto> findCommentsByPost() {
		// TODO Auto-generated method stub
		
		String email = SecurityUtils.getCurrentUser().getUsername();
		User createdBy = userRepository.findByEmail(email);
		Long userId = createdBy.getId();
		List<Comment>comments = commentRepository.findCommentsByPost(userId);
		return comments.stream()
				.map((comment) -> CommentMapper.mapToCommentDto(comment))
				.collect(Collectors.toList());
		
	}
	
	

}
