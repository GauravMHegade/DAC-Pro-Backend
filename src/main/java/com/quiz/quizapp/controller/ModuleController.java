package com.quiz.quizapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.quiz.quizapp.entity.Module;
import com.quiz.quizapp.repository.ModuleRepository;

import java.util.List;

@RestController
@RequestMapping("/api/modules")
@CrossOrigin
public class ModuleController {

    @Autowired
    private ModuleRepository repo;

    @GetMapping
    public List<Module> getModules(){
        return repo.findAll();
    }
}