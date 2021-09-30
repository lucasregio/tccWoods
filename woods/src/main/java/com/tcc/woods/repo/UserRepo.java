package com.tcc.woods.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tcc.woods.model.User;


public interface UserRepo extends JpaRepository<User, Long>{
	void deleteUserById(Long id);

    Optional<User> findUserById(Long id);
}
