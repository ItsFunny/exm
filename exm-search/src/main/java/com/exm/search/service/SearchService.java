package com.exm.search.service;

import com.exm.common.model.SearchResultVo;

/*
 * 
 */
public interface SearchService
{
	SearchResultVo search(String queryString, int page, int pageSize) throws Exception;
}
