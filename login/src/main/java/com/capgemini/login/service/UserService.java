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
public class UserService {

    private final UserRepository userRepository;
    public String addUser(UserPojo user) {
        Optional<User> userNameExists = userRepository.findUserByUserName(user.getUserName());
        if(!userNameExists.isEmpty()){
           throw new UserAlreadyExistsException("Username already exists");
        }
        Optional<User> userEmailExists = userRepository.findUserByEmail(user.getEmail());
        if(!userEmailExists.isEmpty()){
            throw new UserAlreadyExistsException("Email already exists");
        }
        User userDetails = User.builder()
                .userName(user.getUserName())
                .email(user.getEmail()).password(user.getPassword()).build();
        userRepository.save(userDetails);
        return "User registration done successfully";
    }

    public String findUserByUserName(UserPojo user) {
        Optional<User> userDetails = userRepository.findUserByUserName(user.getUserName());
        if(userDetails.isEmpty()){
            throw new UserNotFoundException("Wrong credentials\n Invalid username or password");
        }
        else if(!user.getPassword().equals(userDetails.get().getPassword())){
            throw new UserNotFoundException("Wrong credentials\n Invalid username or password");
        }
        return "Logged in successfully";
    }
}
