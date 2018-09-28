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
//import com.exm.service.StudentService;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations="classpath:spring/spring-*.xml")
//public class StudentTest
//{
//	@Autowired
//	private StudentDao studentDao;
//	@Autowired
//	private StudentService studentService;
//	@Test
//	public void studentTest()
//	{
//		Student student=new Student();
//		student.setUserId((long) 1001);
//		student.setCollegeId(2);
//		student.setSex("女");
//		student.setStudentName("小聪");
//		studentDao.updateStudent(student);
//	}
//	
//	@Test
//	public void studentRestTest()
//	{
//
//		Student student=new Student();
//		student.setUserId((long) 1001);
//		student.setCollegeId(2);
//		student.setSex("女");
//		student.setStudentName("小keke");
//		studentService.updateStudent(student);
//	}
//	@Test
//	public void studentAddTest()
//	{
//		Student student = new Student();
//		student.setBirthYear(new Date());
//		student.setCollegeId(1);
//		student.setGrade(new Date());
//		student.setSex("男");
//		student.setStudentName("22222");
//		student.setUserId((long) 111111);
//		ResponseResultVo responseResultVo = studentService.addStudent(student);
//		System.out.println(responseResultVo.getStatus());
//	}
//	
//}
