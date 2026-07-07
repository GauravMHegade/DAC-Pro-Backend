package com.quiz.quizapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.quiz.quizapp.entity.TestResult;

import java.util.List;

public interface ResultRepository extends JpaRepository<TestResult, Long> {

    List<TestResult> findByUserId(Long userId);
}