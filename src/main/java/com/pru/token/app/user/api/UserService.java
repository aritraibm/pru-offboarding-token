package com.pru.token.app.user.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.pru.token.app.user.User;
import com.pru.token.app.user.UserRepository;

@Service
public class UserService {
	@Autowired private UserRepository repo;
	@Autowired private PasswordEncoder passwordEncoder;
	
	public User save(User user) {
		String rawPassword = user.getPassword();
		String encodedPassword = passwordEncoder.encode(rawPassword);
		user.setPassword(encodedPassword);
		
		return repo.save(user);
	}
}
