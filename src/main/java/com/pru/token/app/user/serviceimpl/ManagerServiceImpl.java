package com.pru.token.app.user.serviceimpl;

import com.pru.token.app.exception.EmployeeIdEmailExistException;
import com.pru.token.app.user.Manager;
import com.pru.token.app.user.ManagerRepository;
import com.pru.token.app.user.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class ManagerServiceImpl implements ManagerService {
    @Autowired
    private ManagerRepository managerRepository;
    @Override
    public Manager createManager(Manager manager) {
        if (managerRepository.existsByEmployeeId(manager.getEmployeeId())){
            throw new EmployeeIdEmailExistException("User with the same employee id already exists.");
        } else if (managerRepository.existsByEmail(manager.getEmail())) {
            throw new EmployeeIdEmailExistException("User with the same employee email already exists.");
        }
        return managerRepository.save(manager);
    }

    @Override
    public Manager getManagerByEmpId(String empId) {
        if (!managerRepository.existsByEmployeeId(empId)){
            throw new EmployeeIdEmailExistException("User with the "+empId+" employee id not exists.");
        }
        return managerRepository.findByEmployeeId(empId);
    }

    @Override
    public Manager getManagerByEmail(String mail) {
        if (!managerRepository.existsByEmail(mail)) {
            throw new EmployeeIdEmailExistException("User with the "+mail+" email not exists.");
        }
        return managerRepository.findByEmail(mail);
    }

    @Override
    public List<Manager> getAllManager() {
        return managerRepository.findAll();
    }
}
