package com.exm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.exm.dao.RoleDao;
import com.exm.model.Role;
import com.exm.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService
{
	@Value("${SEARCH_URL}")
	private String SEARCH_URL;
	@Autowired
	private RoleDao roleDao;

	@Override
	public Role getRoleById(Integer id)
	{
		return roleDao.getRoleById(id);
	}

}
