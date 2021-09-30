package com.tcc.woods.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tcc.woods.model.User;

public interface UserRepo extends JpaRepository<User, Long>{

}
