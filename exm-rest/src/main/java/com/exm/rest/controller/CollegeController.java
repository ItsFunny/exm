package com.exm.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exm.model.College;
import com.exm.rest.service.CollegeService;

@RestController
@RequestMapping("/college")
public class CollegeController
{
	@Autowired
	private CollegeService collegeService;
	@RequestMapping("/all")
	public List<College> getAllColleges()
	{
		return collegeService.getAllColleges();
	}
	

}
