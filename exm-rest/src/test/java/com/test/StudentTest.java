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
//import com.exm.common.model.ResponseResultVo;
//import com.exm.dao.StudentDao;
//import com.exm.model.Student;
//import com.exm.rest.service.StudentService;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = "classpath:spring/spring-*.xml")
//public class StudentTest
//{
//
//	@Autowired
//	private StudentService studentService;
//
//	@Autowired
//	private StudentDao studentDao;
//
//	@Test
//	public void studentTest()
//	{
//		Student student = new Student();
//		student.setBirthYear(new Date());
//		student.setCollegeId(1);
//		student.setGrade(new Date());
//		student.setSex("男");
//		student.setStudentName("qqq");
//		student.setUserId((long) 1223544);
//		studentDao.addStudent(student);
//	}
//
//}
