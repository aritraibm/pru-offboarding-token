package com.pru.token.app.user.api;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pru.token.app.user.Manager;
import com.pru.token.app.user.ManagerRepository;
import com.pru.token.app.user.Reviewer;
import com.pru.token.app.user.ReviewerRepository;
import com.pru.token.app.user.Role;
import com.pru.token.app.user.RoleRepository;
import com.pru.token.app.user.User;
import com.pru.token.app.user.UserRepository;

@RestController
public class UserApi {

	@Autowired 
	private UserService service;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private ManagerRepository managerRepository;
	
	@Autowired
	private ReviewerRepository reviewerRepository;
	
	@PutMapping("/users")
	public ResponseEntity<?> createUser(@RequestBody @Valid User user) {
		User createdUser = service.save(user);
		URI uri = URI.create("/users/" + createdUser.getId());
		
		UserDTO userDto = new UserDTO(createdUser.getId(), createdUser.getEmail());
		
		return ResponseEntity.created(uri).body(userDto);
	}
	
	@PostMapping("/user_add")
	public ResponseEntity<?> addUser(@RequestBody RequestUser requestUser){
		Role rolei = roleRepository.findById(requestUser.getRoleId()).get();
		Manager manager = managerRepository.findByEmpId(requestUser.getManagerEmpId());
		Reviewer reviewer = reviewerRepository.findByEmpId(requestUser.getReviewerEmpId());
		
		User user=new User();
		user.setEmail(requestUser.getEmail());
		user.setRole(rolei);
		user.setEmployeeId(requestUser.getEmployeeId());
		user.setId(requestUser.getId());
		user.setManager(manager);
		user.setPassword(passwordEncoder.encode(requestUser.getPassword()));
		user.setReviewer(reviewer);
		user.setFirstName(requestUser.getFirstName());
		user.setLastName(requestUser.getLastName());
		userRepository.save(user);
		return ResponseEntity.ok(user);
	}
	
	@PostMapping("/manager_add")
	public ResponseEntity<?> createManager(@RequestBody Manager manager){
		managerRepository.save(manager);
		return ResponseEntity.ok(manager);
	}
	
	@PostMapping("/reviewer_add")
	public ResponseEntity<?> createManager(@RequestBody Reviewer reviewer){
		reviewerRepository.save(reviewer);
		return ResponseEntity.ok(reviewer);
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
		return managerRepository.findAll();
	}
	
	@GetMapping("/reviewers")
	public List<Reviewer> getReviewers(){
		return reviewerRepository.findAll();
	}
	
	@GetMapping("/users_get")
	public List<User> getUsers(){
		return userRepository.findAll();
	}
}
