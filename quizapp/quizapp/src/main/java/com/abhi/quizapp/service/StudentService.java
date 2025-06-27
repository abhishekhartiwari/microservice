package com.abhi.quizapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abhi.quizapp.entity.StudentEntity;
import com.abhi.quizapp.repository.StudentRepository;

@Service
public class StudentService {

	@Autowired
	StudentRepository studentRepository;
	
	
	public List<StudentEntity> getAllStudent(){
		
		return studentRepository.findAll();
	}
}
