package com.abhi.quizservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abhi.quizservice.entity.QuestionWrapper;
import com.abhi.quizservice.entity.QuizDto;
import com.abhi.quizservice.entity.Response;
import com.abhi.quizservice.service.QuizService;



@RestController
@RequestMapping("quiz")
public class QuizController {
	
	@Autowired
	QuizService quizService;

	@PostMapping("create")
	public ResponseEntity<String> createQuiz(@RequestBody QuizDto dto) {
		
		return quizService.createQuiz(dto.getCategory(), dto.getNumQuestions(), dto.getTitle());
	
	}
	
	@GetMapping("quizname/{id}")
	public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Long id){
		return quizService.getQuizQuestionsByName(id);
		
	}
	
	@PostMapping("submit/{id}")
	public ResponseEntity<Integer> submitQuiz(@PathVariable Long id, @RequestBody List<Response> quizResponse) {
	    return quizService.calculateResult(id, quizResponse);
	}
}
