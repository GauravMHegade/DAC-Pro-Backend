package com.quiz.quizapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.quiz.quizapp.entity.Module;

public interface ModuleRepository extends JpaRepository<Module, Long> {

}