package com.blog.demo.service;

import com.blog.demo.dto.RegistrationDto;
import com.blog.demo.entity.User;

public interface UserService {
	
	void saveUser(RegistrationDto registrationDto);
	
	User findByEmail(String email);

}
