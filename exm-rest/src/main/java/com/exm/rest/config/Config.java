package com.exm.rest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.alibaba.druid.pool.DruidDataSource;
import com.exm.rest.dao.JedisClient;

import redis.clients.jedis.JedisPool;

@Configuration
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

	@Bean(name = "jedisPool")
	@Profile("dev")
	public JedisPool jedisPool()
	{
		JedisPool jedisPool = new JedisPool("localhost", 6379);
		return jedisPool;
	}

	@Bean(name = "jedisPool")
	@Profile("prod")
	public JedisPool jedisPoolServer()
	{
		JedisPool jedisPool = new JedisPool("59.110.239.161", 6379);
		return jedisPool;
	}

	@Bean
	public JedisClient jedisClient()
	{
		return new JedisClient();
	}
}
