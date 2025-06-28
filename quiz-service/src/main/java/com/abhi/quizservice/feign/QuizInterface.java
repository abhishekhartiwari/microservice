package com.abhi.quizservice.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.abhi.quizservice.entity.QuestionWrapper;
import com.abhi.quizservice.entity.Response;


@FeignClient(name = "QUESTION-SERVICE")

public interface QuizInterface {
	
    @GetMapping("/questions/generate")
    ResponseEntity<List<Long>> getQuestionsForQuiz(@RequestParam String category, @RequestParam int numQuestions);
    
	@PostMapping("/questions/getQuizQuestions")
	public ResponseEntity<List<QuestionWrapper>> getQuizQuestionsById(@RequestBody List<Long> quesIds);

	@PostMapping("/questions/getScore")
	public ResponseEntity<Integer> getScore(@RequestBody List<Response> response);

}
