package com.exm.rest.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.exm.common.model.ResponseResultVo;
import com.exm.model.Course;
import com.exm.model.Teacher;
import com.exm.rest.service.CourseService;
import com.exm.rest.service.TeacherService;

@RestController
@RequestMapping("/teacher")
public class TeacherController
{
	@Autowired
	private TeacherService teacherService;
	@Autowired
	private CourseService courseService;

	@RequestMapping(value = "/all", method =
	{ RequestMethod.GET,
			RequestMethod.POST }, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Teacher> teachers()
	{

		List<Teacher> teachers = teacherService.getAllTeachers();
		return teachers;
	}

	@RequestMapping(value = "/delete/{userId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseResultVo deleteTeacher(@PathVariable Integer userId)
	{
		teacherService.deleteTeacher(userId);
		return new ResponseResultVo(200, "delete sucess");
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseResultVo addTeacher(@RequestBody Teacher teacher, HttpServletRequest request,
			HttpServletResponse response)
	{
		return teacherService.addTeacher(teacher);
	}

	@RequestMapping(value = "/classes/show/{teacherId}")
	public List<Course> getTeacherClasses(@PathVariable Integer teacherId, HttpServletRequest request,
			HttpServletResponse response)
	{
		return courseService.getCoursesByTeacherId(teacherId);
	}

}
