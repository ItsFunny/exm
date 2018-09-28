package com.exm.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.exm.common.model.ResponseResultVo;
import com.exm.common.model.SearchResultVo;
import com.exm.dao.CourseDao;
import com.exm.model.Course;
import com.exm.service.CourseService;

@Service
public class CourseServiceImpl implements CourseService
{
	@Value("${REST_URL}")
	private String REST_URL;
	@Value("${SEARCH_URL}")
	private String SEARCH_URL;
	@Autowired
	private CourseDao courseDao;

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public List<Course> getAll()
	{
		List<Course> courses = courseDao.getAllCourses();
		return courses;
	}

	@Override
	public void deleteCourseById(Integer courseId)
	{
		String url = REST_URL + "/course/edit/delete";
		// String url=REST_URL+"rest/course/edit/delete";
		// Map<String, Object>params=new HashMap<>();
		// params.put("courseId", courseId);
		// 这里不知道为什么无法通过restful风格的url传递参数
		restTemplate.delete(url + "?courseId=" + courseId);
	}

	@Override
	public void insertCourse(Course course)
	{
		courseDao.insertCourse(course);
	}

	@Override
	public Integer getTotalCount()
	{
		return courseDao.getTotalCount();
	}

	@Override
	public List<Course> getCoursesLimited(Integer start, Integer end)
	{
		return courseDao.getCoursesLimited(start, end);
	}

	@Override
	public Course getCourseById(Integer id)
	{
		// String url = "http://www.gaosongg.com/rest/course/show";
		String url = REST_URL + "/course/show";
		RestTemplate restTemplate = new RestTemplate();
		Course course = restTemplate.getForObject(url + "/" + id, Course.class);
		return course;
	}

	@Override
	public void update(Course course)
	{
		courseDao.updateCourse(course);
		Map<String, Object> params = new HashMap<String, Object>();
		// String url = "http://www.gaosongg.com/rest/course/edit/update";
		String url = REST_URL + "/course/edit/update";
		params.put("course", course);
		restTemplate.put(url, course, params);
	}

	@SuppressWarnings("static-access")
	@Override
	public ResponseResultVo addCourse(Course course) throws Exception
	{
		Course isAvliable = courseDao.getCourseById(Integer.parseInt(course.getId().toString()));
		if (isAvliable == null)
		{
			courseDao.addCourse(course);
			return new ResponseResultVo(200,"添加成功");
		} else
		{
			return new ResponseResultVo().build(500, "课程号已经存在,请更换课程号");
		}
	}

	@Override
	public SearchResultVo searchCourses(Integer pageSize, Integer pageNum, String q)
	{
		// String url="http://www.gaosongg.com:8082/search/query";
		String url = SEARCH_URL + "/query";
		SearchResultVo resultVo = restTemplate
				.getForObject(url + "?q=" + q + "&pageSize=" + pageSize + "&pageNum=" + pageNum, SearchResultVo.class);
		if (resultVo.getObjectList() == null)
		{
			
		}
		return resultVo;

	}

}
