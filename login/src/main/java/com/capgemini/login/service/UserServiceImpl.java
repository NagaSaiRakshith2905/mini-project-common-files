package com.capgemini.login.service;

import com.capgemini.login.exception.UserAlreadyExistsException;
import com.capgemini.login.exception.UserNotFoundException;
import com.capgemini.login.model.User;
import com.capgemini.login.pojo.UserRequest;
import com.capgemini.login.pojo.UserResponse;
import com.capgemini.login.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    
    public String registerUser(UserRequest user) {
        Optional<User> userNameExists = userRepository.findUserByUserName(user.getUserName());
        if (userNameExists.isPresent())
            throw new UserAlreadyExistsException("Username already exists");
        Optional<User> userEmailExists = userRepository.findUserByEmail(user.getEmail());
        if (userEmailExists.isPresent())
            throw new UserAlreadyExistsException("Email already exists");
        User userDetails = User.builder()
                .userName(user.getUserName())
                .email(user.getEmail())
                .password(user.getPassword()).build();
        User savedUser = userRepository.save(userDetails);
        return savedUser.getUserName();
    }
    public String loginUser(String value, String password) {
        Optional<User> userDetailsByUsername = userRepository.findUserByUserName(value);
        if (userDetailsByUsername.isEmpty()) {
            Optional<User> userDetailsByEmail = userRepository.findUserByEmail(value);
            if (userDetailsByEmail.isEmpty()) {
                throw new UserNotFoundException("Invalid email or username");
            } else if (!password.equals(userDetailsByEmail.get().getPassword())) {
                throw new UserNotFoundException("Wrong password");
            }
            return userDetailsByEmail.get().getUserName();
        } else if (!password.equals(userDetailsByUsername.get().getPassword())) {
            throw new UserNotFoundException("Wrong password");
        }
        return userDetailsByUsername.get().getUserName();
    }

    public UserResponse getUserByUserName(String username) {
        Optional<User> user = userRepository.findUserByUserName(username);
        if (user.isEmpty())
            throw new UserNotFoundException("Invalid username");
        return UserResponse.builder()
                .id(user.get().getId())
                .userName(user.get().getUserName())
                .email(user.get().getEmail())
                .build();
    }
    @Override
    public UserResponse getUserById(int id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty())
            throw new UserNotFoundException("Invalid ID");
        return UserResponse.builder()
                .id(user.get().getId())
                .userName(user.get().getUserName())
                .email(user.get().getEmail())
                .build();
    }

    @Transactional
    public String updatePassword(String email, String password) {
        Optional<User> userByEmail = userRepository.findUserByEmail(email);
        if (userByEmail.isEmpty())  {
            throw  new UserNotFoundException("Email doesn't exist");
        }
        if (!(password == "") && !password.equals(userByEmail.get().getPassword())){
            userByEmail.get().setPassword(password);
        }
        return "password Updated successFully";
    }

}