package com.exm.search.service.impl;

import org.apache.solr.client.solrj.SolrQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exm.common.model.SearchResultVo;
import com.exm.search.dao.SearchDao;
import com.exm.search.service.SearchService;

@Service
public class SearchServiceImpl implements SearchService
{
	@Autowired
	private SearchDao searchDao;

	@Override
	public SearchResultVo search(String queryString, int page, int rows) throws Exception
	{
		// 创建查询对象
		SolrQuery query = new SolrQuery();
		// 设置查询条件
		query.setQuery(queryString);
//		query.setFields("item_keywords");
//		query.set("q", "item_keywords:3");
		// 设置分页
		query.setStart((page - 1) * rows);
		query.setRows(rows);
		// 设置默认搜素域
		query.set("df", "item_keywords");
//		query.set
		// 设置高亮显示
		query.setHighlight(true);
		query.addHighlightField("course_name");
		query.setHighlightSimplePre("<em style=\"color:red\">");
		query.setHighlightSimplePost("</em>");

		// 执行查询
		SearchResultVo searchResult = searchDao.search(query);
		// 计算查询结果总页数
		long recordCount = searchResult.getRecordCount();

		long pageCount = recordCount / rows;

		if (recordCount % rows > 0)
		{
			pageCount++;
		}
		searchResult.setMaxPage(pageCount);
		searchResult.setCurPage(page);
		return searchResult;
	}

}