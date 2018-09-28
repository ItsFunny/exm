package com.exm.rest.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.exm.common.utils.JsonUtils;
import com.exm.dao.CollegeDao;
import com.exm.model.College;
import com.exm.rest.dao.JedisClient;
import com.exm.rest.service.CollegeService;

@Service
public class CollegeServiceImpl implements CollegeService
{
	@Value("${COLLEGE_INFO}")
	private String COLLEGE_INFO;
	@Autowired
	private CollegeDao collegeDao;
	@Autowired
	private JedisClient jedisClient;

	@Override
	public List<College> getAllColleges()
	{
		List<College> colleges=new ArrayList<>();
		try
		{
			String json=jedisClient.get(COLLEGE_INFO);
			if(!StringUtils.isEmpty(json))
			{
				colleges=JsonUtils.jsonToList(json, College.class);
				return colleges;
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		colleges=collegeDao.getAllColleges();
		try
		{
			String value=JsonUtils.objectToJson(colleges);
			jedisClient.set(COLLEGE_INFO, value);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return colleges;
	}

	
	
}
