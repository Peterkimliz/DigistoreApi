package com.example.digishopapi.controllers;

import com.example.digishopapi.dtos.LoginDto;
import com.example.digishopapi.dtos.LoginResponse;
import com.example.digishopapi.dtos.UserDto;
import com.example.digishopapi.models.User;
import com.example.digishopapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
public class AuthController {
    @Autowired
    UserService userService;
    @PostMapping("signup")
    public ResponseEntity<User> createUser(@RequestBody @Validated  UserDto userDto){
        return new ResponseEntity<User>(userService.createUser(userDto), HttpStatus.CREATED);

    }
    @PostMapping("signin")
    public ResponseEntity<LoginResponse> loginUser(@RequestBody @Validated LoginDto loginDto){
       return new ResponseEntity<LoginResponse>(userService.loginUser(loginDto),HttpStatus.OK);

    }

}
