package com.quiz.quizapp.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "test_results")
public class TestResult {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	@Column(name = "result_id")
	private Long resultId;
	
	@Column(name = "user_id")
	private Long userId;
	
	@Column(name = "module_id")
	private Long moduleId;

	@Column(name = "score")
	private int score;
	
	@Column(name = "attempted")
	private int attempted;
	
	@Column(name = "unattempted")
	private int unattempted;
	
	@Column(name = "test_type")
	private String testType;
	
    @Column(name="mock_no")
	private Integer mockNo;

	public Long getResultId() {
		return resultId;
	}

	public void setResultId(Long resultId) {
		this.resultId = resultId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getModuleId() {
		return moduleId;
	}

	public void setModuleId(Long moduleId) {
		this.moduleId = moduleId;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getAttempted() {
		return attempted;
	}

	public void setAttempted(int attempted) {
		this.attempted = attempted;
	}

	public int getUnattempted() {
		return unattempted;
	}

	public void setUnattempted(int unattempted) {
		this.unattempted = unattempted;
	}

	public String getTestType() {
		return testType;
	}

	public void setTestType(String testType) {
		this.testType = testType;
	}

	public Integer getMockNo() {
		return mockNo;
	}

	public void setMockNo(Integer mockNo) {
		this.mockNo = mockNo;
	}
}