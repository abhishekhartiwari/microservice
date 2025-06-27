package com.abhi.quizapp.controller;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.abhi.quizapp.entity.QuestionWrapper;
import com.abhi.quizapp.entity.QuizResponseEntity;
import com.abhi.quizapp.service.QuizService;

@RestController
@RequestMapping("quiz")
public class QuizController {
	
	@Autowired
	QuizService quizService;

	@PostMapping("create")
	public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam int number, @RequestParam String name) {
		
		return quizService.createQuiz(category, number, name);
	
	}
	
	@GetMapping("quizname/{name}")
	public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable String name){
		return quizService.getQuizQuestionsByName(name);
		
	}
	
	@PostMapping("submit/{id}")
	public ResponseEntity<Integer> submitQuiz(@PathVariable ObjectId id, @RequestBody List<QuizResponseEntity> quizResponse) {
	    return quizService.calculateResult(id, quizResponse);
	}
}
