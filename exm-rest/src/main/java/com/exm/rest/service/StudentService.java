package com.exm.rest.service;

import com.exm.common.model.ResponseResultVo;
import com.exm.model.Student;

public interface StudentService
{
	Student getStudentByUserId(Integer userId);
	
	void updateStudent(Student student);
	
	ResponseResultVo deleteStudentByUserId(Integer userId);
	
	ResponseResultVo addStudent(Student student);
}
