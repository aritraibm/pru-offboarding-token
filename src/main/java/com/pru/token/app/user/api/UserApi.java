package com.pru.token.app.user.api;

import com.pru.token.app.user.*;
import com.pru.token.app.user.service.ManagerService;
import com.pru.token.app.user.service.ReviewerService;
import com.pru.token.app.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/user")
public class UserApi {

	@Autowired 
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private ManagerService managerService;

	@Autowired
	private ReviewerService reviewerService;

	@PostMapping("/user_add")
	public ResponseEntity<?> addUser(@RequestBody RequestUser requestUser){
		User user = userService.createUser(requestUser);
		return ResponseEntity.ok(user);
	}
	
	@PostMapping("/manager_add")
	public ResponseEntity<?> createManager(@RequestBody Manager manager){
		managerService.createManager(manager);
		return ResponseEntity.ok(manager);
	}
	
	@PostMapping("/reviewer_add")
	public ResponseEntity<?> createManager(@RequestBody Reviewer reviewer){
		Reviewer reviewer1 = reviewerService.createReviewer(reviewer);
		return ResponseEntity.ok(reviewer1);
	}
	
	@PostMapping("/role_add")
	public ResponseEntity<?> createRole(@RequestBody Role role){
		roleRepository.save(role);
		return ResponseEntity.ok(role);
	}
	
	@GetMapping("/roles")
	public List<Role> getRoles(){
		return roleRepository.findAll();
	}
	
	@GetMapping("/managers")
	public List<Manager> getManagers(){
		return managerService.getAllManager();
	}
	
	@GetMapping("/reviewers")
	public List<Reviewer> getReviewers(){
		return reviewerService.getAllReviewers();
	}
	
	@GetMapping("/users_get")
	public List<User> getUsers(){
		return userRepository.findAll();
	}
}
