package com.exm.service;

import java.util.List;

import com.exm.common.model.ResponseResultVo;
import com.exm.model.Course;
import com.exm.model.Teacher;

public interface TeacherService
{
	List<Teacher> getAllTeachers();

	ResponseResultVo deleteTeacher(Integer userId);

	ResponseResultVo addTeacher(Teacher teacher);

	ResponseResultVo searchTeachers(String q);
	
	List<Course> getTeacherClasses(Integer teacherId);

	List<Teacher> getTeachersLimited(Integer start, Integer end);
	Integer getTotalCount();
	
	Integer getTeacherAllCoursesTotalCount(Integer userId);
	
	
}
