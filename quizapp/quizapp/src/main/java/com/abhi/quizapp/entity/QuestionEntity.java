package com.abhi.quizapp.entity;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "question")
public class QuestionEntity {

	@Id
	private ObjectId id;
	private String category;
	@Field("difficulty_level")
	private String difficulty;
	private String option1;
	private String option2;
	private String option3;
	private String option4;
	@Field("question_title")
	private String question;
	@Field("right_answer")
	private String answer;

	// Constructors
	public QuestionEntity() {
	    }

	public QuestionEntity(String category, String difficulty, String option1, String option2, String option3,
	                    String option4, String question, String answer) {
	        this.category = category;
	        this.difficulty = difficulty;
	        this.option1 = option1;
	        this.option2 = option2;
	        this.option3 = option3;
	        this.option4 = option4;
	        this.question = question;
	        this.answer = answer;
	    }

	// Getters and Setters

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getOption1() {
		return option1;
	}

	public void setOption1(String option1) {
		this.option1 = option1;
	}

	public String getOption2() {
		return option2;
	}

	public void setOption2(String option2) {
		this.option2 = option2;
	}

	public String getOption3() {
		return option3;
	}

	public void setOption3(String option3) {
		this.option3 = option3;
	}

	public String getOption4() {
		return option4;
	}

	public void setOption4(String option4) {
		this.option4 = option4;
	}

	public String getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	@Override
	public String toString() {
		return "Question{" + "id='" + id + '\'' + ", category='" + category + '\'' + ", difficulty_level='"
				+ difficulty + '\'' + ", option1='" + option1 + '\'' + ", option2='" + option2 + '\''
				+ ", option3='" + option3 + '\'' + ", option4='" + option4 + '\'' + ", question_title='" + question
				+ '\'' + ", right_answer='" + answer + '\'' + '}';
	}
}
