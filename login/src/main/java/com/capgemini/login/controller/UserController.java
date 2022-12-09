package com.capgemini.login.controller;

import com.capgemini.login.pojo.UserPojo;
import com.capgemini.login.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/user/")
public class UserController {
    private final UserService userService;

    @PostMapping(path = "register")
    public ResponseEntity<String> registerUser(@RequestBody @Valid UserPojo user){
        return new ResponseEntity<>(userService.addUser(user), HttpStatus.CREATED);
    }

    @PostMapping(path = "login-by-username")
    public ResponseEntity<String> loginUserByUserName(@RequestBody UserPojo user) {
        return new ResponseEntity<>(userService.findUserByUserName(user), HttpStatus.OK);
    }
}
