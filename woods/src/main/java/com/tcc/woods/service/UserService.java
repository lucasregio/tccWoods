package com.tcc.woods.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.tcc.woods.exception.UserNotFoundException;
import com.tcc.woods.model.User;
import com.tcc.woods.repo.UserRepo;



public class UserService {
	private final UserRepo userRepo;
	
	@Autowired
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }
	
	public User addUser(User user) {
        user.setUserCode(UUID.randomUUID().toString());
        return userRepo.save(user);
    }

    public List<User> findAllUsers() {
        return userRepo.findAll();
    }

    public User updateUser(User user) {
        return userRepo.save(user);
    }

    public User findUserById(Long id) {
        return userRepo.findUserById(id)
                .orElseThrow(() -> new UserNotFoundException("User by id " + id + " was not found"));
    }

    public void deleteUser(Long id){
        userRepo.deleteUserById(id);
    }
}
