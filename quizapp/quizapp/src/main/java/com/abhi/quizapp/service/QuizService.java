package com.abhi.quizapp.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.abhi.quizapp.entity.QuestionEntity;
import com.abhi.quizapp.entity.QuestionWrapper;
import com.abhi.quizapp.entity.QuizEntity;
import com.abhi.quizapp.entity.QuizResponseEntity;
import com.abhi.quizapp.repository.QuestionRepository;
import com.abhi.quizapp.repository.QuizRepository;

@Service
public class QuizService {

	@Autowired
	QuizRepository quizRepository;

	@Autowired
	QuestionRepository questionRepository;

	public ResponseEntity<String> createQuiz(String category, int number, String name) {

		List<QuestionEntity> questionsByCategory = questionRepository.findByCategory(category);
		Collections.shuffle(questionsByCategory);
		List<QuestionEntity> randomQuestions = questionsByCategory.subList(0, 5);
		// Stream<QuestionEntity> definitQuestions =
		// questionsByCategory.stream().limit(number);
		QuizEntity quiz = new QuizEntity();
		quiz.setTitle(name);
		quiz.setQuestionsList(randomQuestions);
		quizRepository.save(quiz);
		return ResponseEntity.status(HttpStatus.CREATED).body("created a quiz with id: " + name + "");
	}

	public ResponseEntity<List<QuestionWrapper>> getQuizQuestionsByName(String name) {
	    List<QuizEntity> quizzes = quizRepository.findByTitle(name);
	    if (quizzes.isEmpty()) {
	        return ResponseEntity.noContent().build();
	    }
	    List<QuestionEntity> questionsListFromDb = quizzes.get(0).getQuestionsList();
	    List<QuestionWrapper> questionForUser = new ArrayList<>();
	    for(QuestionEntity question: questionsListFromDb) {
	    	QuestionWrapper q = new QuestionWrapper(question.getId(), question.getQuestion(), question.getOption1(), question.getOption2(), 
	    			question.getOption4(), question.getOption4());
	    	questionForUser.add(q);
	    }
	    
	    return new ResponseEntity<>(questionForUser, HttpStatus.OK);
	}

	public ResponseEntity<Integer> calculateResult(ObjectId quizId, List<QuizResponseEntity> submittedQuizResponses) {

	    QuizEntity quizEntity = quizRepository.findById(quizId)
	            .orElseThrow(() -> new RuntimeException("Quiz not found"));

	    List<QuestionEntity> questionsList = quizEntity.getQuestionsList();

	    int score = 0;

	    for (QuizResponseEntity submittedResponse : submittedQuizResponses) {
	        for (QuestionEntity question : questionsList) {
	            if (question.getId().toHexString().equals(submittedResponse.getId())) {
	                if (question.getAnswer().equals(submittedResponse.getResponse())) {
	                    score+=10;
	                }
	                break; // found matching question
	            }
	        }
	    }

	    return ResponseEntity.ok(score);
	}


}
