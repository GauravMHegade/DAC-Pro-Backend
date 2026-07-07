package com.quiz.quizapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.quiz.quizapp.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
}