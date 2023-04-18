package com.pru.token.app.user;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoleRepository extends MongoRepository<Role, String> {
	
	public Role findByName(String name);

}
