package com.abhi.quizapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abhi.quizapp.entity.StudentEntity;
import com.abhi.quizapp.service.StudentService;

@RestController
@RequestMapping("students")
public class StudentController {
	
	@Autowired
	StudentService studentService;
	
	@GetMapping("allStudents")
	public List<StudentEntity> getAllStudent(){
		
		List<StudentEntity> allStudent = studentService.getAllStudent();
		return allStudent;
	}

}
