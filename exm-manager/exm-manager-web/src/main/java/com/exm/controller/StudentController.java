package com.exm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.exm.common.model.PageModel;
import com.exm.common.model.ResponseResultVo;
import com.exm.common.model.SearchResultVo;
import com.exm.common.utils.JsonUtils;
import com.exm.common.utils.PageUtils;
import com.exm.model.Course;
import com.exm.model.PageVo;
import com.exm.model.User;
import com.exm.service.CourseService;
import com.exm.service.SelectedCourseService;

@RestController
@RequestMapping("/student")
public class StudentController
{
	@Autowired
	private SelectedCourseService selectedCourseService;

	@Autowired
	private CourseService courseService;

	/*
	 * 显示所有课程,这里不需要使用自己的pageUtils,除非查询课程的时候(传到这里来显示,这样可以省去一张图)
	 */
	@RequestMapping("/courses/show")
	public ModelAndView showAllcourses(@RequestParam(name = "json", required = false) String json,
			HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Map<String, Object> params = new HashMap<String, Object>();
		PageModel<Course> pageModel = new PageModel<>();
		int totalCount = courseService.getTotalCount();
		String pageSizeString = request.getParameter("pageSize");
		String pageNumString = request.getParameter("pageNum");
		pageSizeString = pageSizeString == null ? "5" : pageSizeString;
		pageNumString = pageNumString == null ? "1" : pageNumString;
		int pageSize = Integer.parseInt(pageSizeString);
		int pageNum = Integer.parseInt(pageNumString);
		int maxPage = totalCount % pageSize == 0 ? totalCount / pageSize : (totalCount / pageSize) + 1;
		List<Course> courses = null;
		if (json == null)
		{
			courses = courseService.getCoursesLimited((pageNum - 1) * pageSize, pageSize);
			pageModel.setData(courses);
			pageModel.setMaxPage(maxPage);
			pageModel.setPageNum(pageNum);
			pageModel.setPageSize(pageSize);
			pageModel.setTotalCount(totalCount);
		} else
		{
			List<Course> coursesTemp = JsonUtils.jsonToList(json, Course.class);
			totalCount = coursesTemp.size();
			pageModel = PageUtils.pageHelper(pageSize, pageNum, Course.class, json);
		}

		// params.put("courses", courses);
		// params.put("pageSize", pageSize);
		// params.put("pageNum", pageNum);
		// params.put("maxPage", maxPage);
		params.put("pageModel", pageModel);
		ModelAndView modelAndView = new ModelAndView("studentShowCourses", params);
		return modelAndView;
	}

	/*
	 * 这里查询到的信息可以传到上面这个方法中(利用redirect),将search到的数据以json格式传个上面的参数json
	 * 从而生成页面,可以省去一个页面(1个页面多种使用)
	 */
	@RequestMapping("/course/search")
	public ModelAndView modelAndView(@RequestParam("q") String q, Integer pageSize,
			@RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
			@RequestParam(name = "json", required = false) String json, HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
		Map<String, Object> params = new HashMap<>();
		SearchResultVo result = courseService.searchCourses(pageSize, pageNum, q);
		@SuppressWarnings("unchecked")
		List<Course> courses = (List<Course>) result.getObjectList();
		if (json == null)
		{
			JSONArray jsonArray = new JSONArray(courses);
			json = jsonArray.toString();
		}
		PageModel<Course> pageModel = PageUtils.pageHelper(pageSize, pageNum, Course.class, json);
		params.put("q", q);
		// params.put("courses", courses);
		// params.put("pageSize", pageSize);
		// params.put("pageNum", result.getCurPage());
		// params.put("maxPage", result.getMaxPage());
		params.put("pageModel", pageModel);

		ModelAndView modelAndView = new ModelAndView("showCourses2", params);
		return modelAndView;
	}

	@RequestMapping("/course/select/{courseId}")
	public ModelAndView selectCourse(@PathVariable("courseId") Integer courseId, HttpServletRequest request,
			HttpServletResponse response, Model model)
	{
		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getPrincipal();
		Integer userId = Integer.parseInt(user.getUserId().toString());
		ResponseResultVo resultVo = selectedCourseService.addSelectedCourse(courseId, userId);
		ModelAndView modelAndView = new ModelAndView("redirect:/temp");
		model.addAttribute("error", resultVo.getMsg());
		model.addAttribute("redirect", "/index");
		return modelAndView;
	}

	@RequestMapping("/course/selected/show")
	public ModelAndView showSelectedCourses(
			@RequestParam(name = "pageSize", defaultValue = "5", required = false) Integer pageSize,
			@RequestParam(name = "pageNum", defaultValue = "1", required = false) Integer pageNum,

			HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Map<String, Object> params = new HashMap<>();
		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getPrincipal();
		Integer userId = Integer.parseInt(user.getUserId().toString());
		List<List<Course>> allCourses = selectedCourseService.getStudentAllCourses(userId);
		List<Course> courses = allCourses.get(0);
		JSONArray jsonArray = new JSONArray(courses);
		String json = JsonUtils.objectToJson(courses);
		PageModel<Course> pageModel = PageUtils.pageHelper(pageSize, pageNum, Course.class, json);
		// int size = courses.size();
		// PageVo pageVo = new PageVo(size, pageSize, pageNum);
//		params.put("courses", courses);
		params.put("pageModel", pageModel);
		ModelAndView modelAndView = new ModelAndView("studentShowSelectedCourses", params);
		return modelAndView;
	}

	@RequestMapping("/course/completed/show")
	public ModelAndView showCompletedCourses(
			@RequestParam(name = "pageSize", defaultValue = "5", required = false) Integer pageSize,
			@RequestParam(name = "pageNum", defaultValue = "1", required = false) Integer pageNum,
			HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getPrincipal();
		Integer userId = Integer.parseInt(user.getUserId().toString());
		List<List<Course>> allCourses = selectedCourseService.getStudentAllCourses(userId);
		List<Course> courses = allCourses.get(1);
		JSONArray jsonArray = new JSONArray(courses);
		String json = jsonArray.toString();
		PageModel<Course> pageModel = PageUtils.pageHelper(pageSize, pageNum, Course.class, json);
		ModelAndView modelAndView = new ModelAndView("studentCompletedCourses");
		modelAndView.addObject("pageModel", pageModel);
		return modelAndView;
	}

}
