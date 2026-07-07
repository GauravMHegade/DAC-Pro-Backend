package com.quiz.quizapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.quiz.quizapp.entity.User;
import com.quiz.quizapp.service.UserService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    @Autowired
    private UserService service;

    @PostMapping("/register")
    public User register(@RequestBody User user){
        return service.register(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user){
        return service.login(user.getEmail(), user.getPassword());
    }

    @PostMapping("/google")
    public ResponseEntity<?> googleLogin(@RequestBody User user){
        return service.googleLogin(user);
    }
}