package com.exm.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.exm.common.model.ResponseResultVo;
import com.exm.dao.StudentDao;
import com.exm.model.Student;
import com.exm.service.StudentService;

@Service
public class StudenServiceImpl implements StudentService
{
	@Value("${REST_URL}")
	private String REST_URL;
	@Value("${SEARCH_URL}")
	private String SEARCH_URL;
	@Autowired
	private StudentDao studentDao;

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public List<Student> getAllStudent()
	{
		return studentDao.getAllStudents();
	}

	@Override
	public Student getStudentByUserId(Integer userId)
	{
//		String url = "http://www.gaosongg.com/rest/student/get/";
		String url = REST_URL+"/student/get/";
		Student student = restTemplate.getForObject(url + userId, Student.class);
		return student;
	}

	@Override
	public ResponseResultVo updateStudent(Student student)
	{
//		String url = "http://www.gaosongg.com/rest/student/update";
		String url = REST_URL+"/student/update";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("student", student);
		ResponseResultVo resultVo = restTemplate.postForObject(url, student, ResponseResultVo.class);
		return resultVo;
	}

	@Override
	public ResponseResultVo deleteStudent(Integer userId)
	{
//		String url = "http://www.gaosongg.com/rest/student/delete/" + userId;
		String url = REST_URL+"/student/delete/" + userId;
		ResponseResultVo responseResultVo = restTemplate.getForObject(url, ResponseResultVo.class);
		return responseResultVo;
	}

	@Override
	public ResponseResultVo addStudent(Student student)
	{
//		String url = "http://www.gaosongg.com/rest/student/add";
		String url = REST_URL+"/student/add";
		ResponseResultVo responseResultVo = restTemplate.postForObject(url, student, ResponseResultVo.class);
		return responseResultVo;
	}

	@Override
	public List<Student> searchStudens(String q)
	{
//		String url="http://www.gaosongg.com:8082/search/student/{q}";
		String url=SEARCH_URL+"/student/{q}";
		@SuppressWarnings("unchecked")
		List<Student> students = restTemplate.getForObject(url, List.class, q);
		return students;
	}
	
	@Override
	public Integer getTotalCount()
	{
		return studentDao.getTotalCount();
	}

	@Override
	public List<Student> getStudentsLimited(Integer start, int end)
	{
		return studentDao.getStudentsLimited(start, end);
	}
}
