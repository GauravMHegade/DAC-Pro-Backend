package com.quiz.quizapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.quiz.quizapp.entity.Module;
import com.quiz.quizapp.entity.Question;
import com.quiz.quizapp.entity.TestResult;
import com.quiz.quizapp.entity.User;
import com.quiz.quizapp.repository.ModuleRepository;
import com.quiz.quizapp.repository.QuestionRepository;
import com.quiz.quizapp.repository.ResultRepository;
import com.quiz.quizapp.repository.UserRepository;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "http://localhost:3000")
public class AdminController {

    @Autowired
    private ModuleRepository moduleRepo;

    @Autowired
    private QuestionRepository questionRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private ResultRepository resultRepo;

    // ==================== HELPER ====================

    private ResponseEntity<String> forbidden() {
        return ResponseEntity.status(403).body("Access denied: Admins only");
    }

    private boolean isAdmin(String role) {
        return "ADMIN".equalsIgnoreCase(role);
    }

    // ==================== MODULES ====================

    @GetMapping("/modules")
    public ResponseEntity<?> getAllModules(@RequestHeader("X-User-Role") String role) {
        if (!isAdmin(role)) return forbidden();
        return ResponseEntity.ok(moduleRepo.findAll());
    }

    @PostMapping("/modules")
    public ResponseEntity<?> addModule(
            @RequestHeader("X-User-Role") String role,
            @RequestBody Module module) {
        if (!isAdmin(role)) return forbidden();
        return ResponseEntity.ok(moduleRepo.save(module));
    }

    @PutMapping("/modules/{id}")
    public ResponseEntity<?> updateModule(
            @RequestHeader("X-User-Role") String role,
            @PathVariable Long id,
            @RequestBody Module updated) {
        if (!isAdmin(role)) return forbidden();
        return moduleRepo.findById(id).map(m -> {
            m.setModuleName(updated.getModuleName());
            return ResponseEntity.ok(moduleRepo.save(m));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/modules/{id}")
    public ResponseEntity<?> deleteModule(
            @RequestHeader("X-User-Role") String role,
            @PathVariable Long id) {
        if (!isAdmin(role)) return forbidden();
        if (!moduleRepo.existsById(id))
            return ResponseEntity.notFound().build();
        moduleRepo.deleteById(id);
        return ResponseEntity.ok("Module deleted");
    }

    // ==================== QUESTIONS ====================

    @GetMapping("/questions")
    public ResponseEntity<?> getAllQuestions(@RequestHeader("X-User-Role") String role) {
        if (!isAdmin(role)) return forbidden();
        return ResponseEntity.ok(questionRepo.findAll());
    }

    @GetMapping("/questions/module/{moduleId}")
    public ResponseEntity<?> getQuestionsByModule(
            @RequestHeader("X-User-Role") String role,
            @PathVariable Long moduleId) {
        if (!isAdmin(role)) return forbidden();
        return ResponseEntity.ok(questionRepo.findByModuleId(moduleId));
    }

    @PostMapping("/questions")
    public ResponseEntity<?> addQuestion(
            @RequestHeader("X-User-Role") String role,
            @RequestBody Question question) {
        if (!isAdmin(role)) return forbidden();
        return ResponseEntity.ok(questionRepo.save(question));
    }

    @PutMapping("/questions/{id}")
    public ResponseEntity<?> updateQuestion(
            @RequestHeader("X-User-Role") String role,
            @PathVariable Long id,
            @RequestBody Question updated) {
        if (!isAdmin(role)) return forbidden();
        return questionRepo.findById(id).map(q -> {
            q.setQuestionText(updated.getQuestionText());
            q.setOptionA(updated.getOptionA());
            q.setOptionB(updated.getOptionB());
            q.setOptionC(updated.getOptionC());
            q.setOptionD(updated.getOptionD());
            q.setCorrectOption(updated.getCorrectOption());
            q.setModuleId(updated.getModuleId());
            q.setMockNo(updated.getMockNo());
            return ResponseEntity.ok(questionRepo.save(q));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/questions/{id}")
    public ResponseEntity<?> deleteQuestion(
            @RequestHeader("X-User-Role") String role,
            @PathVariable Long id) {
        if (!isAdmin(role)) return forbidden();
        if (!questionRepo.existsById(id))
            return ResponseEntity.notFound().build();
        questionRepo.deleteById(id);
        return ResponseEntity.ok("Question deleted");
    }

    // ==================== USERS ====================

    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers(@RequestHeader("X-User-Role") String role) {
        if (!isAdmin(role)) return forbidden();
        return ResponseEntity.ok(userRepo.findAll());
    }

    // ==================== RESULTS ====================

    @GetMapping("/results")
    public ResponseEntity<?> getAllResults(@RequestHeader("X-User-Role") String role) {
        if (!isAdmin(role)) return forbidden();
        return ResponseEntity.ok(resultRepo.findAll());
    }
}
