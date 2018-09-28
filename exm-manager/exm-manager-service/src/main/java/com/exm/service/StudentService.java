package com.exm.service;

import java.util.List;

import com.exm.common.model.ResponseResultVo;
import com.exm.model.Student;

public interface StudentService
{
	List<Student> getAllStudent();
	Student getStudentByUserId(Integer userId);
	ResponseResultVo  updateStudent(Student student);
	
	ResponseResultVo deleteStudent(Integer userId);
	
	
	ResponseResultVo addStudent(Student student);
	
	List<Student> searchStudens(String q);
	
	Integer getTotalCount();
	
	List<Student>getStudentsLimited(Integer start,int end);
}
