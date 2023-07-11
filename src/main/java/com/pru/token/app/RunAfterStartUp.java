package com.pru.token.app;

import com.pru.token.app.user.*;
import com.pru.token.app.user.api.RequestUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

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
			roleRepository.save(new Role("ROLE_OFFBOARDING_REVIEWER"));
			roleRepository.save(new Role("ROLE_OFFBOARDING_MANAGER"));
			System.out.println("Roles created........");
		}
		if (managerRepository.findAll().size() < 1) {
			managerRepository.deleteAll();
			managerRepository.save(new Manager("Waseem","Hasan","manager1@abc.com","112233"));
			managerRepository.save(new Manager("N","A","non@ibm.com","000IBM"));
			System.out.println("Managers created........");
			Role role = roleRepository.findByName("ROLE_OFFBOARDING_MANAGER");
			managerRepository.findAll().forEach(m->{
				RequestUser requestUserManager = new RequestUser(m.getEmail(), m.getEmployeeId(), null, null,
						m.getFirstName(),m.getLastName(), m.getEmployeeId(), role.getId());
				User user = new User();
				user.setEmail(requestUserManager.getEmail());
				user.setRole(role);
				user.setEmployeeId(requestUserManager.getEmployeeId());
				user.setManager(null);
				user.setPassword(passwordEncoder.encode(requestUserManager.getPassword()));
				user.setReviewer(null);
				user.setFirstName(requestUserManager.getFirstName());
				user.setLastName(requestUserManager.getLastName());
				userRepository.save(user);
			});
			System.out.println("Manager user created........");
		}
		// Reviewers
		if (reviewerRepository.findAll().size() < 1) {
			reviewerRepository.deleteAll();
			reviewerRepository.save(new Reviewer("Arati"," Patil","reviewer1@abc.com","004MKL","987NJK"));
			reviewerRepository.save(new Reviewer("Aditi"," Kishore","reviewer2@abc.com","007NJU","987NJK"));
			reviewerRepository.save(new Reviewer("Manjula"," L","reviewer3@abc.com","008EDS","987NJK"));
			reviewerRepository.save(new Reviewer("N"," A","reviewer4@abc.com","000PLO","987NJK"));
			System.out.println("Reviewers created........");
			Role role = roleRepository.findByName("ROLE_OFFBOARDING_REVIEWER");
			Manager manager = managerRepository.findByFirstName("Waseem");
			reviewerRepository.findAll().forEach(r->{
				RequestUser requestUserReviewer = new RequestUser(r.getEmail(), r.getEmployeeId(), null, manager.getEmployeeId(),
						r.getFirstName(),r.getLastName(), r.getEmployeeId(), role.getId());
				User user = new User();
				user.setEmail(requestUserReviewer.getEmail());
				user.setRole(role);
				user.setEmployeeId(requestUserReviewer.getEmployeeId());
				user.setManager(manager);
				user.setPassword(passwordEncoder.encode(requestUserReviewer.getPassword()));
				user.setReviewer(null);
				user.setFirstName(requestUserReviewer.getFirstName());
				user.setLastName(requestUserReviewer.getLastName());
				userRepository.save(user);
			});
			System.out.println("Reviewer user created........");
		}
		// Managers


		//Associate
		if(!userRepository.findByEmail("associate@test.com").isPresent()) {
			Role role = roleRepository.findByName("ROLE_ASSOCIATE");
			Reviewer reviewer = reviewerRepository.findByFirstName("Arati");
			Manager manager = managerRepository.findByFirstName("Waseem");
			RequestUser requestUserAssociate = new RequestUser("associate@test.com", "123456", reviewer.getEmployeeId(), manager.getEmployeeId(),
					"AssocaiteTest","Asslname", "Aso123456", role.getId());
			User user = new User();
			user.setEmail(requestUserAssociate.getEmail());
			user.setRole(role);
			user.setEmployeeId(requestUserAssociate.getEmployeeId());
			user.setManager(manager);
			user.setPassword(passwordEncoder.encode(requestUserAssociate.getPassword()));
			user.setReviewer(reviewer);
			user.setFirstName(requestUserAssociate.getFirstName());
			user.setLastName(requestUserAssociate.getLastName());
			userRepository.save(user);
			System.out.println("Associate Role created........");
		}
	}

}