//package com.exm.config;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//
//public class CharsetFilter implements HandlerInterceptor
//{
//
//	@Override
//	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception
//	{
//		response.setCharacterEncoding("UTF-8");
//		
//		return false;
//	}
//	@Override
//	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
//			throws Exception
//	{
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
//			throws Exception
//	{
//		// TODO Auto-generated method stub
//		
//	}
//
//
//}
