package com.exm.common.utils;

import java.util.ArrayList;
import java.util.List;


import com.exm.common.model.PageModel;

public class PageUtils
{

	public static List<?> getResponses()
	{
		return responses;
	}

	public static void setResponses(List<?> responses)
	{
		PageUtils.responses = responses;
	}

	private static List<?> responses;

	public static <T> PageModel<T> pageHelper(int pageSize, int pageNum, Class<T> responseClass, String json)
			throws Exception
	{
		// int maxPage = totalCount % pageSize == 0 ? totalCount / pageSize :
		// (totalCount / pageSize) + 1;
		// 调用反射,动态生成一定数组长度的list
		// 利用泛型,动态生成
//		json=new String(json.getBytes("iso-8859-1"),"utf-8");
//		String s=new String(json.getBytes("utf-8"),"utf-8");
		List<T> test = (List<T>) JsonUtils.jsonToList(json, responseClass);
		int totalCount = test.size();
		int maxPage = totalCount % pageSize == 0 ? totalCount / pageSize : (totalCount / pageSize) + 1;
		List<T> temp = new ArrayList<>(pageSize);
		int times = 0;
		if (totalCount < ((pageSize) * (pageNum)))
		{
			times = totalCount;
		} else
		{
			times = pageSize * pageNum;
		}
		for (int i = (pageNum - 1) * pageSize; i < times && pageNum <= maxPage; ++i)
		{
			temp.add(test.get(i));
		}
//		PageModel<T> pageModel = new PageModel<T>();
//		pageModel.setMaxPage(maxPage);
//		pageModel.setPageNum(pageNum);
//		pageModel.setPageSize(pageSize);
//		pageModel.setTotalCount(totalCount);
//		pageModel.setData(temp);
		return new PageModel<>(pageSize, pageNum, totalCount, maxPage, temp);
	}

}
