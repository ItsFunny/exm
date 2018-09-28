package com.exm.common.model;

import java.util.List;

public class SearchResultVo
{


	@Override
	public String toString()
	{
		return "SearchResultVo [objectList=" + objectList + ", recordCount=" + recordCount + ", maxPage=" + maxPage
				+ ", curPage=" + curPage + "]";
	}

	// 商品列表
	private List<?> objectList;
	// 总记录数
	private long recordCount;
	// 总页数
	private long maxPage;
	// 当前页
	private long curPage;

	
	public long getMaxPage()
	{
		return maxPage;
	}

	public void setMaxPage(long maxPage)
	{
		this.maxPage = maxPage;
	}

	public List<?> getObjectList()
	{
		return objectList;
	}

	public void setObjectList(List<?> objectList)
	{
		this.objectList = objectList;
	}

	public long getRecordCount()
	{
		return recordCount;
	}

	public void setRecordCount(long recordCount)
	{
		this.recordCount = recordCount;
	}

	

	public long getCurPage()
	{
		return curPage;
	}

	public void setCurPage(long curPage)
	{
		this.curPage = curPage;
	}
}
