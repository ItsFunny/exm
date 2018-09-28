package com.exm.rest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.druid.util.StringUtils;
import com.exm.common.model.ResponseResultVo;
import com.exm.common.utils.JsonUtils;
import com.exm.dao.TeacherDao;
import com.exm.dao.UserDao;
import com.exm.model.Teacher;
import com.exm.model.User;
import com.exm.rest.dao.JedisClient;
import com.exm.rest.service.TeacherService;
import com.exm.rest.service.UserService;

@Service
public class TeacherServiceImpl implements TeacherService
{
	@Value("${TEACHERS_INFO}")
	private String TEACHERS_INFO;
	@Autowired
	private TeacherDao teacherDao;
	@Autowired
	private UserService userService;
	@Autowired
	private UserDao userDao;
	//
	@Autowired
	private JedisClient jedisClient;

	@Override
	public List<Teacher> getAllTeachers()
	{
		// 似乎没必要将所有的teacher全存放在redis中
		// try
		// {
		// String json = jedisClient.get(TEACHERS_INFO);
		// if (!StringUtils.isEmpty(json))
		// {
		// List<Teacher> teachers = JsonUtils.jsonToList(json, Teacher.class);
		// return teachers;
		// }
		// } catch (Exception e)
		// {
		// e.printStackTrace();
		// }
		List<Teacher> teachers = teacherDao.getAllTeachers();
		// try
		// {
		// String value = JsonUtils.objectToJson(teachers);
		// jedisClient.set(TEACHERS_INFO, value);
		// } catch (Exception e)
		// {
		// e.printStackTrace();
		// }
		return teachers;
	}

	@Transactional
	@Override
	public void deleteTeacher(Integer userId)
	{
		String key = TEACHERS_INFO + ":" + userId.toString();
		try
		{
			String json = jedisClient.get(key);
			if (!StringUtils.isEmpty(json))
			{
				jedisClient.del(key);
			}
		} catch (Exception e)
		{
		}
		teacherDao.deleteTeacherByUserId(userId);

	}

	public boolean isAvliable(Integer userId)
	{
		User user = userDao.getUserByUserId(userId);
		return user == null ? true : false;
	}

	@Transactional
	@Override
	public ResponseResultVo addTeacher(Teacher teacher)
	{
		String key = TEACHERS_INFO + ":" + teacher.getUserId().toString();
		if (isAvliable(teacher.getUserId()))
		{
			try
			{
				String json = jedisClient.get(key);
				if (!StringUtils.isEmpty(json))
				{
					jedisClient.del(key);
				} else
				{
					String value = JsonUtils.objectToJson(teacher);
					jedisClient.set(key, value);
				}
			} catch (Exception e)
			{
				e.printStackTrace();
			}
			teacherDao.addTeacher(teacher);
			userService.addUser(teacher);
			return new ResponseResultVo(200, "添加成功");
		}
		return new ResponseResultVo(400, "账号已经存在");

	}
}
