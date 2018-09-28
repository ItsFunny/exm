package com.exm.search.dao;


import org.apache.solr.client.solrj.SolrQuery;

import com.exm.common.model.SearchResultVo;


public interface SearchDao
{
	SearchResultVo search(SolrQuery query) throws Exception;
}
