package com.blog.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.blog.demo.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long>{
	
	@Query(value="select c.* from comments c inner join posts p \n"+
			"where c.post_id=p.id and p.created_by =:userId", nativeQuery=true)
	List<Comment> findCommentsByPost(Long userId);

}
