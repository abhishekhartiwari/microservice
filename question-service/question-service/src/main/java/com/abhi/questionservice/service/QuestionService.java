package com.abhi.questionservice.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.abhi.questionservice.entity.QuestionEntity;
import com.abhi.questionservice.entity.QuestionWrapper;
import com.abhi.questionservice.entity.Response;
import com.abhi.questionservice.repository.QuestionRepository;

@Service
public class QuestionService {

	@Autowired
	QuestionRepository questionRepository;

	public List<QuestionEntity> getAllQuestions() {

		return questionRepository.findAll();
	}

	public List<QuestionEntity> getQuestionsByCategory(String category) {

		return questionRepository.findByCategory(category);

	}

	public String addQuestion(QuestionEntity question) {

		questionRepository.save(question);
		return "Success";

	}

	public Optional<QuestionEntity> getQuestionById(Long id) {

		return questionRepository.findById(id);

	}

	public ResponseEntity<?> updateQuestionById(Long id, QuestionEntity updatedQuestion) {

		Optional<QuestionEntity> optionalQuestion = questionRepository.findById(id);
		if (optionalQuestion.isPresent()) {
			QuestionEntity existingQuestion = optionalQuestion.get();
			// Update fields
			existingQuestion.setCategory(updatedQuestion.getCategory());
			existingQuestion.setDifficulty(updatedQuestion.getDifficulty());
			existingQuestion.setOption1(updatedQuestion.getOption1());
			existingQuestion.setOption2(updatedQuestion.getOption2());
			existingQuestion.setOption3(updatedQuestion.getOption3());
			existingQuestion.setOption4(updatedQuestion.getOption4());
			existingQuestion.setQuestion(updatedQuestion.getQuestion());
			existingQuestion.setAnswer(updatedQuestion.getAnswer());
			// Save updated entity
			questionRepository.save(existingQuestion);
			//return new ResponseEntity<>(HttpStatus.OK);
			//return new ResponseEntity<>("Question created successfully!", HttpStatus.OK);
			return ResponseEntity.status(HttpStatus.OK).body("The resourse has been updated");
		} else {
			
			return new ResponseEntity<>("No resource found for this id", HttpStatus.NOT_FOUND);
			
		}

	}

	public ResponseEntity<?> deleteQuestion(Long id) {
		Optional<QuestionEntity> deletedQuestion = questionRepository.findById(id);
		try {
			  questionRepository.deleteById(id);
			return ResponseEntity.ok().body("resourse has been deleted" +deletedQuestion+"");
		} catch (Exception e) {
			
			e.printStackTrace();
			return ResponseEntity.notFound().build();
		}
		
	}

	public ResponseEntity<List<QuestionEntity>> getQuestionByCategoryAndDifficulty(String category, String difficulty) {
		
		try {
	        List<QuestionEntity> questions = questionRepository.findByCategoryAndDifficulty(category, difficulty);
	        return ResponseEntity.ok(questions);
		} catch (Exception e) {
			
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
		
	}


	//below 3 endpoint will get call from quiz service
		public ResponseEntity<List<Long>> getQuestionsForQuiz(String category, int numQuestions) {
		    List<QuestionEntity> questionsByCategory = questionRepository.findByCategory(category);
		    Collections.shuffle(questionsByCategory);
		    List<QuestionEntity> randomQuestions = questionsByCategory.subList(0, numQuestions);

		    List<Long> questionsId = new ArrayList<>();
		    for (QuestionEntity q : randomQuestions) {
		        questionsId.add(q.getId());
		    }

		    return new ResponseEntity<>(questionsId, HttpStatus.OK);
		}

		public ResponseEntity<List<QuestionWrapper>> getQuizQuestionsById(List<Long> quesIds) {
		    List<QuestionEntity> questions = questionRepository.findAllById(quesIds);

		    List<QuestionWrapper> wrappers = questions.stream()
		        .map(q -> new QuestionWrapper(
		            q.getId(),
		            q.getOption1(),
		            q.getOption2(),
		            q.getOption3(),
		            q.getOption4(),
		            q.getQuestion()
		        ))
		        .collect(Collectors.toList());

		    return ResponseEntity.ok(wrappers);
		}



		public ResponseEntity<Integer> calculateResult(List<Response> response) {
		    List<QuestionEntity> questionsList = questionRepository.findAll();

		    int score = 0;

		    for (Response submittedResponse : response) {
		        for (QuestionEntity question : questionsList) {
		            if (question.getId().equals(submittedResponse.getId())) {
		                if (question.getAnswer().equalsIgnoreCase(submittedResponse.getResponse())) {
		                    score += 10;
		                }
		                break;
		            }
		        }
		    }

		    return new ResponseEntity<>(score, HttpStatus.OK);
		}

}
