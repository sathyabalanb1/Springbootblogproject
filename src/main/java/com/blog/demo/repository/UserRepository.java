package com.blog.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.demo.entity.User;

public interface UserRepository extends JpaRepository<User,Long> {
	
	User findByEmail(String email);
}
