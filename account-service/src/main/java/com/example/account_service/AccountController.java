package com.example.account_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {
    @Autowired
    private UserService userService;

//    @PostMapping("/register")
//    public String register(@RequestBody User user){
//        System.out.println("Rejestracja uzytkownika: " + user.getName() + ", "+ user.getEmail() + ", "+ user.getPassword());
//        userService.saveUser(user);
//        return "User registered succesfully";
//    }
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user){
        System.out.println("Registering user: " + user.getName() + ", "+ user.getEmail() + ", "+ user.getPassword());
        userService.saveUser(user);
        return ResponseEntity.ok("User registered succesfully");
}

    @PostMapping("/token")
    public String generateToken(@RequestBody AuthRequest authRequest){
        return userService.generateJwtToken(authRequest.getEmail(), authRequest.getPassword());
    }

}
