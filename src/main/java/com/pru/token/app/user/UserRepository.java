package com.pru.token.app.user;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
//@EnableMongoRepositories
public interface UserRepository extends MongoRepository<User, String> {
	
	Optional<User> findByEmail(String email);
	
	Optional<User> findByEmployeeId(String employeeId);
	
	boolean existsByEmployeeId(String employeeId);

	boolean existsByEmail(String email);
	
}
