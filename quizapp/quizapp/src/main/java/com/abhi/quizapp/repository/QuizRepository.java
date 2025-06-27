package com.abhi.quizapp.repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.abhi.quizapp.entity.QuizEntity;

public interface QuizRepository extends MongoRepository<QuizEntity, ObjectId>{

	public List<QuizEntity> findByTitle(String title);


}
