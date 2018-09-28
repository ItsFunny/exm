package com.exm.service;

import java.util.List;

import com.exm.common.model.ResponseResultVo;
import com.exm.common.model.SearchResultVo;
import com.exm.model.Course;

public interface CourseService
{
	public List<Course> getAll();

	public void deleteCourseById(Integer id);

	public void insertCourse(Course course);
	
	public Integer getTotalCount(); 
	
	public List<Course> getCoursesLimited(Integer start,Integer end);

	public Course getCourseById(Integer id); 
	
	public void update(Course course);
	
	public ResponseResultVo addCourse(Course course) throws Exception;
	
	public SearchResultVo searchCourses(Integer pageSize,Integer pageNum,String q);
}
