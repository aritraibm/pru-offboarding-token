package com.pru.token.app.user.serviceimpl;

import com.pru.token.app.exception.EmployeeIdEmailExistException;
import com.pru.token.app.user.Reviewer;
import com.pru.token.app.user.ReviewerRepository;
import com.pru.token.app.user.service.ReviewerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class ReviewerServiceImpl implements ReviewerService {
    @Autowired
    private ReviewerRepository reviewerRepository;
    @Override
    public Reviewer createReviewer(Reviewer reviewer) {
        if (reviewerRepository.existsByEmployeeId(reviewer.getEmployeeId())){
            throw new EmployeeIdEmailExistException("User with the same employee id already exists.");
        } else if (reviewerRepository.existsByEmail(reviewer.getEmail())) {
            throw new EmployeeIdEmailExistException("User with the same employee email already exists.");
        }
        return reviewerRepository.save(reviewer);
    }

    @Override
    public Reviewer getReviewerByEmpId(String empId) {
        if (!reviewerRepository.existsByEmployeeId(empId)){
            throw new EmployeeIdEmailExistException("User with the "+empId+" employee id not exists.");
        }
        return reviewerRepository.findByEmployeeId(empId);
    }

    @Override
    public Reviewer getReviewerByEmail(String mail) {
        if (!reviewerRepository.existsByEmail(mail)) {
            throw new EmployeeIdEmailExistException("User with the "+mail+" email not exists.");
        }
        return reviewerRepository.findByEmail(mail);
    }

    @Override
    public List<Reviewer> getAllReviewers() {
        return reviewerRepository.findAll();
    }
}
