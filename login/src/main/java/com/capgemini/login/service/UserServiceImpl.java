package com.capgemini.login.service;

import com.capgemini.login.exception.UserAlreadyExistsException;
import com.capgemini.login.exception.UserNotFoundException;
import com.capgemini.login.model.User;
import com.capgemini.login.pojo.UserPojo;
import com.capgemini.login.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public String registerUser(UserPojo user) {
        Optional<User> userNameExists = userRepository.findUserByUserName(user.getUserName());
        if (!userNameExists.isEmpty()) {
            throw new UserAlreadyExistsException("Username already exists");
        }
        Optional<User> userEmailExists = userRepository.findUserByEmail(user.getEmail());
        if (!userEmailExists.isEmpty()) {
            throw new UserAlreadyExistsException("Email already exists");
        }
        User userDetails = User.builder()
                .userName(user.getUserName())
                .email(user.getEmail())
                .password(user.getPassword()).build();
        userRepository.save(userDetails);
        return "User registration done successfully";
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
        } else if (!password.equals(userDetailsByUsername.get().getPassword())) {
            throw new UserNotFoundException("Wrong password");
        }
        return "Logged in successfully";
    }
}
