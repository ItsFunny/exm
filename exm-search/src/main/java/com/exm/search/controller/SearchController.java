package com.exm.search.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.exm.common.model.ResponseResultVo;
import com.exm.common.model.SearchResultVo;
import com.exm.model.Student;
import com.exm.search.service.SearchService;
import com.exm.search.service.StudentService;
import com.exm.search.service.TeacherService;

@Controller
public class SearchController
{
	@Autowired
	private SearchService searchService;

	@Autowired
	private StudentService studentService;
	@Autowired
	private TeacherService teacherService;

	@RequestMapping(value = "/query", method = RequestMethod.GET)
	@ResponseBody
	public SearchResultVo search(@RequestParam("q") String queryString,
			@RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
			@RequestParam(name = "pageSize", defaultValue = "5") Integer rows) throws Exception
	{
		// 查询条件不能为空,这里的交给前端的js来控制
		// if (StringUtils.isEmpty(queryString))
		// {
		// return null;
		// }
		SearchResultVo result = null;
		try
		{
			queryString = new String(queryString.getBytes("iso8859-1"), "utf-8");
			result = searchService.search(queryString, pageNum, rows);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}

	@RequestMapping("/student/{q}")
	@ResponseBody
	public List<Student> getStudents(@PathVariable String q)
	{
		List<Student> students = studentService.searchStudents(q);
		return students;
	}

	@RequestMapping(value = "/teacher/{q}")
	@ResponseBody
	public ResponseResultVo searchTeachers(@PathVariable(name = "q") String q, HttpServletRequest request,
			HttpServletResponse response)
	{
		ResponseResultVo resultVo = teacherService.searchTeacher(q);
		return resultVo;
	}

}
