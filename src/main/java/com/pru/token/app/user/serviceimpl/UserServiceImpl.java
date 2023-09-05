package com.pru.token.app.user.serviceimpl;

import com.pru.token.app.exception.EmployeeIdEmailExistException;
import com.pru.token.app.user.Role;
import com.pru.token.app.user.RoleRepository;
import com.pru.token.app.user.User;
import com.pru.token.app.user.UserRepository;
import com.pru.token.app.user.api.RequestUser;
import com.pru.token.app.user.service.ManagerService;
import com.pru.token.app.user.service.ReviewerService;
import com.pru.token.app.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ReviewerService reviewerService;
    @Autowired
    private ManagerService managerService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Override
    public User createUser(RequestUser requestUser) {
        System.out.println("requestUser : "+requestUser);
        Role role = roleRepository.findById(requestUser.getRoleId()).get();
        System.out.println("role "+role.getName());

        User user=new User();
        if (userRepository.existsByEmail(requestUser.getEmail())){
            throw new EmployeeIdEmailExistException("User with the same employee email already exists.");
        } else if (userRepository.existsByEmployeeId(requestUser.getEmployeeId())){
            throw new EmployeeIdEmailExistException("User with the same employee id already exists.");
        }
        user.setEmail(requestUser.getEmail());
        user.setEmployeeId(requestUser.getEmployeeId());
        user.setRole(role);
        user.setPassword(passwordEncoder.encode(requestUser.getPassword()));
        if (role.getName().equals("ROLE_ASSOCIATE")){
            user.setReviewer(reviewerService.getReviewerByEmpId(requestUser.getReviewerEmpId()));
            user.setManager(managerService.getManagerByEmpId(requestUser.getManagerEmpId()));
        } else if (role.getName().equals("ROLE_OFFBOARDING_REVIEWER")) {
            user.setManager(managerService.getManagerByEmpId(requestUser.getManagerEmpId()));
        }
//        user.setName(requestUser.getFirstName()+" "+requestUser.getLastName());
        user.setFirstName(requestUser.getFirstName());
		user.setLastName(requestUser.getLastName());
        
        userRepository.save(user);
        return user;
    }
}
