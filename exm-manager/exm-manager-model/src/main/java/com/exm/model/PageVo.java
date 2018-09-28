package com.exm.model;

public class PageVo
{



	private Integer pageSize;
	private Integer pageNum;
	private Integer maxPage;

	private Integer totalCount;
	
	public PageVo(Integer totalCount,Integer pageSize, Integer pageNum)
	{
		super();
		this.totalCount=totalCount;
		this.pageSize = pageSize;
		this.pageNum = pageNum;
		this.maxPage=this.totalCount%this.pageSize==0?this.totalCount/this.pageSize:(this.totalCount/this.pageSize)+1;
	}

	public PageVo(int totalCount,String pageSize, String pageNum)
	{
		super();
		this.totalCount=totalCount;
		this.pageSize = Integer.parseInt(pageSize);
		this.pageNum = Integer.parseInt(pageNum);
		this.maxPage=this.totalCount%this.pageSize==0?this.totalCount/this.pageSize:(this.totalCount/this.pageSize)+1;
	}

	public Integer getTotalCount()
	{
		return totalCount;
	}

	public void setTotalCount(Integer totalCount)
	{
		this.totalCount = totalCount;
	}
	public Integer getPageSize()
	{
		return pageSize;
	}

	public void setPageSize(Integer pageSize)
	{
		this.pageSize = pageSize;
	}

	public void setPageSize(String pageSize)
	{
		this.pageSize = Integer.parseInt(pageSize);
	}

	public Integer getPageNum()
	{
		return pageNum;
	}

	public void setPageNum(String pageNum)
	{
		this.pageNum = Integer.parseInt(pageNum);
	}

	public void setPageNum(Integer pageNum)
	{
		this.pageNum = pageNum;
	}

	public Integer getMaxPage()
	{
		return maxPage;
	}

	public void setMaxPage(Integer maxPage)
	{
		this.maxPage = maxPage;
	}

}
