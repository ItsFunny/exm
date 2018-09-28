package com.exm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.exm.model.College;

@Mapper
public interface CollegeDao
{
	@Select("select * from college")
	List<College> getAllColleges();
	
	
}
