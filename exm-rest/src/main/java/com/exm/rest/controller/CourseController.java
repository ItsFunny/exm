package com.exm.rest.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.exm.model.Course;
import com.exm.rest.service.CourseService;

@RestController
@RequestMapping("/course")
public class CourseController
{
	@Autowired
	private CourseService courseService;

	@RequestMapping("/show/{courseId}")
	public Course getCourse(@PathVariable("courseId") Integer id)
	{

		Course course = courseService.getCourseById(id);
		return course;
	}

	@RequestMapping("/edit/update")
	public void updateCourse(@RequestBody Course course)
	{
		courseService.update(course);
	}

	@RequestMapping("/edit/delete")
	public void deleteCourse(@RequestParam("courseId") Integer courseId, HttpServletRequest request,
			HttpServletResponse response)
	{
		// http://localhost:8081/rest/course/edit/delete/10

		courseService.deleteCourse(courseId);
	}

//	@RequestMapping(name="/teacher/all/{teacherId}",method=RequestMethod.POST)
//	public int getTeacherAllCoursesCount(@PathVariable("teacherId") Integer teacherId)
//	{
//		return courseService.getTeacherAllCourses(teacherId);
//	}

}
