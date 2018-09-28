package com.exm.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.exm.common.model.ResponseResultVo;
import com.exm.model.Student;
import com.exm.rest.service.StudentService;

@RestController
@RequestMapping("/student")
public class StudentController
{
	@Autowired
	private StudentService studentService;

	@RequestMapping("/get/{userId}")
	public Student getStudent(@PathVariable("userId") Integer userId)
	{
		return studentService.getStudentByUserId(userId);
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ResponseResultVo updateStudent(@RequestBody Student student)
	{
		studentService.updateStudent(student);
		ResponseResultVo responseResultVo = new ResponseResultVo(200, "update sucess");
		return responseResultVo;
	}

	@RequestMapping(value = "/delete/{userId}")
	public ResponseResultVo deleteStudentByUserId(@PathVariable("userId") Integer userId)
	{
		ResponseResultVo responseResultVo = studentService.deleteStudentByUserId(userId);
		return responseResultVo;
	}
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public ResponseResultVo addStudent(@RequestBody Student student)
	{
		return studentService.addStudent(student);
	}
}
