package com.quiz.quizapp.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.quiz.quizapp.security.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.quiz.quizapp.entity.User;
import com.quiz.quizapp.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    @Autowired
    private JwtService jwtService;


    @Autowired
    private PasswordEncoder passwordEncoder;

    public User register(User user) {

        if (repo.findByEmail(user.getEmail()) != null) {
            throw new RuntimeException("Email already exists");
        }

        if (user.getRole() == null || user.getRole().isBlank()) {
            user.setRole("USER");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return repo.save(user);
    }



    public ResponseEntity<?> login(String email, String password) {

        User user = repo.findByEmail(email);

        if (user == null) {
            return ResponseEntity.status(404).body("Invalid email address");
        }

        if (!passwordEncoder.matches(password, user.getPassword())) {
            return ResponseEntity.status(401).body("Incorrect password");
        }

        String token = jwtService.generateToken(user);

        Map<String, Object> res = new HashMap<>();
        res.put("token", token);
        res.put("user", user);
        res.put("role", user.getRole());

        return ResponseEntity.ok(res);
    }

    public ResponseEntity<?> googleLogin(User googleUser) {

        User user = repo.findByEmail(googleUser.getEmail());

        if (user == null) {

            googleUser.setPassword(passwordEncoder.encode("GOOGLE_USER"));
            googleUser.setRole("USER");

            user = repo.save(googleUser);
        }

        String token = jwtService.generateToken(user);

        Map<String, Object> res = new HashMap<>();
        res.put("token", token);
        res.put("user", user);
        res.put("role", user.getRole());

        return ResponseEntity.ok(res);
    }
}
