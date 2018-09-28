package com.exm.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.exm.common.model.ResponseResultVo;
import com.exm.model.RolePermission;
import com.exm.model.User;
import com.exm.service.RolePermissionService;
import com.exm.service.UserService;

@RestController
public class EntryController
{

	@Autowired
	private RolePermissionService rolePermissionService;
	@Autowired
	private UserService userService;

	@RequestMapping("/login")
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response)
	{
		return new ModelAndView("login");
	}
	@RequestMapping("/user/register/check")
	public void checkUserName(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		PrintWriter out=response.getWriter();
		String username=request.getParameter("username");
		System.out.println("username="+username);
		if(username==null||username.equals(""))
		{
			out.print("用户名不可为空");
		}
		else if("sa".equals(username))
		{
			out.print("该用户名已经存在");
		}
		else {
			out.print("该用户可以注册");
		}
	}

	@RequestMapping("/index")
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response)
	{
		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getPrincipal();
		Map<String, Object> params = new HashMap<String, Object>();
		List<RolePermission> permissions = rolePermissionService.getUserPermissiosByRoleId(user.getRoleId());
		params.put("permissions", permissions);
		ModelAndView modelAndView = new ModelAndView("main", params);
		return modelAndView;

	}

	@RequestMapping("/nopermission")
	public ModelAndView noPermission()
	{
		ModelAndView modelAndView = new ModelAndView("redirect:/temp");
		modelAndView.addObject("error", "对不起,你没有权限");
		return modelAndView;
	}

	@RequestMapping("/change/pwd")
	public ModelAndView changeMyPwd(HttpServletRequest request, HttpServletResponse response)
	{
		return new ModelAndView("changePwd");
	}

	@RequestMapping("/change/pwd.do")
	public ModelAndView doChangeMyPwd(HttpServletRequest request, HttpServletResponse response)
	{
		String oldPwd = request.getParameter("oldPwd");
		String newPwd = request.getParameter("newPwd");
		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getPrincipal();
		ResponseResultVo resultVo = userService.updatePwd(newPwd, oldPwd, user);
		ModelAndView modelAndView = new ModelAndView("redirect:/temp");
		if (resultVo.getStatus() == 200)
		{
			modelAndView.addObject("error", "update sucess please relogin");
			modelAndView.addObject("redirect", "/logout");
		} else
		{
			modelAndView.addObject("error", resultVo.getMsg());
			modelAndView.addObject("redirect", resultVo.getData());
		}
		return modelAndView;
	}

	/*
	 * 修改用户的密码,只有管理员才可以
	 */
	@RequestMapping("/user/change/pwd")
	@RequiresRoles(value = "admin")
	public ModelAndView changePwd(HttpServletRequest request, HttpServletResponse response)
	{
//		Subject subject = SecurityUtils.getSubject();
//		if(subject.hasRole("admin"))
//		{
			ModelAndView modelAndView = new ModelAndView("changeUserPwd");
			return modelAndView;	
//		}
//		return new ModelAndView("redirect:/nopermission");
		
	}

	@RequestMapping("/user/change/pwd.do")
	@RequiresPermissions(value = "edit:changeUserPwd")
	public ModelAndView doChangePwd(HttpServletRequest request, HttpServletResponse response)
	{
		int userId = Integer.parseInt(request.getParameter("userId"));
		String newPwd = request.getParameter("password");
		ResponseResultVo resultVo = userService.doChangePwd(userId, newPwd);
		Map<String, Object> params = new HashMap<>();
		params.put("error", resultVo.getMsg());
		params.put("redirect", resultVo.getData());
		ModelAndView modelAndView = new ModelAndView("redirect:/temp", params);
		return modelAndView;
	}

	@RequestMapping("/temp")
	public ModelAndView tempAddr(@RequestParam(name = "redirect", defaultValue = "/index") String redirect,
			HttpServletRequest request, HttpServletResponse response, Model model) throws UnsupportedEncodingException
	{
		String error = new String(request.getParameter("error").getBytes("iso-8859-1"), "utf-8");
		Map<String, Object> params = new HashMap<>();
		params.put("error", error);
		params.put("redirect", redirect);
		ModelAndView modelAndView = new ModelAndView("temp", params);
		return modelAndView;
	}

}
