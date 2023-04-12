package com.pru.token.app.user;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReviewerRepository extends MongoRepository<Reviewer, String> {
	
	Reviewer findByEmpId(String empId);
	
	Reviewer findByReviewerName(String reviewerName);

}
