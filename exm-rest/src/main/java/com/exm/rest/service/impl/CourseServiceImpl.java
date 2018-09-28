package com.exm.rest.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.exm.common.model.ResponseResultVo;
import com.exm.common.utils.JsonUtils;
import com.exm.dao.CourseDao;
import com.exm.model.Course;
import com.exm.rest.dao.JedisClient;
import com.exm.rest.service.CourseService;

@Service
public class CourseServiceImpl implements CourseService
{
	@Value("${COURSE_INFO}")
	private String COURSE_INFO;
	@Value("TEACHER_INFO")
	private String TEACHER_INFO;

	@Autowired
	private CourseDao courseDao;

	@Autowired
	private JedisClient jedisClient;

	@Override
	public Course getCourseById(Integer courseId)
	{
		try
		{
			String json = jedisClient.get(COURSE_INFO + ":" + courseId.toString());
			if (!StringUtils.isEmpty(json))
			{
				Course course = JsonUtils.jsonToPojo(json, Course.class);
				return course;
			}
		} catch (Exception e)
		{
		}
		Course course = courseDao.getCourseById(courseId);
		String value = JsonUtils.objectToJson(course);
		try
		{
			jedisClient.set(COURSE_INFO + ":" + course.getId().toString(), value);
		} catch (Exception e)
		{
			e.printStackTrace();
		}

		return course;
	}

	@Override
	public void update(Course course)
	{
		try
		{
			String json = JsonUtils.objectToJson(course);
			jedisClient.set(COURSE_INFO + ":" + course.getId().toString(), json);
		} catch (Exception e)
		{
			// 这里可以返回错误的信息
			e.printStackTrace();
		}
	}

	@Override
	public void deleteCourse(Integer id)
	{
		courseDao.deleteById(id);
		String key = COURSE_INFO + ":" + id.toString();
		try
		{
			String json = jedisClient.get(key);
			if (json != null)
			{
				jedisClient.del(key);
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public List<Course> getCoursesByTeacherId(Integer teacherId)
	{
		String key = TEACHER_INFO + ":" + teacherId.toString() + ":" + "classes";
		List<Course> courses = new ArrayList<Course>();
		try
		{
			String json = jedisClient.get(key);
			if (json != null)
			{
				courses = JsonUtils.jsonToList(json, Course.class);
				return courses;
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		courses = courseDao.getCoursesByTeacherId(teacherId);

		try
		{
			JSONArray jsonArray = new JSONArray(courses);

			String value = jsonArray.toString();
			jedisClient.set(key, value);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return courses;
	}

	@Override
	public int getTeacherAllCourses(Integer teacherId)
	{
		return courseDao.getTeacherCoursesTotalCount(teacherId);
	}

}
