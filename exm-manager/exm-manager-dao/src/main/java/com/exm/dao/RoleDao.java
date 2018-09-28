package com.exm.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.exm.model.Role;

@Mapper
public interface RoleDao
{
	@Select("select * from role where id=#{id}")
	Role getRoleById(Integer id);
}
