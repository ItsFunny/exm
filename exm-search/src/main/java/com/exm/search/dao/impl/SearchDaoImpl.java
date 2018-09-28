package com.exm.search.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.exm.common.model.SearchResultVo;
import com.exm.model.Course;
import com.exm.search.dao.SearchDao;

@Component
public class SearchDaoImpl implements SearchDao
{
	@Autowired
	private SolrServer solrServer;

	@Override
	public SearchResultVo search(SolrQuery query) throws Exception
	{
		// 返回值对象
		SearchResultVo result = new SearchResultVo();

		// 根据查询条件查询索引库
		QueryResponse queryResponse = solrServer.query(query);
		
		// 取查询结果
		SolrDocumentList solrDocumentList = queryResponse.getResults();
	
		//取得查询结果的总数量
		result.setRecordCount(solrDocumentList.getNumFound());
		//课程列表
		List<Course>courses=new ArrayList<>();
		//高亮显示
//		Map<String, Map<String, List<String>>>highLighting=queryResponse.getHighlighting();
		//取课程列表
		for (SolrDocument solrDocument : solrDocumentList)
		{
			Course course=new Course();
			course.setId(Long.parseLong( solrDocument.get("id").toString()));
			//取高亮显示的结构
//			List<String>list=highLighting.get(solrDocument.get("id")).get("course_name");
//			String title="";
//			if (list != null && list.size() > 0)
//			{
//				title = list.get(0);
//			} else
//			{
//				title = (String) solrDocument.get("course_name");
//			}
//			document.addField("course_namae", course.getCourseName());
//			document.addField("teacher_id", course.getTeacherId());
//			document.addField("course_time", course.getCourseTime());
//			document.addField("class_room", course.getClassRoom());
//			document.addField("course_week", course.getCourseWeek());
//			document.addField("course_type", course.getCourseType());
//			document.addField("score", course.getScore());
			
			course.setCourseName((String) solrDocument.get("course_name"));
			course.setTeacherId(Integer.parseInt( solrDocument.get("teacher_id").toString()));
			course.setCourseTime((String) solrDocument.get("course_time"));
			course.setClassRoom((String) solrDocument.get("class_room"));
			course.setCourseWeek(Integer.parseInt( solrDocument.get("course_week").toString()));
			course.setCourseType((String) solrDocument.get("course_type"));
			course.setScore(Integer.parseInt( solrDocument.get("score").toString()));
			//添加到课程列表上
			courses.add(course);
			
		}
		result.setObjectList(courses);
		return result;
	}

}
