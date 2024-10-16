package com.example.account_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
public class AccountController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String register(@RequestBody User user){
        userService.saveUser(user);
        return "User registered succesfully";
    }

    @PostMapping("/token")
    public String generateToken(@RequestBody AuthRequest authRequest){
        return userService.generateJwtToken(authRequest.getEmail(), authRequest.getPassword());
    }

}
