package com.exm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.exm.model.SelectedCourseCustom;

/*
 *	嵌套查询,老师查看选了这门课程的学生的成绩,以及给这名学生授予成绩 
 * 
 *
 */
@Mapper
public interface SelectedCourseCustomDao
{
	List<SelectedCourseCustom> getStudentsSelectedTheCourseByCourseId(Integer courseId);
	
	

}
