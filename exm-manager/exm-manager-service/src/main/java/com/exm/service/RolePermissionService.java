package com.exm.service;

import java.util.List;

import com.exm.model.RolePermission;

public interface RolePermissionService
{
	List<RolePermission> getUserPermissiosByRoleId(Integer roleId);
}
