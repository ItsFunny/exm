//package com.test;
//
//import java.util.Date;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import com.exm.model.Teacher;
//import com.exm.rest.service.TeacherService;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = "classpath:spring/spring-*.xml")
//public class TeacherTest
//{
//	@Autowired
//	private TeacherService teacherService;
//
//	@Test
//	public void addTeacherTest()
//	{
//		Teacher teacher = new Teacher();
//		teacher.setBirthYear(new Date());
//		teacher.setCollegeId(1);
//		teacher.setDegree("11");
//		teacher.setGrade(new Date());
//		teacher.setSex("nan");
//		teacher.setTeacherName("qweqew");
//		teacher.setTitle("qqqqqq");
//		teacher.setUserId(123312);
//		teacherService.addTeacher(teacher);
//	}
//
//}
