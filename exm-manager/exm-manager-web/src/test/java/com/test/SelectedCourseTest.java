//package com.test;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import com.exm.config.Config;
//import com.exm.dao.SelectedCourseDao;
//import com.exm.model.Selectedcourse;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = "classpath:spring/spring-*.xml",classes=Config.class)
//public class SelectedCourseTest
//{
//
//	@Autowired
//	private SelectedCourseDao selectedCourseDao;
//	@Test
//	public void selectedCourseTest()
//	{
//		Selectedcourse selectedcourse=new Selectedcourse();
//		selectedcourse.setCourseId(1234);
//		selectedcourse.setMark(null);
//		selectedcourse.setStudentId(1234);
//		selectedCourseDao.addSelectedCourse(selectedcourse);
//	}
//}
