package com.abhi.quizservice.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.abhi.quizservice.entity.QuestionWrapper;
import com.abhi.quizservice.entity.QuizEntity;
import com.abhi.quizservice.entity.Response;
import com.abhi.quizservice.feign.QuizInterface;
import com.abhi.quizservice.repository.QuizRepository;

@Service
public class QuizService {

	@Autowired
	QuizRepository quizRepository;

	@Autowired
	QuizInterface quizInterface;

	public ResponseEntity<String> createQuiz(String category, int numQuestions, String title) {

		List<Long> questionsId = quizInterface.getQuestionsForQuiz(category, numQuestions).getBody();
		QuizEntity quiz = new QuizEntity();
		quiz.setTitle(title);
		quiz.setQuestionIds(questionsId);
		
		quizRepository.save(quiz);
		return ResponseEntity.status(HttpStatus.CREATED).body("created a quiz with id: " + title + "");
	}

	public ResponseEntity<List<QuestionWrapper>> getQuizQuestionsByName(Long id) {
	    Optional<QuizEntity> optionalQuiz = quizRepository.findById(id);

	    if (!optionalQuiz.isPresent()) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
	    }

	    List<Long> questionIds = optionalQuiz.get().getQuestionIds();
	    List<QuestionWrapper> quizQuestions = quizInterface.getQuizQuestionsById(questionIds).getBody();

	    return ResponseEntity.ok(quizQuestions);
	}


	public ResponseEntity<Integer> calculateResult(Long quizId, List<Response> submittedQuizResponses) {

//	    QuizEntity quizEntity = quizRepository.findById(quizId)
//	            .orElseThrow(() -> new RuntimeException("Quiz not found"));
//
//	    List<QuestionEntity> questionsList = quizEntity.getQuestionsList();
//
//	    int score = 0;
//
//	    for (QuizResponseEntity submittedResponse : submittedQuizResponses) {
//	        for (QuestionEntity question : questionsList) {
//	            if (question.getId().toHexString().equals(submittedResponse.getId())) {
//	                if (question.getAnswer().equals(submittedResponse.getResponse())) {
//	                    score+=10;
//	                }
//	                break; // found matching question
//	            }
//	        }
//	    }

	    return null;
	}


}
