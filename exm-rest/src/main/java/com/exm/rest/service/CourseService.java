package com.exm.rest.service;

import java.util.List;

import com.exm.model.Course;

public interface CourseService
{
	Course getCourseById(Integer id);

	public void update(Course course);
	
	void deleteCourse(Integer id);
	
	List<Course> getCoursesByTeacherId(Integer teacherId);
	
	int getTeacherAllCourses(Integer teacherId);
}
