package com.abhi.quizapp.repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.abhi.quizapp.entity.QuestionEntity;

@Repository
public interface QuestionRepository extends MongoRepository<QuestionEntity, ObjectId> {

	//custom method
	List<QuestionEntity> findByCategory(String categoryName);

	List<QuestionEntity> findByCategoryAndDifficulty(String category, String difficulty);

	List<QuestionEntity> findByTitle(String title);

		
	}
