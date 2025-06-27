package com.abhi.quizservice.entity;

import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "quiz")
public class QuizEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
	@ElementCollection
	private List<Long> questionIds;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public List<Long> getQuestionIds() {
		return questionIds;
	}
	public void setQuestionIds(List<Long> questionIds) {
		this.questionIds = questionIds;
	}
	public QuizEntity(Long id, String title, List<Long> questionIds) {
		super();
		this.id = id;
		this.title = title;
		this.questionIds = questionIds;
	}
	@Override
	public String toString() {
		return "QuizEntity [id=" + id + ", title=" + title + ", questionIds=" + questionIds + "]";
	}
	public QuizEntity() {
		
	}
	
	
	
}
