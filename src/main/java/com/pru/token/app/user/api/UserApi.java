package com.pru.token.app.user.api;

import com.pru.token.app.user.*;
import com.pru.token.app.user.service.ManagerService;
import com.pru.token.app.user.service.ReviewerService;
import com.pru.token.app.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

	@PreAuthorize("hasAnyRole({'ROLE_OFFBOARDING_REVIEWER','ROLE_OFFBOARDING_MANAGER'})")
	@PostMapping("/user_add")
	public ResponseEntity<?> addUser(@RequestBody RequestUser requestUser){
		User user = userService.createUser(requestUser);
		return ResponseEntity.ok(user);
	}
	
	@PreAuthorize("hasAnyRole({'ROLE_OFFBOARDING_REVIEWER','ROLE_OFFBOARDING_MANAGER'})")
	@PostMapping("/manager_add")
	public ResponseEntity<?> createManager(@RequestBody Manager manager){
		managerService.createManager(manager);
		return ResponseEntity.ok(manager);
	}
	
	@PreAuthorize("hasAnyRole({'ROLE_OFFBOARDING_REVIEWER','ROLE_OFFBOARDING_MANAGER'})")
	@PostMapping("/reviewer_add")
	public ResponseEntity<?> createManager(@RequestBody Reviewer reviewer){
		Reviewer reviewer1 = reviewerService.createReviewer(reviewer);
		return ResponseEntity.ok(reviewer1);
	}
	
	@PreAuthorize("hasAnyRole({'ROLE_OFFBOARDING_REVIEWER','ROLE_OFFBOARDING_MANAGER'})")
	@PostMapping("/role_add")
	public ResponseEntity<?> createRole(@RequestBody Role role){
		roleRepository.save(role);
		return ResponseEntity.ok(role);
	}
	
	@PreAuthorize("hasAnyRole({'ROLE_OFFBOARDING_REVIEWER','ROLE_OFFBOARDING_MANAGER'})")
	@GetMapping("/roles")
	public List<Role> getRoles(){
		return roleRepository.findAll();
	}
	
	@PreAuthorize("hasAnyRole({'ROLE_OFfBOARDING_REVIEWER','ROLE_OFFBOARDING_MANAGER'})")
	@GetMapping("/managers")
	public List<Manager> getManagers(){
		return managerService.getAllManager();
	}
	
	@PreAuthorize("hasAnyRole({'ROLE_OFFBOARDING_REVIEWER','ROLE_OFFBOARDING_MANAGER'})")
	@GetMapping("/reviewers")
	public List<Reviewer> getReviewers(){
		return reviewerService.getAllReviewers();
	}
	
	@PreAuthorize("hasAnyRole({'ROLE_OFFBOARDING_REVIEWER','ROLE_OFFBOARDING_MANAGER'})")
	@GetMapping("/users_get")
	public List<User> getUsers(){
		return userRepository.findAll();
	}
}
