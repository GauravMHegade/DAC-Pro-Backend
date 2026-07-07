package com.quiz.quizapp.entity;

import jakarta.persistence.*;

@Entity
@Table(name="questions")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    @Column(name = "question_id")
    private Long questionId;
    
    @Column(name = "module_id")
    private Long moduleId;
    
    @Column(name="question_text")
    private String questionText;

    @Column(name="option_a")
    private String optionA;

    @Column(name="option_b")
    private String optionB;

    @Column(name="option_c")
    private String optionC;

    @Column(name="option_d")
    private String optionD;

    @Column(name="correct_option")
    private String correctOption;
    
    @Column(name="mock_no")
    private Integer mockNo;

    public Integer getMockNo() {
        return mockNo;
    }

    public void setMockNo(Integer mockNo) {
        this.mockNo = mockNo;
    }

    public Long getQuestionId() { return questionId; }
    public void setQuestionId(Long questionId) { this.questionId = questionId; }

    public Long getModuleId() { return moduleId; }
    public void setModuleId(Long moduleId) { this.moduleId = moduleId; }

    public String getQuestionText() { return questionText; }
    public void setQuestionText(String questionText) { this.questionText = questionText; }

    public String getOptionA() { return optionA; }
    public void setOptionA(String optionA) { this.optionA = optionA; }

    public String getOptionB() { return optionB; }
    public void setOptionB(String optionB) { this.optionB = optionB; }

    public String getOptionC() { return optionC; }
    public void setOptionC(String optionC) { this.optionC = optionC; }

    public String getOptionD() { return optionD; }
    public void setOptionD(String optionD) { this.optionD = optionD; }

    public String getCorrectOption() { return correctOption; }
    public void setCorrectOption(String correctOption) { this.correctOption = correctOption; }
}