package com.exm.rest.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.exm.common.model.ResponseResultVo;
import com.exm.common.utils.JsonUtils;
import com.exm.dao.SelectedCourseDao;
import com.exm.model.Course;
import com.exm.model.Selectedcourse;
import com.exm.rest.dao.JedisClient;
import com.exm.rest.service.CourseService;
import com.exm.rest.service.SelectedCourseService;

@Service
public class SelectedCourseServiceImpl implements SelectedCourseService
{
	private Logger logger=LoggerFactory.getLogger(SelectedCourseServiceImpl.class);
	@Value("${STUDENT_SELECTED_COURSE_INTO}")
	private String STUDENT_SELECTED_COURSE_INTO;
	@Value("${COURSE_INFO}")
	private String COURSE_INFO;

	@Autowired
	private SelectedCourseDao selectedCourseDao;
	@Autowired
	private CourseService courseService;

	@Autowired
	private JedisClient jedisClient;

	public boolean isAvliable(Integer courseId, Integer userId)
	{
		String studentkey = STUDENT_SELECTED_COURSE_INTO + ":" + userId.toString() + ":" + "selected:"
				+ courseId.toString();
		Selectedcourse ishaveThisCourse = selectedCourseDao.isHaveThisCourse(courseId, userId);
		if (ishaveThisCourse != null)
		{
			// 这里面的redis操作其实感觉是多余的,浪费性能而已,又好像是必要的
			// 万一redis挂了呢?,毕竟不是集群

			try
			{
				Course course = courseService.getCourseById(ishaveThisCourse.getCourseId());
				String jsonOfCourse = jedisClient.get(studentkey);
				if (jsonOfCourse == null)
				{
					String value = JsonUtils.objectToJson(course);
					jedisClient.set(studentkey, value);
				}
			} catch (Exception e)
			{
				e.printStackTrace();
			}
			return false;
		} else
		{
			try
			{
				String jsonOfCourse = jedisClient.get(studentkey);
				if (jsonOfCourse != null)
				{
					jedisClient.del(studentkey);
				}
			} catch (Exception e)
			{
				e.printStackTrace();
			}
			return true;
		}
	}

	@Override
	public ResponseResultVo addSelectedCourse(Selectedcourse selectedcourse)
	{
		Integer userId = selectedcourse.getStudentId();
		Integer courseId = selectedcourse.getCourseId();
		String studentkey = STUDENT_SELECTED_COURSE_INTO + ":" + userId.toString() + "selected:" + courseId.toString();
		String courseKey = COURSE_INFO + ":" + courseId.toString();
		if (isAvliable(courseId, userId))
		{
			try
			{
				String jsonOfCourse = jedisClient.get(courseKey);
				if (jsonOfCourse == null)
				{
					Course course = courseService.getCourseById(courseId);
					String value = JsonUtils.objectToJson(course);
					jedisClient.set(courseKey, value);
				}
				jedisClient.set(studentkey, jsonOfCourse);
			} catch (Exception e)
			{
				e.printStackTrace();
			}
			selectedCourseDao.addSelectedCourse(selectedcourse);
			return new ResponseResultVo(200, "选课成功,请前往我的选课列表中查看");
		} else
		{
			return new ResponseResultVo(500, "你已经选过这门课了,不能再选了");
		}
	}

	@Override
	public List<Selectedcourse> getStudentSelectedCourses(Integer userId)
	{
		
		String key = STUDENT_SELECTED_COURSE_INTO + ":" + userId.toString() + ":" + "selected";
		List<Selectedcourse> selectedcourses = new ArrayList<Selectedcourse>();
		try
		{
			String json = jedisClient.get(key);
			if (json != null)
			{
				selectedcourses = JsonUtils.jsonToList(json, Selectedcourse.class);
				return selectedcourses;
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		selectedcourses = selectedCourseDao.getStudentSelectedcoursesByUserId(userId);
		try
		{
			JSONArray jsonArray = new JSONArray();
			jsonArray.put(selectedcourses);
			JSONArray array = (JSONArray) jsonArray.get(0);
			String value = array.toString();
			jedisClient.set(key, value);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return selectedcourses;
	}

	@Override
	public ResponseResultVo updateStudentMark(Integer mark, Integer studentId, Integer courseId)
	{
		//关于成绩正确性(是否为数字啦,大小范围等)都交个前端的js来进行判断
		String studentkey = STUDENT_SELECTED_COURSE_INTO + ":" + studentId.toString() + ":" + "selected:"
				+ courseId.toString();
		selectedCourseDao.updateMarkOfStudentByStudentId(mark, studentId,courseId);
		Course course = courseService.getCourseById(courseId);
		course.setMark(mark);
		String value = JsonUtils.objectToJson(course);
		try
		{
			String json = jedisClient.get(studentkey);
			if(json!=null)
			{
				jedisClient.del(studentkey);
			}
			jedisClient.set(studentkey, value);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return new ResponseResultVo(200, "学生的成绩修改成功");

	}
}
