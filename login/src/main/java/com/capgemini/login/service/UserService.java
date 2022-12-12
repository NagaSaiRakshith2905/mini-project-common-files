package com.capgemini.login.service;

import com.capgemini.login.pojo.UserPojo;

public interface UserService {

    String registerUser(UserPojo user);
    String loginUser(String value,String password);
}
