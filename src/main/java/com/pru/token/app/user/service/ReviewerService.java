package com.pru.token.app.user.service;

import com.pru.token.app.user.Reviewer;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ReviewerService {
    public Reviewer createReviewer(Reviewer Reviewer);
    public Reviewer getReviewerByEmpId(String empId);
    public Reviewer getReviewerByEmail(String mail);
    public List<Reviewer> getAllReviewers();
}
