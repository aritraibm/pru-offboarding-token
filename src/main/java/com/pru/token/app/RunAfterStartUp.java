package com.pru.token.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.pru.token.app.user.Manager;
import com.pru.token.app.user.ManagerRepository;
import com.pru.token.app.user.Reviewer;
import com.pru.token.app.user.ReviewerRepository;
import com.pru.token.app.user.Role;
import com.pru.token.app.user.RoleRepository;
import com.pru.token.app.user.User;
import com.pru.token.app.user.UserRepository;
import com.pru.token.app.user.api.RequestUser;

@Component
public class RunAfterStartUp {

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	ReviewerRepository reviewerRepository;

	@Autowired
	ManagerRepository managerRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@EventListener(ApplicationReadyEvent.class)
	public void runAfterStartup() {
		// Roles
		if (roleRepository.findAll().size() < 1) {
			roleRepository.deleteAll();
			roleRepository.save(new Role("ROLE_ASSOCIATE"));
			roleRepository.save(new Role("ROLE_ONBOARDING_REVIEWER"));
			roleRepository.save(new Role("ROLE_ONBOARDING_MANAGER"));
			System.out.println("Roles created........");
		}
		// Reviewers
		if (reviewerRepository.findAll().size() < 1) {
			reviewerRepository.deleteAll();
			reviewerRepository.save(new Reviewer("Ushakiran S"));
			reviewerRepository.save(new Reviewer("Arati Patil"));
			reviewerRepository.save(new Reviewer("Aditi Kishore"));
			reviewerRepository.save(new Reviewer("Manjula L"));
			reviewerRepository.save(new Reviewer("Pravalika Puchakayala"));
			reviewerRepository.save(new Reviewer("N/A"));
			System.out.println("Reviewers created........");
		}
		// Managers
		if (managerRepository.findAll().size() < 1) {
			managerRepository.deleteAll();
			managerRepository.save(new Manager("Waseem Hasan"));
			managerRepository.save(new Manager("N/A"));
			System.out.println("Managers created........");
		}
		// Manager user
		if(!userRepository.findByEmail("Dudekula.Shamshad@ibm.com").isPresent()) {
			Role role = roleRepository.findByName("ROLE_ONBOARDING_MANAGER");
			Reviewer reviewer = reviewerRepository.findByReviewerName("N/A");
			Manager manager = managerRepository.findByManagerName("N/A");
			RequestUser requestUserManager = new RequestUser("Dudekula.Shamshad@ibm.com", "987654", reviewer.getEmpId(), manager.getEmpId(),
					"ManagerTest", "Man123456", role.getId());
			User user = new User();
			user.setEmail(requestUserManager.getEmail());
			user.setRole(role);
			user.setEmployeeId(requestUserManager.getEmployeeId());
			user.setId(requestUserManager.getId());
			user.setManager(manager);
			user.setPassword(passwordEncoder.encode(requestUserManager.getPassword()));
			user.setReviewer(reviewer);
			user.setUserName(requestUserManager.getUserName());
			userRepository.save(user);
			System.out.println("Manager Role created........");
		}
		//Reviewer user
		if(!userRepository.findByEmail("reviewer@test.com").isPresent()) {
			Role role = roleRepository.findByName("ROLE_ONBOARDING_REVIEWER");
			Reviewer reviewer = reviewerRepository.findByReviewerName("N/A");
			Manager manager = managerRepository.findByManagerName("Waseem Hasan");
			RequestUser requestUserManager = new RequestUser("reviewer@test.com", "567890", reviewer.getEmpId(), manager.getEmpId(),
					"ReviewerTest", "Rev123456", role.getId());
			User user = new User();
			user.setEmail(requestUserManager.getEmail());
			user.setRole(role);
			user.setEmployeeId(requestUserManager.getEmployeeId());
			user.setId(requestUserManager.getId());
			user.setManager(manager);
			user.setPassword(passwordEncoder.encode(requestUserManager.getPassword()));
			user.setReviewer(reviewer);
			user.setUserName(requestUserManager.getUserName());
			userRepository.save(user);
			System.out.println("Reviewer Role created........");
		}
		//Associate
		if(!userRepository.findByEmail("associate@test.com").isPresent()) {
			Role role = roleRepository.findByName("ROLE_ASSOCIATE");
			Reviewer reviewer = reviewerRepository.findByReviewerName("Arati Patil");
			Manager manager = managerRepository.findByManagerName("Waseem Hasan");
			RequestUser requestUserManager = new RequestUser("associate@test.com", "123456", reviewer.getEmpId(), manager.getEmpId(),
					"AssocaiteTest", "Aso123456", role.getId());
			User user = new User();
			user.setEmail(requestUserManager.getEmail());
			user.setRole(role);
			user.setEmployeeId(requestUserManager.getEmployeeId());
			user.setId(requestUserManager.getId());
			user.setManager(manager);
			user.setPassword(passwordEncoder.encode(requestUserManager.getPassword()));
			user.setReviewer(reviewer);
			user.setUserName(requestUserManager.getUserName());
			userRepository.save(user);
			System.out.println("Associate Role created........");
		}
	}

}