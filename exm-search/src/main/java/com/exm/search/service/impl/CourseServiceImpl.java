package com.exm.search.service.impl;

import java.util.List;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exm.common.model.SearchResultVo;
import com.exm.dao.CourseDao;
import com.exm.model.Course;
import com.exm.search.service.CourseService;

@Service
public class CourseServiceImpl implements CourseService
{
	@Autowired
	private CourseDao courseDao;
	@Autowired
	private SolrServer solrServer;

	@Override
	public SearchResultVo importAll()
	{
		// 查询课程列表
		List<Course> courses = courseDao.getAllCourses();
		// 把课程信息写入solr库
		for (Course course : courses)
		{
			// 创建一个solrdocument对象
			SolrInputDocument document = new SolrInputDocument();
			document.addField("id", course.getId());
			document.addField("course_name", course.getCourseName());
			document.addField("teacher_id", course.getTeacherId());
			document.addField("course_time", course.getCourseTime());
			document.addField("class_room", course.getClassRoom());
			document.addField("course_week", course.getCourseWeek());
			document.addField("course_type", course.getCourseType());
			document.addField("score", course.getScore());
			// 将文档写入索引库
			try
			{
				solrServer.add(document);
			} catch (Exception e)
			{
				e.printStackTrace();
			}
			// 提交修改,不提交是不会生效的
			try
			{
				solrServer.commit();
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		}

		return null;
	}

}
