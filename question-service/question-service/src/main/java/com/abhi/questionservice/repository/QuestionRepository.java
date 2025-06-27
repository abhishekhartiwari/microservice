package com.abhi.questionservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abhi.questionservice.entity.QuestionEntity;

@Repository
public interface QuestionRepository extends JpaRepository<QuestionEntity, Long> {

	//custom method

	public List<QuestionEntity> findByCategory(String category);
	
	public  List<QuestionEntity> findByCategoryAndDifficulty(String category, String difficulty);
		
		
	}
