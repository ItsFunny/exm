package com.exm.search.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exm.search.service.CourseService;

@RestController
public class CourseController
{
	/*
	 * 将商品导入数据库
	 */
	@Autowired
	private CourseService courseService;
	@RequestMapping("/importAll")
	public void importAll()
	{
		courseService.importAll();
	}
}
