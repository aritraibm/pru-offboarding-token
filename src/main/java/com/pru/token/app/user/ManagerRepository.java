package com.pru.token.app.user;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ManagerRepository extends MongoRepository<Manager, String> {

	Manager findByEmpId(String empId);
	
	Manager findByManagerName(String managerName);

}
