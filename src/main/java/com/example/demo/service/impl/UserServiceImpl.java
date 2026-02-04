package com.example.demo.service.impl;

import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repo;

    @Override
    public User register(User user) {
        return repo.save(user);
    }

    @Override
    public User login(String mobile, String password) {
        return repo.findByMobileAndPassword(mobile, password);
    }
    
    @Override
    public boolean existsByMobile(String mobile) {
      
		return repo.findByMobile(mobile) != null;
    }

}
