package com.abhi.questionservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.abhi.questionservice.entity.QuestionEntity;
import com.abhi.questionservice.entity.QuestionWrapper;
import com.abhi.questionservice.entity.Response;
import com.abhi.questionservice.service.QuestionService;

@RestController
@RequestMapping("questions")
public class QuestionController {

	@Autowired
	QuestionService questionService;
	
	@Autowired
	Environment env;

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
	public ResponseEntity<?> updateQuestion(@RequestBody QuestionEntity updatedQuestion, @PathVariable Long id) {
		return questionService.updateQuestionById(id, updatedQuestion);
	}

	@DeleteMapping("delete/id/{id}")
	public ResponseEntity<?> deleteQuestion(@PathVariable Long id) {
		return questionService.deleteQuestion(id);

	}

	@GetMapping("category/{category}/{difficulty}")
	public ResponseEntity<List<QuestionEntity>> getQuestionByCategoryAndDifficulty(@PathVariable String category, @PathVariable String difficulty) {
		return questionService.getQuestionByCategoryAndDifficulty(category, difficulty);

	}
	
	//to get the questions from question db
	@GetMapping("generate")
	public ResponseEntity<List<Long>> getQuestionsForQuiz(@RequestParam String category, @RequestParam int numQuestions) {
		    
	    if (category == null || category.isBlank()) {
	        System.out.println("categoryName is null or blank!");
	    }
		    System.out.println("categoryName = " + category + ", numOfQuestion = " + numQuestions);

		    return questionService.getQuestionsForQuiz(category, numQuestions);
	}

	@PostMapping("getQuizQuestions")
	public ResponseEntity<List<QuestionWrapper>> getQuizQuestionsById(@RequestBody List<Long> quesIds) {
		System.out.println(env.getProperty("local.server.port"));
	    return questionService.getQuizQuestionsById(quesIds);
	}

	@PostMapping("getScore")
	public ResponseEntity<Integer> getScore(@RequestBody List<Response> response) {
	    return questionService.calculateResult(response);
	}
}
