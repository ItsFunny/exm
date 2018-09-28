package com.exm.search.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exm.common.model.ResponseResultVo;
import com.exm.dao.TeacherDao;
import com.exm.model.Teacher;
import com.exm.search.service.TeacherService;

@Service
public class TeacherServiceImpl implements TeacherService
{
	@Autowired
	private TeacherDao teacherDao;

	@Override
	public ResponseResultVo searchTeacher(String q)
	{
		ResponseResultVo responseResultVo = new ResponseResultVo();
		try
		{
			int qInt = Integer.parseInt(q);
			Teacher teacher = teacherDao.getTeacherByUserId(qInt);
			List<Teacher>list=new ArrayList<Teacher>();
			list.add(teacher);
			responseResultVo.setData(list);
		} catch (Exception e)
		{
			List<Teacher> teachers = teacherDao.getTeachersByTeacherName("%" + q + "%");
			responseResultVo.setData(teachers);
		}
		return responseResultVo;
	}
}
