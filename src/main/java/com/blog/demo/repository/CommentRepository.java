package com.blog.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.demo.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long>{

}
