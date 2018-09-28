package com.exm.rest.service;

import java.util.List;

import com.exm.common.model.ResponseResultVo;
import com.exm.model.Selectedcourse;

public interface SelectedCourseService
{
	ResponseResultVo addSelectedCourse(Selectedcourse selectedcourse);
	
	
	List<Selectedcourse> getStudentSelectedCourses(Integer userId);
	
	
	ResponseResultVo updateStudentMark(Integer mark,Integer studentId,Integer courseId);
}
