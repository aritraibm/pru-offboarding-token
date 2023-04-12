package com.pru.token.app.user;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface LogoutUserTokenRepository extends MongoRepository<LogoutUserToken, String> {
	
	Optional<LogoutUserToken> findByToken(String token);
	
	Optional<LogoutUserToken> findByEmpId(String empId);
}
