package com.blog.demo.service.impl;

import java.util.Arrays;

import org.springframework.stereotype.Service;

import com.blog.demo.dto.RegistrationDto;
import com.blog.demo.entity.Role;
import com.blog.demo.entity.User;
import com.blog.demo.repository.RoleRepository;
import com.blog.demo.repository.UserRepository;
import com.blog.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	private UserRepository userRepository;
	private RoleRepository roleRepository;
	
	public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
	}
	
	@Override
	public void saveUser(RegistrationDto registrationDto) {
		User user = new User();
		user.setName(registrationDto.getFirstName()+""+registrationDto.getLastName());
		//user.setEmail(registrationDto.getEmail());
		user.setEmail(registrationDto.getEmail());
		user.setPassword(registrationDto.getPassword());
		
		Role role = roleRepository.findByName("ROLE_GUEST");
		
		user.setRoles(Arrays.asList(role));
		userRepository.save(user);
		
		
	}
	
	@Override
	public User findByEmail(String email) {
		
		return userRepository.findByEmail(email);
	}
}
