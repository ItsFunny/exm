package com.exm.search.config;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.alibaba.druid.pool.DruidDataSource;
import com.exm.search.dao.impl.SearchDaoImpl;

@Configuration
@ComponentScan(basePackages = "com.exm.search")
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

	// 配置solrserver单机版
	@Bean(name = "solrServer")
	@Profile("dev")
	public SolrServer solrServer()
	{
		SolrServer solrServer = new HttpSolrServer("http://192.168.163.154:8080/solr");
		return solrServer;
	}

	@Bean(name = "solrServer")
	@Profile("prod")
	public SolrServer solrServer_server()
	{
		SolrServer solrServer = new HttpSolrServer("http://59.110.239.161:8082/solr");
		return solrServer;
	}

	@Bean
	public SearchDaoImpl searchDao()
	{
		SearchDaoImpl searchDaoImpl = new SearchDaoImpl();
		return searchDaoImpl;
	}

}
