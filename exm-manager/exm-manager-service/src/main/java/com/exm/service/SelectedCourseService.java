package com.exm.service;

import java.util.List;

import com.exm.common.model.ResponseResultVo;
import com.exm.model.Course;

public interface SelectedCourseService
{
	ResponseResultVo addSelectedCourse(Integer courseId, Integer userId);

	List<List<Course>> getStudentAllCourses(Integer userId);
	
	ResponseResultVo updateStudentMark(Integer mark,Integer studentId,Integer courseId);

}
