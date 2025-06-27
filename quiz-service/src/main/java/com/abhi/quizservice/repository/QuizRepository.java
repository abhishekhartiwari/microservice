	package com.abhi.quizservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abhi.quizservice.entity.QuizEntity;

public interface QuizRepository extends JpaRepository<QuizEntity, Long>{

	public List<QuizEntity> findByTitle(String title);


}
