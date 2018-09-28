package com.exm.rest.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exm.common.model.ResponseResultVo;
import com.exm.common.utils.JsonUtils;
import com.exm.dao.StudentDao;
import com.exm.model.Student;
import com.exm.rest.dao.JedisClient;
import com.exm.rest.service.StudentService;
import com.exm.rest.service.UserService;

@Service
public class StudentServiceImpl implements StudentService
{
	@Value("${STUDENT_INFO}")
	private String STUDENT_INFO;
	@Autowired
	private StudentDao studentDao;
	@Autowired
	private JedisClient jedisClient;
	@Autowired
	private UserService userService;

	Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

	@Override
	public Student getStudentByUserId(Integer userId)
	{
		String key = STUDENT_INFO + ":" + userId.toString();
		Student student = new Student();
		// 从redis中取
		try
		{
			String json = jedisClient.get(key);
			if (json != null)
			{
				student = JsonUtils.jsonToPojo(json, Student.class);
				return student;
			}

		} catch (Exception e)
		{
			e.printStackTrace();
		}
		student = studentDao.getStudentByUserId(userId);
		try
		{
			String json = JsonUtils.objectToJson(student);
			jedisClient.set(key, json);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return student;
	}

	@Override
	public void updateStudent(Student student)
	{
		studentDao.updateStudent(student);
		// 对redis进行更新操作
		try
		{
			jedisClient.del(STUDENT_INFO + ":" + student.getUserId().toString());
			String value = JsonUtils.objectToJson(student);
			jedisClient.set(STUDENT_INFO + ":" + student.getUserId().toString(), value);
		} catch (Exception e)
		{
			logger.info("当redis试图更新账户:" + student.getUserId() + "姓名为" + student.getStudentName() + "的时候bug了");
			e.printStackTrace();
		}
		ResponseResultVo responseResultVo = new ResponseResultVo();
		responseResultVo.setStatus(200);
		responseResultVo.setMsg("update sucess");
		// return responseResultVo;
	}

	@Override
	public ResponseResultVo deleteStudentByUserId(Integer userId)
	{
		// 先从redis中查询,有则删,无则过
		String key = STUDENT_INFO + userId.toString();
		try
		{
			String json = jedisClient.get(key);
			if (json != null)
			{
				jedisClient.del(key);
			}
		} catch (Exception e)
		{
			logger.info("当redis试图删除:" + userId + "的时候出bug了");
			e.printStackTrace();
		}
		studentDao.deleteStudentByUserId(userId);
		ResponseResultVo responseResultVo = new ResponseResultVo();
		responseResultVo.setStatus(200);
		responseResultVo.setMsg("delete sucess");
		return responseResultVo;
	}

	public boolean isAvliable(Integer userId)
	{
		String key = STUDENT_INFO + userId.toString();
		try
		{
			String json = jedisClient.get(key);
			if (json != null)
			{
				return false;
			}
		} catch (Exception e)
		{
		}
		Student student = studentDao.getStudentByUserId(userId);
		return student == null ? true : false;
	}

	@SuppressWarnings("static-access")
	@Transactional
	@Override
	public ResponseResultVo addStudent(Student student)
	{
		String key = STUDENT_INFO + student.getUserId().toString();

		if (!isAvliable(Integer.parseInt(student.getUserId().toString())))
		{
			return new ResponseResultVo().build(500, "学号已经存在,请重新输入");
		}
		studentDao.addStudent(student);
		try
		{
			String value = JsonUtils.objectToJson(student);
			jedisClient.set(key, value);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		userService.addUser(student);
		
		return new ResponseResultVo(200, "添加成功");
	}

}
