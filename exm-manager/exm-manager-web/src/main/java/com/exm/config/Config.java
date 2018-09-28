package com.exm.config;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

import javax.activation.DataSource;
import javax.servlet.Filter;

import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.aspectj.lang.annotation.Before;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.core.convert.ConversionService;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.velocity.VelocityConfigurer;
import org.springframework.web.servlet.view.velocity.VelocityViewResolver;

import com.alibaba.druid.pool.DruidDataSource;
import com.exm.excetion.MyExceptionResolver;

@SuppressWarnings(
{ "unused", "deprecation" })
@org.springframework.context.annotation.Configuration
@org.springframework.context.annotation.ComponentScan(basePackages = "com.exm.config")
public class Config
{
	@Bean(name = "dataSource")
	@Profile(value = "dev")
	public DruidDataSource dataSource()
	{
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setUsername("root");
		dataSource.setPassword("123");
		dataSource.setUrl("jdbc:mysql://localhost/examination?characterEncoding=utf-8");
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setMaxActive(10);
		dataSource.setMinIdle(5);
		return dataSource;
	}

	@Bean(name = "dataSource")
	@Profile("prod")
	public DruidDataSource dataSourceProd()
	{
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://59.110.239.161/examination?characterEncoding=utf-8");
		dataSource.setUsername("root");
		dataSource.setPassword("123456");
		dataSource.setMaxActive(10);
		dataSource.setMinIdle(5);
		return dataSource;
	}

	@Bean
	public RestTemplate restTemplate()
	{
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate;
	}

	// shiro
	@Bean
	public LifecycleBeanPostProcessor lifecycleBeanPostProcessor()
	{
		return new LifecycleBeanPostProcessor();
	}

	@Bean
	public MyShiroRealm shiroRealm()
	{
		MyShiroRealm myShiroRealm = new MyShiroRealm();
		return myShiroRealm;
	}

	@Bean
	public OnLoginFilter onLoginFilter()
	{
		return new OnLoginFilter();
	}

	@Bean(name = "shiroFilter")
	public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager)
	{
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

		shiroFilterFactoryBean.setSecurityManager(securityManager);
		// 自定义filter
		
		Map<String, Filter> filterMap = shiroFilterFactoryBean.getFilters();
		OnLoginFilter onLoginFilter = new OnLoginFilter();
		filterMap.put("loginFilter", onLoginFilter);
		shiroFilterFactoryBean.setFilters(filterMap);
		// 拦截器
		Map<String, String> filterChainDefinationMap = new LinkedHashMap<String, String>();
		filterChainDefinationMap.put("/static/**", "anon");
		filterChainDefinationMap.put("/css/**", "anon");
		filterChainDefinationMap.put("/js/**", "anon");
		filterChainDefinationMap.put("/images/**", "anon");
		filterChainDefinationMap.put("/fonts/**", "anon");
		filterChainDefinationMap.put("/logout", "logout");
		filterChainDefinationMap.put("/login", "loginFilter");
//		filterChainDefinationMap.put("/**", "authc");

		shiroFilterFactoryBean.setLoginUrl("/login");
		shiroFilterFactoryBean.setSuccessUrl("/index");
		shiroFilterFactoryBean.setUnauthorizedUrl("/nopermission");
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinationMap);
		return shiroFilterFactoryBean;
	}

	@Bean
	public SecurityManager securityManager()
	{
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setRealm(shiroRealm());
		return securityManager;
	}

	@Bean(name = "defaultAdvisorAutoProxyCreator")
	public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator()
	{
		DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
		defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
		return defaultAdvisorAutoProxyCreator;
	}

	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager)
	{
		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();

		authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
		return authorizationAttributeSourceAdvisor;
	}

	// 采用javaconfig的形式

	@Bean
	public VelocityConfigurer velocityConfigurer()
	{
		VelocityConfigurer velocityConfigurer = new VelocityConfigurer();
		velocityConfigurer.setResourceLoaderPath("WEB-INF/templates");
		Properties properties = new Properties();
		properties.setProperty("input.encoding", "utf-8");
		properties.setProperty("output.encoding", "utf-8");
		velocityConfigurer.setVelocityProperties(properties);
		return velocityConfigurer;
	}

	@Bean
	public ViewResolver viewResolver()
	{
		VelocityViewResolver velocityViewResolver = new VelocityViewResolver();
		velocityViewResolver.setSuffix(".vm");
		velocityViewResolver.setContentType("text/html;charset=utf-8");
		return velocityViewResolver;
	}

	// 自定义参数绑定组件
	// @Bean
	// public ConversionService conversionService()
	// {
	// ConversionService conversionService=new conversion
	// }
	// 全局异常处理器
	@Bean
	public MyExceptionResolver myExceptionResolver()
	{
		return new MyExceptionResolver();
	}

}
