package com.abhi.quizapp.entity;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "quiz")
public class QuizEntity {

	@Id
	private ObjectId id;
	private String title;
	private List<QuestionEntity> questionsList;
	
	public ObjectId getId() {
		return id;
	}
	public void setId(ObjectId id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<QuestionEntity> getQuestionsList() {
		return questionsList;
	}
	public void setQuestionsList(List<QuestionEntity> questionsList) {
		this.questionsList = questionsList;
	}
	public QuizEntity(ObjectId id, String title, List<QuestionEntity> questionsList) {
		super();
		this.id = id;
		this.title = title;
		this.questionsList = questionsList;
	}
	@Override
	public String toString() {
		return "QuizEntity [id=" + id + ", title=" + title + ", questionsList=" + questionsList + "]";
	}
	public QuizEntity() {
		
	}
	
	
	
}
