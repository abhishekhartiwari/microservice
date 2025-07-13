package com.abhi.quizapp.entity;

public class QuizResponseEntity {

	private String id;
	String response;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	public QuizResponseEntity(String id, String response) {
		super();
		this.id = id;
		this.response = response;
	}
	public QuizResponseEntity() {
		super();
	}



}
