package com.exm.rest.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exm.common.model.ResponseResultVo;
import com.exm.model.Selectedcourse;
import com.exm.rest.service.SelectedCourseService;

@RestController
public class SelectedCourseController
{
	@Autowired
	private SelectedCourseService selectedCourseService;

	/*
	 * 选课
	 */
	@RequestMapping("/student/course/select")
	public ResponseResultVo studentSelectCourse(@RequestBody Selectedcourse selectedcourse)
	{
		// Integer mark=markString==null?null:Integer.parseInt(markString);
		ResponseResultVo responseResultVo = selectedCourseService.addSelectedCourse(selectedcourse);
		return responseResultVo;
	}

	/*
	 * 显示已经选了的课程
	 */
	@RequestMapping("/student/course/selected/show/{userId}")
	public List<Selectedcourse> getStudentSelectedCourses(@PathVariable("userId") Integer userId)
	{
		return selectedCourseService.getStudentSelectedCourses(userId);
	}
	/*
	 * 修改学生的成绩
	 */
	@RequestMapping("/teacher/mark/update")
	public ResponseResultVo updateStudentMark(HttpServletRequest request,HttpServletResponse response)
	{
		Integer studentId=Integer.parseInt(request.getParameter("studentId"));
		int courseId = Integer.parseInt(request.getParameter("courseId"));
		int mark=Integer.parseInt(request.getParameter("mark"));
		ResponseResultVo resultVo = selectedCourseService.updateStudentMark(mark, studentId, courseId);
		return resultVo;
	}
}
