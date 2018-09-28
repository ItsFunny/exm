package com.exm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.exm.common.model.PageModel;
import com.exm.common.model.ResponseResultVo;
import com.exm.common.utils.JsonUtils;
import com.exm.common.utils.PageUtils;
import com.exm.model.Course;
import com.exm.model.SelectedCourseCustom;
import com.exm.model.Selectedcourse;
import com.exm.model.Student;
import com.exm.model.User;
import com.exm.service.CourseService;
import com.exm.service.SelectedCourseCustomService;
import com.exm.service.SelectedCourseService;
import com.exm.service.StudentService;
import com.exm.service.TeacherService;
import com.mysql.fabric.xmlrpc.base.Data;

@Controller
@RequestMapping("/teacher")
public class TeacherController
{
	// @Autowired
	// private CourseService courseService;
	@Autowired
	private TeacherService teacherService;
	@Autowired
	private StudentService studentService;
	@Autowired
	private CourseService courseService;
	@Autowired
	private SelectedCourseService selectedCourseService;
	@Autowired
	private SelectedCourseCustomService selectedCourseCustomService;

	/*
	 * 显示教师教授的课程
	 */
	@RequiresPermissions(value = "edit:showMyClasses")
	@RequestMapping("/classes/show")
	public ModelAndView showMyClasses(@RequestParam(name = "json", required = false) String json,
			@RequestParam(name = "pageSize", defaultValue = "5") Integer pageSize,
			@RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum, HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{

		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getPrincipal();
		Integer totalCount = teacherService
				.getTeacherAllCoursesTotalCount(Integer.parseInt(user.getUserId().toString()));
		// int maxPage = totalCount % pageSize == 0 ? totalCount / pageSize :
		// (totalCount / pageSize) + 1;

		Map<String, Object> params = new HashMap<>();
		if (json == null)
		{
			List<Course> classesOfTeacher = teacherService
					.getTeacherClasses(Integer.parseInt(user.getUserId().toString()));
			JSONArray jsonList = new JSONArray(classesOfTeacher);
			json = jsonList.toString();
		}
		PageModel<Course> pageModel = PageUtils.pageHelper(pageSize, pageNum, Course.class, json);

		// params.put("pageSize", pageSize);
		// params.put("pageNum", pageNum);
		// params.put("maxPage", pageHelper.getMaxPage());
		// params.put("totalCount", pageHelper.getTotalCount());
		// params.put("json", json);
		params.put("pageModel", pageModel);
		ModelAndView modelAndView = new ModelAndView("teacherShowClasses", params);
		return modelAndView;
	}

	/*
	 * 显示哪些学生选了这门课
	 */
	@RequiresRoles(value = "teacher")
	@RequestMapping("/class/grade/show/{courseId}")
	public ModelAndView showClassGrade(@RequestParam(name = "json", required = false) String json,
			@PathVariable("courseId") Integer courseId, HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		Map<String, Object> params = new HashMap<String, Object>();

		String pageSizeString = request.getParameter("pageSize");
		String pageNumString = request.getParameter("pageNum");
		pageSizeString = pageSizeString == null ? "5" : pageSizeString;
		pageNumString = pageNumString == null ? "1" : pageNumString;
		int pageSize = Integer.parseInt(pageSizeString);
		int pageNum = Integer.parseInt(pageNumString);
//		int totalCount = 0;
		if (json == null)
		{
			List<SelectedCourseCustom> grades = selectedCourseCustomService
					.getStudentsSelectedTheCourseByCourseId(courseId);
//			totalCount = grades.size();
			JSONArray jsonArray = new JSONArray(grades);
			json = jsonArray.toString();
		} 
//		else
//		{
//			List<Selectedcourse> temp = JsonUtils.jsonToList(json, Selectedcourse.class);
//		}
//		int maxPage = totalCount % pageSize == 0 ? totalCount / pageSize : (totalCount / pageSize) + 1;
		PageModel<SelectedCourseCustom> pageModel = PageUtils.pageHelper(pageSize, pageNum, SelectedCourseCustom.class,
				json);

		String redirect = new String(request.getRequestURL());

		// params.put("maxPage", maxPage);
		// params.put("courseId", courseId);
		// params.put("pageSize", pageSize);
		// params.put("pageNum", pageNum);
		params.put("redirect", redirect);
		params.put("pageModel", pageModel);
		// params.put("grades", grades);
		ModelAndView modelAndView = new ModelAndView("teacherShowGrade", params);
		return modelAndView;
	}

	/*
	 * 修改学生成绩
	 */
	@RequiresRoles(value = "teacher")
	@RequestMapping("/class/mark")
	public ModelAndView writeMark(@RequestParam Map<String, Object> params, HttpServletRequest request,
			HttpServletResponse response)
	{
		Integer studentId = Integer.parseInt(params.get("studentId").toString());
		Integer courseId = Integer.parseInt(params.get("courseId").toString());
		Student student = studentService.getStudentByUserId(studentId);
		Course course = courseService.getCourseById(courseId);

		String redirect = request.getParameter("redirect");
		params.put("redirect", redirect);
		params.put("course", course);
		params.put("student", student);
		ModelAndView modelAndView = new ModelAndView("teacherUpdateMark", params);
		return modelAndView;
	}

	@RequestMapping("/class/mark.do")
	public ModelAndView doUpdateStudentMark(@RequestParam("studentId") Integer studentId,
			@RequestParam("courseId") Integer courseId, @RequestParam("mark") Integer mark, HttpServletRequest request,
			HttpServletResponse response, Model model)
	{

		String redirect = request.getParameter("redirect");
		ResponseResultVo resultVo = selectedCourseService.updateStudentMark(mark, studentId, courseId);

		ModelAndView modelAndView = new ModelAndView("redirect:/temp");
		model.addAttribute("error", resultVo.getMsg());
		model.addAttribute("redirect", redirect);
		return modelAndView;
	}
}
