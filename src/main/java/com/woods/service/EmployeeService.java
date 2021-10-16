package com.woods.service;

import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import com.woods.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.woods.exception.UserNotFoundException;
import com.woods.repo.EmployeeRepo;

@Service
@Transactional
public class EmployeeService {
    private final EmployeeRepo employeeRepo;

    @Autowired
    public EmployeeService(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    public User addEmployee(User user) {
        user.setEmployeeCode(UUID.randomUUID().toString());
        return employeeRepo.save(user);
    }

    public List<User> findAllEmployees() {
        return employeeRepo.findAll();
    }

    public User updateEmployee(User user) {
        return employeeRepo.save(user);
    }

    public User findEmployeeById(Long id) {
        return employeeRepo.findEmployeeById(id)
                .orElseThrow(() -> new UserNotFoundException("User by id " + id + " was not found"));
    }

    public void deleteEmployee(Long id){
        employeeRepo.deleteEmployeeById(id);
    }
}
