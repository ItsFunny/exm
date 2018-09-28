package com.exm.search.service;

import java.util.List;

import com.exm.model.Student;

public interface StudentService
{

	List<Student>getStudentsByStudentName(String studentName);
	
	List<Student>getStudentsByUserId(Integer userId);
	
	
	List<Student>searchStudents(String searchQuery);
}
