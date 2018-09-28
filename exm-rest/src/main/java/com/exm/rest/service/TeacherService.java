package com.exm.rest.service;

import java.util.List;

import com.exm.common.model.ResponseResultVo;
import com.exm.model.Teacher;

public interface TeacherService
{
	List<Teacher> getAllTeachers();
	
	void deleteTeacher(Integer userId);
	
	ResponseResultVo addTeacher(Teacher teacher);
	
	
}
