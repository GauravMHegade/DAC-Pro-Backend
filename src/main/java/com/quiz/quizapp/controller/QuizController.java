package com.quiz.quizapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.quiz.quizapp.entity.Question;
import com.quiz.quizapp.repository.QuestionRepository;

@RestController
@RequestMapping("/api/quiz")
@CrossOrigin(origins = "http://localhost:3000")
public class QuizController {

    @Autowired
    private QuestionRepository repo;

    // Practice Test (after login)
    @GetMapping("/{moduleId}")
    public List<Question> getQuestions(@PathVariable Long moduleId){

        return repo.findRandomQuestions(moduleId);

    }

    // Free demo test (before login)
    @GetMapping("/demo/{moduleId}")
    public List<Question> demoPractice(@PathVariable Long moduleId){

        return repo.findDemoQuestions(moduleId);

    }
}