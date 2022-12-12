package com.capgemini.login.controller;

import com.capgemini.login.pojo.UserPojo;
import com.capgemini.login.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/user/")
public class UserController {
    private final UserService userService;

    @PostMapping(path = "register")
    public ResponseEntity<String> registerUser(@RequestBody @Valid UserPojo user){
        return new ResponseEntity<>(userService.registerUser(user), HttpStatus.CREATED);
    }

    @GetMapping(path = "login")
    public ResponseEntity<String> loginUser(@RequestBody UserPojo user) {
        return new ResponseEntity<>(userService.loginUser(user), HttpStatus.OK);
    }

}
