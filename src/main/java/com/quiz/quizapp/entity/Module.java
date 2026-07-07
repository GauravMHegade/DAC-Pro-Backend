package com.quiz.quizapp.entity;

import jakarta.persistence.*;

@Entity
@Table(name="modules")
public class Module {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    @Column(name="module_id")
    private Long moduleId;
    
    @Column(name="module_name")
    private String moduleName;

    public Long getModuleId() { return moduleId; }
    public void setModuleId(Long moduleId) { this.moduleId = moduleId; }

    public String getModuleName() { return moduleName; }
    public void setModuleName(String moduleName) { this.moduleName = moduleName; }
}