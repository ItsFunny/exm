package com.exm.controller;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import com.exm.model.College;
import com.exm.model.Course;
import com.exm.model.Student;
import com.exm.model.Teacher;
import com.exm.service.CollegeService;
import com.exm.service.CourseService;
import com.exm.service.StudentService;
import com.exm.service.TeacherService;

@RestController
@RequestMapping("/admin")
public class AdminController
{

	@Value("${COURSE_URL}")
	private String COURSE_URL;
	@Value("${STUDENT_URL}")
	private String STUDENT_URL;
	@Value("${TEACHER_URL}")
	private String TEACHER_URL;

	@Autowired
	private CourseService courseService;

	@Autowired
	private TeacherService teacherService;

	@Autowired
	private CollegeService collegeService;
	@Autowired
	private StudentService studentService;

	@RequestMapping("/course/show")
	// @RequestParam(name = "courses", required = false) String json,
	public ModelAndView showCourses(HttpServletRequest request, HttpServletResponse response, Model model)
			throws Exception
	{

		int totalCount = courseService.getTotalCount();
		String pageSizeString = request.getParameter("pageSize");
		String pageNumString = request.getParameter("pageNum");
		if (pageSizeString == null)
		{
			pageSizeString = "5";
		}
		if (pageNumString == null)
		{
			pageNumString = "1";
		}
		int pageSize = Integer.parseInt(pageSizeString);
		int pageNum = Integer.parseInt(pageNumString);
		int maxPage = totalCount % pageSize == 0 ? totalCount / pageSize : (totalCount / pageSize) + 1;
		// (totalCount / pageSize) + 1;

		List<Course> courses = new ArrayList<>();
		// if (json == null)
		// {
		courses = courseService.getCoursesLimited((pageNum - 1) * pageSize, pageSize);
		PageModel<Course> pageModel = new PageModel<>(pageSize, pageNum, totalCount, maxPage, courses);
		// JSONArray jsonArray = new JSONArray(courses);
		// json = jsonArray.toString();
		// }
		// else
		// {
		// json = new String(json.getBytes("iso-8859-1"), "utf-8");
		// JsonUtils.jsonToList(courseString, Course.class);
		// totalCount = courses.size();
		// maxPage = totalCount % pageSize == 0 ? totalCount / pageSize : (totalCount /
		// pageSize) + 1;
		// courses = PageUtils.pageHelper(pageSize, pageNum, maxPage, totalCount,
		// Course.class, json);
		// }
		// PageModel<Course> pageModel = PageUtils.pageHelper(pageSize, pageNum,
		// Course.class, json);
		Map<String, Object> params = new HashMap<String, Object>();

		// params.put("pageSize", pageSize);
		// params.put("pageNum", pageNum);
		// params.put("maxPage", maxPage);
		// params.put("courses", courses);
		params.put("pageModel", pageModel);
		ModelAndView modelAndView = new ModelAndView("showCourses", params);
		// 将json传给页面,这样,提交的时候将json也一起提交,防止再查询数据库
		// 需要ajax和js,不会所以跳过了
		// modelAndView.addObject("json", json);
		return modelAndView;
	}

	@RequestMapping("/course/delete/{id}")
	public ModelAndView delCourse(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response,
			Model model)
	{
		System.out.println(id);
		courseService.deleteCourseById(id);

		model.addAttribute("error", "sucess");
		model.addAttribute("redirect", COURSE_URL);
		response.setContentType("html/text;charset=urf-8");
		ModelAndView modelAndView = new ModelAndView("redirect:/temp");
		return modelAndView;
	}

	@RequestMapping("/editCourse/{courseId}")
	public ModelAndView editCourse(@PathVariable("courseId") Integer id, HttpServletRequest request,
			HttpServletResponse response)
	{
		Map<String, Object> params = new HashMap<>();

		Course course = courseService.getCourseById(id);
		List<Teacher> teachers = teacherService.getAllTeachers();

		List<College> colleges = collegeService.getAllColleges();
		params.put("course", course);
		params.put("colleges", colleges);
		params.put("teachers", teachers);
		ModelAndView modelAndView = new ModelAndView("editCourse", params);
		return modelAndView;
	}

	@RequestMapping("/course/update")
	public ModelAndView updateCourse(Course course, @RequestParam Map<String, Object> params,
			HttpServletRequest request, HttpServletResponse response, Model model) throws UnsupportedEncodingException
	{
		courseService.update(course);
		model.addAttribute("error", "update sucess");
		model.addAttribute("redirect", COURSE_URL);
		ModelAndView modelAndView = new ModelAndView("redirect:/temp");
		return modelAndView;
	}

	@RequestMapping("/course/add")
	public ModelAndView addCourse(HttpServletRequest request, HttpServletResponse response)
	{
		Map<String, Object> params = new HashMap<>();
		List<Teacher> teachers = teacherService.getAllTeachers();
		List<College> colleges = collegeService.getAllColleges();
		params.put("teachers", teachers);
		params.put("colleges", colleges);
		ModelAndView modelAndView = new ModelAndView("addCourse", params);
		return modelAndView;
	}

	@RequestMapping("/course/add.do")
	public ModelAndView doAddCourse(Course course, Model model, HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
		System.out.println(course.toString());
		ResponseResultVo resultVo = courseService.addCourse(course);
		// 跳转到最后一页?

		model.addAttribute("error", resultVo.getMsg());
		model.addAttribute("redirect", COURSE_URL);
		ModelAndView modelAndView = new ModelAndView("redirect:/temp");
		return modelAndView;
	}

	@RequestMapping("/course/search")
	public ModelAndView searchCourse(Integer pageSize,
			@RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum, String q, HttpServletRequest request,
			HttpServletResponse response)
	{

		Map<String, Object> params = new HashMap<>();
		SearchResultVo result = courseService.searchCourses(pageSize, pageNum, q);
//		List<Course> courses = (List<Course>) result.getObjectList();
		@SuppressWarnings("unchecked")
		PageModel<Course> pageModel=new PageModel<Course>(pageSize, pageNum, (int)result.getRecordCount(), (int) result.getMaxPage(), (List<Course>) result.getObjectList());
		params.put("q", q);
//		params.put("courses", courses);
//		params.put("pageSize", pageSize);
//		params.put("pageNum", result.getCurPage());
//		params.put("maxPage", result.getMaxPage());
		params.put("pageModel", pageModel);
		ModelAndView modelAndView = new ModelAndView("showCourses", params);
		return modelAndView;
	}

	@RequestMapping("/students/show")
	public ModelAndView showStudent(@RequestParam(name = "studentString", required = false) String json,
			HttpServletRequest request, HttpServletResponse response) throws ParseException, Exception
	{
		Map<String, Object> params = new HashMap<>();

		int totalCount = studentService.getTotalCount();

		String pageSizeString = request.getParameter("pageSize");
		String pageNumString = request.getParameter("pageNum");
		pageSizeString = pageNumString == null ? "2" : pageSizeString;
		pageNumString = pageNumString == null ? "1" : pageNumString;
		int pageSize = Integer.parseInt(pageSizeString);
		int pageNum = Integer.parseInt(pageNumString);
		int maxPage = totalCount % pageSize == 0 ? totalCount / pageSize : (totalCount / pageSize) + 1;

		// if (json == null)
		// {
		List<Student> students = studentService.getStudentsLimited((pageNum - 1) * pageSize, pageSize);
		PageModel<Student> pageModel = new PageModel<>(pageSize, pageNum, totalCount, maxPage, students);
		// for (Student student : students)
		// {
		// SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss
		// zzz");
		// String dateString = dateFormat.format(student.getBirthYear());
		// student.setBirthYear(dateFormat.parse(dateString));
		// }
		// maxPage=totalCount % pageSize == 0 ? totalCount / pageSize : (totalCount /
		// pageSize) + 1;
		// JSONArray jsonArray = new JSONArray(students);
		// json = jsonArray.toString();
		// }
		// else
		// {
		// json=new String(json.getBytes("iso-8859-1"),"utf-8");
		// totalCount=students.size();
		// maxPage = totalCount % pageSize == 0 ? totalCount / pageSize : (totalCount /
		// pageSize) + 1;
		// }
		// PageModel<Student> pageModel = PageUtils.pageHelper(pageSize, pageNum,
		// Student.class, json);

		// params.put("pageSize", pageSize);
		// params.put("pageNum", pageNum);
		// params.put("maxPage", maxPage);
		//
		// params.put("students", students);

		params.put("pageModel", pageModel);
		ModelAndView modelAndView = new ModelAndView("showStudents", params);
		return modelAndView;
	}

	@RequestMapping("/student/update")
	public ModelAndView updateStudent(HttpServletRequest request, HttpServletResponse response)
	{
		Map<String, Object> params = new HashMap<>();
		Integer userId = Integer.parseInt(request.getParameter("userId").toString());
		Student student = studentService.getStudentByUserId(userId);
		List<College> colleges = collegeService.getAllColleges();
		params.put("student", student);
		params.put("colleges", colleges);
		ModelAndView modelAndView = new ModelAndView("editStudent", params);
		return modelAndView;
	}

	@RequestMapping("/student/update.do")
	public ModelAndView doUpdateStudent(Student student, HttpServletRequest request, HttpServletResponse response)
	{

		Map<String, Object> params = new HashMap<>();
		ResponseResultVo responseResultVo = studentService.updateStudent(student);
		if (responseResultVo.getStatus() == 200)
		{
			params.put("error", responseResultVo.getMsg());
			params.put("redirect", STUDENT_URL);
			ModelAndView modelAndView = new ModelAndView("redirect:/temp", params);
			return modelAndView;
		}
		// 这里还可以写一些其他操作,针对update的service返回值有不同的操作
		return null;
	}

	@RequestMapping("/student/delete/{userId}")
	public ModelAndView deleteStudent(@PathVariable Integer userId, HttpServletRequest request,
			HttpServletResponse response)
	{
		Map<String, Object> params = new HashMap<>();
		ResponseResultVo resultVo = studentService.deleteStudent(userId);
		params.put("error", resultVo.getMsg());
		params.put("redirect", STUDENT_URL);
		ModelAndView modelAndView = new ModelAndView("redirect:/temp", params);
		return modelAndView;
	}

	@RequestMapping("/student/add")
	public ModelAndView addStudent(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		List<College> colleges = collegeService.getAllColleges();
		List<Teacher> teachers = teacherService.getAllTeachers();
		model.addAttribute("colleges", colleges);
		model.addAttribute("teachers", teachers);
		ModelAndView modelAndView = new ModelAndView("addStudent");
		return modelAndView;
	}

	@RequestMapping("/student/add.do")
	public ModelAndView doAddStudent(HttpServletRequest request, HttpServletResponse response) throws ParseException
	{
		String gradesString = request.getParameter("grade");
		String birthYearString = request.getParameter("birthYear");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date grade = dateFormat.parse(gradesString);
		Date birthYear = dateFormat.parse(birthYearString);
		Student student = new Student();
		student.setUserId(Long.parseLong(request.getParameter("userId").toString()));
		student.setStudentName(request.getParameter("studentName"));
		student.setCollegeId(Integer.parseInt(request.getParameter("collegeId").toString()));
		student.setGrade(grade);
		student.setBirthYear(birthYear);
		student.setSex(request.getParameter("sex"));
		ResponseResultVo responseResultVo = studentService.addStudent(student);
		Map<String, Object> params = new HashMap<>();
		params.put("error", responseResultVo.getMsg());
		params.put("redirect", STUDENT_URL);
		ModelAndView modelAndView = new ModelAndView("redirect:/temp", params);
		return modelAndView;
	}

	@RequestMapping("/student/search")
	public ModelAndView searchStudens(@RequestParam("q") String q,
			@RequestParam(name = "pageSize", defaultValue = "5") Integer pageSize,
			@RequestParam(name = "pageNum", defaultValue = "1") int pageNum) throws Exception
	{
		ModelAndView modelAndView = null;

		List<Student> students = studentService.searchStudens(q);
		if (students == null)
		{
			modelAndView = new ModelAndView("redirect:/temp");
			modelAndView.addObject("error", "找不到任何学生");
			modelAndView.addObject("redirect", STUDENT_URL);
			return modelAndView;
		}
		JSONArray jsonArray = new JSONArray(students);
		String json = jsonArray.toString();
		PageModel<Student> pageModel = PageUtils.pageHelper(pageSize, pageNum, Student.class, json);

		// int totalCount = students.size();
		// int maxPage = totalCount % pageSize == 0 ? totalCount / pageSize :
		// (totalCount / pageSize) + 1;
		modelAndView = new ModelAndView("showStudents");
		// modelAndView.addObject("students", students);
		// modelAndView.addObject("pageSize", pageSize);
		// modelAndView.addObject("maxPage", maxPage);
		// modelAndView.addObject("pageNum", 1);
		modelAndView.addObject("pageModel", pageModel);
		return modelAndView;
	}

	/*
	 * 显示所有的老师,能用数据库查找的就用数据库的limit方法
	 */
	@RequestMapping(value = "/teachers/show")
	public ModelAndView showTeachers(HttpServletRequest request, HttpServletResponse response)
	{
		Map<String, Object> params = new HashMap<>();

		int totalCount = teacherService.getTotalCount();
		String pageSizeString = request.getParameter("pageSize");
		String pageNumString = request.getParameter("pageNum");
		pageSizeString = pageSizeString == null ? "5" : pageSizeString;
		pageNumString = pageNumString == null ? "1" : pageNumString;
		int pageSize = Integer.parseInt(pageSizeString);
		int pageNum = Integer.parseInt(pageNumString);

		int maxPage = totalCount % pageSize == 0 ? totalCount / pageSize : (totalCount / pageSize) + 1;

		List<Teacher> teachers = new ArrayList<Teacher>();

		teachers = teacherService.getTeachersLimited((pageNum - 1) * pageSize, pageSize);

		PageModel<Teacher> pageModel = new PageModel<>(pageSize, pageNum, totalCount, maxPage, teachers);
		// else
		// {
		// teachers = JsonUtils.jsonToList(json, Teacher.class);
		// }
		// params.put("teachers", teachers);
		// params.put("pageSize", pageSize);
		// params.put("pageNum", pageNum);
		// params.put("maxPage", maxPage);
		params.put("pageModel", pageModel);
		ModelAndView modelAndView = new ModelAndView("showTeachers", params);
		return modelAndView;
	}

	@RequestMapping("/teacher/delete/{userId}")
	public ModelAndView deleteTeacher(@PathVariable Integer userId, HttpServletRequest request,
			HttpServletResponse response)
	{
		ResponseResultVo responseResultVo = teacherService.deleteTeacher(userId);
		ModelAndView modelAndView = new ModelAndView("redirect:/temp");
		modelAndView.addObject("redirect", TEACHER_URL);
		modelAndView.addObject("error", responseResultVo.getMsg());
		return modelAndView;
	}

	@RequestMapping("/teacher/add")
	public ModelAndView addTeacher(HttpServletRequest request, HttpServletResponse response) throws ParseException
	{
		Map<String, Object> params = new HashMap<>();
		List<College> colleges = collegeService.getAllColleges();
		params.put("colleges", colleges);
		ModelAndView modelAndView = new ModelAndView("addTeacher", params);
		return modelAndView;
	}

	@RequestMapping("/teacher/add.do")
	public ModelAndView doAddTeacher(HttpServletRequest request, HttpServletResponse response) throws ParseException
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date birthYear = dateFormat.parse(request.getParameter("birthYear"));
		Date grade = dateFormat.parse(request.getParameter("grade"));
		// private Integer userId;
		// private String teacherName;
		// private String sex;
		// private Date birthYear;
		// private Date grade;
		// private String degree;
		// private String title;
		// private Integer collegeId;
		Teacher teacher = new Teacher();
		teacher.setUserId(Integer.parseInt(request.getParameter("userId")));
		teacher.setCollegeId(Integer.parseInt(request.getParameter("collegeId")));
		teacher.setSex(request.getParameter("sex"));
		teacher.setBirthYear(birthYear);
		teacher.setGrade(grade);
		teacher.setDegree(request.getParameter("degree"));
		teacher.setTeacherName(request.getParameter("teacherName"));
		teacher.setTitle(request.getParameter("title"));

		ResponseResultVo resultVo = teacherService.addTeacher(teacher);
		Map<String, Object> params = new HashMap<>();
		params.put("error", resultVo.getMsg());
		params.put("redirect", TEACHER_URL);
		ModelAndView modelAndView = new ModelAndView("redirect:/temp", params);
		return modelAndView;
	}

	@RequestMapping("/teacher/search")
	public ModelAndView searchTeachers(@RequestParam(name = "pageSize", defaultValue = "5") Integer pageSize,
			@RequestParam(name = "pageNum", defaultValue = "1") int pageNum, @RequestParam("q") String q,
			HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		ModelAndView modelAndView = null;
		ResponseResultVo responseResultVo = teacherService.searchTeachers(q);
		if (responseResultVo.getData() == null)
		{
			// model.addAttribute("error", "未找到任何教师信息,请重新输入");
			// model.addAttribute("redirect", TEACHER_URL);
			// return new ModelAndView("redirect:/temp");
			modelAndView = new ModelAndView("redirect:/temp");
			;
			modelAndView.addObject("error", "没有搜到任何结果,重新输入");
			modelAndView.addObject("redirect", TEACHER_URL);
			return modelAndView;
		}
		@SuppressWarnings("unchecked")
		List<Teacher> teachers = (List<Teacher>) responseResultVo.getData();
		String json = JsonUtils.objectToJson(teachers);
		// model.addAttribute("json", json);
		PageModel<Teacher> pageModel = PageUtils.pageHelper(pageSize, pageNum, Teacher.class, json);
		response.setContentType("text/html;charset=utf-8");

		modelAndView = new ModelAndView("showTeachers");
		modelAndView.addObject("q", q);
		modelAndView.addObject("pageModel", pageModel);
		return modelAndView;
	}

}
