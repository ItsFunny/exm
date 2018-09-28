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
import com.exm.model.Course;
import com.exm.model.Selectedcourse;
import com.exm.service.SelectedCourseService;

@Service
public class SelectedCourseServiceImpl implements SelectedCourseService
{
	@Value("${REST_URL}")
	private String REST_URL;

	@Value("${SEARCH_URL}")
	private String SEARCH_URL;
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private CourseDao courseDao;

	@Override
	public ResponseResultVo addSelectedCourse(Integer courseId, Integer userId)
	{
		String url = REST_URL+"/student/course/select";
		Selectedcourse selectedcourse = new Selectedcourse();
		selectedcourse.setCourseId(courseId);
		selectedcourse.setStudentId(userId);
		ResponseResultVo resultVo = restTemplate.postForObject(url, selectedcourse, ResponseResultVo.class);
		return resultVo;
	}

	@Override
	public List<List<Course>> getStudentAllCourses(Integer userId)
	{
		String url = REST_URL + "/student/course/selected/show/" + userId;
		System.out.println(url);
		String jsonString = restTemplate.getForObject(url, String.class);
		List<Selectedcourse> selectedCourses = JsonUtils.jsonToList(jsonString, Selectedcourse.class);
		List<List<Course>> allCourses = new ArrayList<>();
		List<Course> courses = new ArrayList<Course>();
		List<Course> completedCourses = new ArrayList<>();
		for (Selectedcourse selectedcourse : selectedCourses)
		{
			if (selectedcourse.getMark() != null)
			{
				Course course = courseDao.getCourseById(selectedcourse.getCourseId());
				course.setMark(selectedcourse.getMark());
				completedCourses.add(course);
			}
			Course course = courseDao.getCourseById(selectedcourse.getCourseId());
			courses.add(course);
		}
		// 0为所有
		// 1为修完了的
		allCourses.add(courses);
		allCourses.add(completedCourses);
		return allCourses;
	}

	@Override
	public ResponseResultVo updateStudentMark(Integer mark, Integer studentId,Integer courseId)
	{
		String url=REST_URL+"/teacher/mark/update"+"?studentId={studentId}&mark={mark}&courseId={courseId}";
		Map<String, Object>params=new HashMap<String, Object>();
		params.put("studentId", studentId);
		params.put("mark", mark);
		params.put("courseId", courseId);
		ResponseResultVo resultVo = restTemplate.postForObject(url, null, ResponseResultVo.class, params);
//		ResponseResultVo resultVo = restTemplate.getForObject(url, ResponseResultVo.class, params);
		return resultVo;
	}

}
