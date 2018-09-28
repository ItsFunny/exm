package com.exm.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.exm.common.utils.JsonUtils;
import com.exm.dao.CollegeDao;
import com.exm.model.College;
import com.exm.service.CollegeService;

@Service
public class CollegeServiceImpl implements CollegeService
{
	@Value("${REST_URL}")
	private String REST_URL;
	@Value("${SEARCH_URL}")
	private String SEARCH_URL;
	@Autowired
	RestTemplate restTemplate;

	@Autowired
	private CollegeDao collegeDao;

	@Override
	public List<College> getAllColleges()
	{
		
		String url = REST_URL+"/college/all";
//		String url = REST_URL+"rest/college/all";
		List<College> colleges = new ArrayList<College>();
		// 如果从rest服务中获取不到,则自己查询
		try
		{
			String json = restTemplate.getForObject(url, String.class);
			colleges = JsonUtils.jsonToList(json, College.class);

		} catch (Exception e)
		{
			colleges = collegeDao.getAllColleges();
		}

		return colleges;
	}

}
