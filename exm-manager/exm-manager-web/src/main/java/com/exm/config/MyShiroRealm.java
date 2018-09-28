package com.exm.config;

import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.exm.excetion.MyException;
import com.exm.model.RolePermission;
import com.exm.model.User;
import com.exm.service.RolePermissionService;
import com.exm.service.RoleService;
import com.exm.service.UserService;

public class MyShiroRealm extends AuthorizingRealm
{
	@Autowired
	private UserService userService;

	@Autowired
	private RolePermissionService rolePermissionService;

	@Autowired
	private RoleService roleService;

	// 身份验证
	// 返回的是认证信息
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException
	{
		String username = (String) token.getPrincipal();
		Integer userid = Integer.parseInt(username);
		String password = null;
		try
		{
			password = new String((char[]) token.getCredentials());
		} catch (Exception e)
		{
			e.printStackTrace();
		}

		User user = userService.getUserByUserId(userid);
		if(user==null)
		{
		}
		if (!userService.checkUser(userid, password))
		{
			throw new AuthenticationException();
		}
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user, user.getPassword(),
				this.getName());
		return authenticationInfo;
	}

	// 返回的是授权信息
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals)
	{
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		User user = (User) principals.getPrimaryPrincipal();
		authorizationInfo.addRole(roleService.getRoleById(user.getRoleId()).getRoleName());
		List<RolePermission> rolePermissions = rolePermissionService.getUserPermissiosByRoleId(user.getRoleId());
		for (RolePermission rolePermission : rolePermissions)
		{
			authorizationInfo.addStringPermission(rolePermission.getPermissions());
		}
		return authorizationInfo;
	}

}
