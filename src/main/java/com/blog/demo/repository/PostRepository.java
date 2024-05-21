package com.blog.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blog.demo.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long>{

	Optional<Post> findByUrl(String url);
}
