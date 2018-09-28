package com.exm.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.exm.common.model.ResponseResultVo;
import com.exm.common.utils.JsonUtils;
import com.exm.dao.CourseDao;
import com.exm.dao.TeacherDao;
import com.exm.model.Course;
import com.exm.model.Teacher;
import com.exm.service.TeacherService;

@Service
public class TeacherServiceImpl implements TeacherService
{
	@Value("${REST_URL}")
	private String REST_URL;
	@Value("${SEARCH_URL}")
	private String SEARCH_URL;
	@Autowired
	private TeacherDao teacherDao;
	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private CourseDao courseDao;
	@Override
	public List<Teacher> getAllTeachers()
	{
		// String url = "http://www.gaosongg.com/rest/teacher/all";
		String url = REST_URL + "/teacher/all";
		List<Teacher> teachers = new ArrayList<Teacher>();

		try
		{
			String json = restTemplate.getForObject(url, String.class);
			teachers = JsonUtils.jsonToList(json, Teacher.class);
		} catch (Exception e)
		{
			teachers = teacherDao.getAllTeachers();
		}

		return teachers;
	}

	@Override
	public ResponseResultVo deleteTeacher(Integer userId)
	{
		// String url = "http://www.gaosongg.com/rest/teacher/delete/" + userId;
		String url = REST_URL + "/teacher/delete/" + userId;
		ResponseResultVo resultVo = restTemplate.getForObject(url, ResponseResultVo.class);
		return resultVo;
	}

	@Override
	public ResponseResultVo addTeacher(Teacher teacher)
	{
		// String url = "http://www.gaosongg.com/rest/teacher/add";
		String url = REST_URL + "/teacher/add";
		ResponseResultVo resultVo = restTemplate.postForObject(url, teacher, ResponseResultVo.class);
		return resultVo;
	}

	@Override
	public ResponseResultVo searchTeachers(String q)
	{
		// String url = "http://www.gaosongg.com:8082/search/teacher/" + q;
		String url = SEARCH_URL + "/teacher/" + q;
		ResponseResultVo responseResultVo = restTemplate.getForObject(url, ResponseResultVo.class);
		return responseResultVo;
	}

	@Override
	public List<Teacher> getTeachersLimited(Integer start, Integer end)
	{
		return teacherDao.getTeachersLimited(start, end);
	}

	@Override
	public Integer getTotalCount()
	{
		return teacherDao.getTotal();
	}

	@Override
	public List<Course> getTeacherClasses(Integer teacherId)
	{
		String url = REST_URL + "/teacher/classes/show/" + teacherId;
		String json = restTemplate.getForObject(url, String.class);
		List<Course> courses = JsonUtils.jsonToList(json, Course.class);
		return courses;
	}

	@Override
	public Integer getTeacherAllCoursesTotalCount(Integer teacherId)
	{
//		String url = REST_URL + "/course/teacher/all/{teacherId}";
//		Map<String, Object> params = new HashMap<String, Object>();
//		params.put("teacherId", teacherId);
//		Integer count = restTemplate.postForObject(url, null, Integer.class, params);
		return courseDao.getTeacherCoursesTotalCount(teacherId);
	}
}
