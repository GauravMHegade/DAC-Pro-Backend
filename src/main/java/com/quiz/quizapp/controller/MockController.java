package com.quiz.quizapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.quiz.quizapp.entity.Question;
import com.quiz.quizapp.repository.QuestionRepository;

@RestController
@RequestMapping("/api/mock")
@CrossOrigin(origins = "http://localhost:3000")
public class MockController {

    @Autowired
    private QuestionRepository repo;

    @GetMapping("/{moduleId}/{mockNo}")
    public List<Question> getMockQuestions(
            @PathVariable Long moduleId,
            @PathVariable int mockNo) {

        return repo.findByModuleIdAndMockNo(moduleId, mockNo);
    }
}