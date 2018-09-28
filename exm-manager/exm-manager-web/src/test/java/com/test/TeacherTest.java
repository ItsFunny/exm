// package com.test;
//
// import java.util.Date;
//
// import org.junit.Test;
// import org.junit.runner.RunWith;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.test.context.ContextConfiguration;
// import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
// import com.exm.dao.TeacherDao;
// import com.exm.model.Teacher;
//
// @RunWith(SpringJUnit4ClassRunner.class)
// @ContextConfiguration(locations = "classpath:spring/spring-*.xml")
// public class TeacherTest
// {
// @Autowired
// private TeacherDao teacherDao;
//
// @Test
// public void addTeacherTest()
// {
// Teacher teacher = new Teacher();
// teacher.setBirthYear(new Date());
// teacher.setCollegeId(1);
// teacher.setDegree("qq");
// teacher.setGrade(new Date());
// teacher.setSex("1");
// teacher.setTeacherName("qqqq");
// teacher.setTitle("asd");
// teacher.setUserId(12333333);
// teacherDao.addTeacher(teacher);
// }
//
// @Test
// public void insert()
// {
// for (Integer i = 1; i < 30; i++)
// {
// Teacher teacher = new Teacher();
// teacher.setBirthYear(new Date());
// teacher.setCollegeId(1);
// teacher.setDegree("qq:" + i * 7);
// teacher.setGrade(new Date());
// teacher.setSex("男");
// teacher.setTeacherName("www:" + i * 3);
// teacher.setTitle("asd");
// teacher.setUserId(i * 104);
// teacherDao.addTeacher(teacher);
//
// }
//
// }
// }
