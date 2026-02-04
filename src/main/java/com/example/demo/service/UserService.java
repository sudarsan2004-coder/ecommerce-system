package com.example.demo.service;

import com.example.demo.model.User;

public interface UserService {
    User register(User user);
    User login(String mobile, String password);
    public boolean existsByMobile(String mobile);

}

