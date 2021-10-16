package com.woods.repo;

import java.util.Optional;

import com.woods.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepo extends JpaRepository<User, Long> {
    void deleteEmployeeById(Long id);

    Optional<User> findEmployeeById(Long id);
}
