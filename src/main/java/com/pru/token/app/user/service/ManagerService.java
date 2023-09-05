package com.pru.token.app.user.service;

import com.pru.token.app.user.Manager;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ManagerService {
    public Manager createManager(Manager manager);
    public Manager getManagerByEmpId(String empId);
    public Manager getManagerByEmail(String mail);
    public List<Manager> getAllManager();
}
