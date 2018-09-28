//package com.test;
//
//import java.util.List;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Import;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import com.exm.config.Config;
//import com.exm.dao.SelectedCourseCustomDao;
//import com.exm.model.SelectedCourseCustom;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations="classpath:spring/spring-*.xml")
//@Import(value=Config.class)
//public class UnionSqlTest
//{
//
//	@Autowired
//	private SelectedCourseCustomDao selectedCourseCustomDao;
//	@Test
//	public void unionSqlTest()
//	{
//		List<SelectedCourseCustom> studentsSelectedTheCourseByCourseId = selectedCourseCustomDao.getStudentsSelectedTheCourseByCourseId(1);
//		for (SelectedCourseCustom selectedCourseCustom : studentsSelectedTheCourseByCourseId)
//		{
//			System.out.println(selectedCourseCustom.toString());
//		}
//	}
//}
