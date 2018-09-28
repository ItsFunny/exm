package com.exm.excetion;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/*
 * 全局异常处理器
 */
public class MyExceptionResolver implements HandlerExceptionResolver
{

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object ob1,
			Exception e)
	{
		ModelAndView modelAndView = null;
		if (e instanceof MyException)
		{
			System.out.println("22222222222222222222222222");
			// modelAndView=new ModelAndView("redirect:/temp");
			// modelAndView.addObject("errpr","myexception");
			return null;
		} else if (e instanceof UnknownAccountException)
		{
			modelAndView = new ModelAndView("login");
			modelAndView.addObject("error", "账户不存在或者密码错误,请重新输入");
			return modelAndView;
		} else if (e instanceof UnauthorizedException)
		{
			return new ModelAndView("redirect:/nopermission");
		}
		return null;
	}

}
