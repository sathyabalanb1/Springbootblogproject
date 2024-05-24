package com.blog.demo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.blog.demo.dto.CommentDto;
import com.blog.demo.entity.Comment;
import com.blog.demo.entity.Post;
import com.blog.demo.mapper.CommentMapper;
import com.blog.demo.repository.CommentRepository;
import com.blog.demo.repository.PostRepository;
import com.blog.demo.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
	
	private CommentRepository commentRepository;
	private PostRepository postRepository;
	
	public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository) {
		this.commentRepository=commentRepository;
		this.postRepository=postRepository;
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
	
	

}
