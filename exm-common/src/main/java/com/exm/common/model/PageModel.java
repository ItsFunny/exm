package com.exm.common.model;

import java.util.List;

public class PageModel<T>
{

	private int pageSize;
	private int pageNum;
	private int totalCount;
	private int maxPage;

	private List<T> data;

	public PageModel(int pageSize, int pageNum, int totalCount, int maxPage, List<T> data)
	{
		super();
		this.pageSize = pageSize;
		this.pageNum = pageNum;
		this.totalCount = totalCount;
		this.maxPage = totalCount % pageSize == 0 ? totalCount / pageSize : (totalCount / pageSize) + 1;
		this.data = data;
	}

	public PageModel()
	{
		super();
	}

	public List<T> getData()
	{
		return data;
	}

	public void setData(List<T> data)
	{
		this.data = data;
	}

	public int getPageSize()
	{
		return pageSize;
	}

	public void setPageSize(int pageSize)
	{
		this.pageSize = pageSize;
	}

	public int getPageNum()
	{
		return pageNum;
	}

	public void setPageNum(int pageNum)
	{
		this.pageNum = pageNum;
	}

	public int getTotalCount()
	{
		return totalCount;
	}

	public void setTotalCount(int totalCount)
	{
		this.totalCount = totalCount;
	}

	public int getMaxPage()
	{
		return maxPage;
	}

	public void setMaxPage(int maxPage)
	{
		this.maxPage = maxPage;
	}

	@Override
	public String toString()
	{
		return "PageModel [pageSize=" + pageSize + ", pageNum=" + pageNum + ", totalCount=" + totalCount + ", maxPage="
				+ maxPage + ", data=" + data + "]";
	}

}
