//package com.test;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.exm.model.Course;
//import com.exm.service.CourseService;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations="classpath:spring/spring-*.xml")
//public class CourseTest
//{
//	
//	@Autowired
//	private CourseService courseService;
//	@Test
//	public void CourseTest()
//	{	
//		for (Integer i = 1; i <100; i++)
//		{
//			Course course=new Course();
//			String coursename="course"+"-"+i.toString();
//			course.setCourseName(coursename);
//			course.setClassRoom("qw");
//			course.setCollegeId(1);
//			course.setCourseTime("123");
//			course.setCourseType("qqq");
//			course.setCourseWeek(2);
//			course.setScore(3);
//			course.setTeacherId(1001);
//			courseService.insertCourse(course);
//		}
//	}
//	@Test
//	public void courseUpdateTest()
//	{
//		
//	}
//
//}
