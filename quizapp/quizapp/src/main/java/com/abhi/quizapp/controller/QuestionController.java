package com.abhi.quizapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abhi.quizapp.entity.QuestionEntity;
import com.abhi.quizapp.service.QuestionService;

@RestController
@RequestMapping("questions")
public class QuestionController {

	@Autowired
	QuestionService questionService;

	@GetMapping("allQuestions")
	public List<QuestionEntity> getAllQuestions() {

		List<QuestionEntity> allQuestions = questionService.getAllQuestions();
		return allQuestions;
	}

	@GetMapping("category/{category}")
	public List<QuestionEntity> getAllQuestionsByCategory(@PathVariable String category) {

		return questionService.getQuestionsByCategory(category);
	}

	@PostMapping("question")
	public String addQuestion(@RequestBody QuestionEntity question) {
		questionService.addQuestion(question);
		return "We have added another question in the databse " + question.toString() + " ";
	}

	@PutMapping("update/id/{id}")
	public ResponseEntity<?> updateQuestion(@RequestBody QuestionEntity updatedQuestion, @PathVariable String id) {
		return questionService.updateQuestionById(id, updatedQuestion);
	}

	@DeleteMapping("delete/id/{id}")
	public ResponseEntity<?> deleteQuestion(@PathVariable String id) {
		return questionService.deleteQuestion(id);

	}

	@GetMapping("category/{category}/{difficulty}")
	public ResponseEntity<List<QuestionEntity>> getQuestionByCategoryAndDifficulty(@PathVariable String category, @PathVariable String difficulty) {
		return questionService.getQuestionByCategoryAndDifficulty(category, difficulty);

	}
	


}
