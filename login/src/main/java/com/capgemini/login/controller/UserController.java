package com.capgemini.login.controller;

import com.capgemini.login.pojo.UserPojo;
import com.capgemini.login.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/user/")
public class UserController {
    private final UserServiceImpl userServiceImpl;

    @PostMapping(path = "register/")
    public ResponseEntity<String> registerUser(@RequestBody UserPojo user){
        return new ResponseEntity<>(userServiceImpl.registerUser(user), HttpStatus.CREATED);
    }

    @GetMapping(path = "login/")
    public ResponseEntity<String> loginUser(@RequestParam(value = "value")String value,@RequestParam(value = "password")String password) {
        return new ResponseEntity<>(userServiceImpl.loginUser(value,password), HttpStatus.OK);
    }

}
