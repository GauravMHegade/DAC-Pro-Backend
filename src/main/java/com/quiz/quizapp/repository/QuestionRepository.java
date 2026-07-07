package com.quiz.quizapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.quiz.quizapp.entity.Question;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    List<Question> findByModuleId(Long moduleId);
    
    @Query(value = "SELECT * FROM questions WHERE module_id=?1 AND mock_no=?2", nativeQuery = true)
    List<Question> findMockQuestions(Long moduleId, int mockNo);
    
    @Query(value = "SELECT * FROM questions WHERE module_id=?1 ORDER BY RAND() LIMIT 20", nativeQuery = true)
    List<Question> findRandomQuestions(Long moduleId);

    @Query(value = "SELECT * FROM questions WHERE module_id=?1 LIMIT 5", nativeQuery = true)
    List<Question> findDemoQuestions(Long moduleId);
    
    
    List<Question> findByModuleIdAndMockNo(Long moduleId, int mockNo);
}