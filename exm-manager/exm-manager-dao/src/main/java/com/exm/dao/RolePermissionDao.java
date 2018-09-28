package com.exm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.exm.model.RolePermission;

@Mapper
public interface RolePermissionDao
{	
	//根据用户的角色获取权限
	@Select("select * from role_permission where role_id=#{roleid}")
	public List<RolePermission> getUserPermissionsByRoleId(Integer roleId);

}
