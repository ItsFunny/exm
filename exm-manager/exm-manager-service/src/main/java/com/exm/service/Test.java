package com.exm.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.exm.dao.TeacherDao;
import com.exm.model.Teacher;


public class Test
{
	@Autowired
	private TeacherDao teacherDao;
	public void insert()
	{
		for (Integer i = 1; i <30; i++)
		{
			Teacher teacher=new Teacher();
			teacher.setBirthYear(new Date());
			teacher.setCollegeId(1);
			teacher.setDegree("qq:"+i*7);
			teacher.setGrade(new Date());
			teacher.setSex("男");
			teacher.setTeacherName("www:"+i*3);
			teacher.setTitle("asd");
			teacher.setUserId(i*104);
			teacherDao.addTeacher(teacher);
			
		}
		
	}
	
	
}
