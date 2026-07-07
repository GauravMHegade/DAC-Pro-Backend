package com.quiz.quizapp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.quiz.quizapp.entity.TestResult;
import com.quiz.quizapp.repository.ResultRepository;

@RestController
@RequestMapping("/api/result")
@CrossOrigin
public class ResultController {

    @Autowired
    private ResultRepository repo;

    @PostMapping
    public TestResult save(@RequestBody TestResult result){
        return repo.save(result);
    }
    
    @GetMapping("/attempts/{userId}")
    public List<TestResult> getAttempts(@PathVariable Long userId){
        return repo.findByUserId(userId);
    }
    
    @GetMapping("/latest/{userId}")
    public Map<String,Object> getLatest(@PathVariable Long userId){

        List<TestResult> list = repo.findByUserId(userId);

        Map<String,Object> res = new HashMap<>();

        if(list.isEmpty()){
            res.put("moduleName","N/A");
            res.put("score",0);
            res.put("attempted",0);
            res.put("unattempted",0);
            res.put("totalTests",0);
            res.put("practiceTests",0);
            res.put("mockTests",0);
            res.put("bestScore",0);
            return res;
        }

        TestResult latest = list.get(list.size()-1);

        int practice = 0;
        int mock = 0;
        int best = 0;

        for(TestResult r : list){

            if("Practice".equalsIgnoreCase(r.getTestType()))
                practice++;

            if("Mock".equalsIgnoreCase(r.getTestType()))
                mock++;

            if(r.getScore() > best)
                best = r.getScore();
        }

        res.put("moduleName","Module " + latest.getModuleId());
        res.put("score",latest.getScore());
        res.put("attempted",latest.getAttempted());
        res.put("unattempted",latest.getUnattempted());
        res.put("totalTests",list.size());
        res.put("practiceTests",practice);
        res.put("mockTests",mock);
        res.put("bestScore",best);

        return res;
    }
}