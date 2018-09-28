package com.exm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.exm.dao.RolePermissionDao;
import com.exm.model.RolePermission;
import com.exm.service.RolePermissionService;

@Service
public class RolePermissionServiceImpl implements RolePermissionService
{

	@Value("${SEARCH_URL}")
	private String SEARCH_URL;
	@Autowired
	private RolePermissionDao rolePermissionDao;
	@Override
	public List<RolePermission> getUserPermissiosByRoleId(Integer roleId)
	{
		return rolePermissionDao.getUserPermissionsByRoleId(roleId);
	}

}
