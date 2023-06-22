package com.pru.token.app.user;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ManagerRepository extends MongoRepository<Manager, String> {

	Manager findByEmployeeId(String employeeId);

	boolean existsByEmployeeId(String employeeId);

	boolean existsByEmail(String email);
	
	Manager findByFirstName(String firstName);

	Manager findByEmail(String email);
}
