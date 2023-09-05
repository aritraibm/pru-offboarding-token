package com.pru.token.app.user;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReviewerRepository extends MongoRepository<Reviewer, String> {
	
	Reviewer findByEmployeeId(String employeeId);

	boolean existsByEmployeeId(String employeeId);

	boolean existsByEmail(String email);

	Reviewer findByFirstName(String firstName);

	Reviewer findByEmail(String email);
	
}
