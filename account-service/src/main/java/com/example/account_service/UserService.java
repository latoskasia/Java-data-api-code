package com.example.account_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public void saveUser(User user){
        user.setPassword((passwordEncoder.encode(user.getPassword())));
        userRepository.save(user);
    }

    public String generateJwtToken(String email, String password){
        User user = userRepository.findByEmail(email);
        if (user!=null && passwordEncoder.matches(password, user.getPassword())){
            return jwtUtil.generateToken(email);
        }
        throw new RuntimeException("Invalid credentials");
    }

}
