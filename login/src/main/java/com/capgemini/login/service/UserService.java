package com.capgemini.login.service;

import com.capgemini.login.pojo.UserRequest;
import com.capgemini.login.pojo.UserResponse;

public interface UserService {

    String registerUser(UserRequest user);
    String loginUser(String value,String password);

    UserResponse getUserByUserName(String username);

    UserResponse getUserById(int id);
}
