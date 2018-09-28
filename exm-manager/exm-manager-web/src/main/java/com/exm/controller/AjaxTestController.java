package com.exm.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/static")
public class AjaxTestController
{
	private Logger logger=LoggerFactory.getLogger(AjaxTestController.class);
	@RequestMapping("/test")
	public void ajaxTest(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		System.out.println(request.getParameter("a"));
		System.out.println(request.getParameter("b"));
		System.out.println("ajax进入了测试");
		logger.info("[ajax测试]成功进入了服务端");
		PrintWriter out=response.getWriter();
		out.print("aaaa");
	}
	
	@RequestMapping("/test/xml")
	public void xmlTest(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		PrintWriter out=response.getWriter();
		out.println("<china>");
		out.println("<province name='吉林省'>");
		out.println("<city>a</city>");
		out.println("<city>b</city>");
		out.println("<city>c</city>");
		out.println("<province name='辽宁省'>");
		out.println("<city>d</city>");
		out.println("<city>e</city>");
		out.println("<city>f</city>");
		out.println("</china>");
	}
	@RequestMapping("/test/json")
	public void jsonTest(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		PrintWriter out=response.getWriter();
		String json="[{'province':'吉林省'},{'province':'辽宁省'},{'province':'山东省'}]";
		out.println(json);
	}
	@RequestMapping("/jquery/ajax/text")
	public void jqueryTest(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		System.out.println(request.getParameter("username"));
		System.err.println(request.getMethod());
		PrintWriter out=response.getWriter();
		out.println("ok");
	}
	
}
