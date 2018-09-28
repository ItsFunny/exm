package com.exm.rest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exm.dao.UserDao;
import com.exm.model.Student;
import com.exm.model.Teacher;
import com.exm.model.User;
import com.exm.rest.service.UserService;

@Service
public class UserServiceImpl implements UserService
{

	@Autowired
	private UserDao userDao;

	@Override
	public void addUser(Student student)
	{
		User user = new User();
		user.setPassword("123");
		user.setUserId(student.getUserId());
		user.setRoleId(3);
		userDao.addUser(user);
	}

	@Override
	public void addUser(Teacher teacher)
	{
		User user=new User();
		user.setPassword("123");
		user.setUserId( Long.parseLong(teacher.getUserId().toString()));
		user.setRoleId(2);
		userDao.addUser(user);
	}

}
