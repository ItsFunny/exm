package com.exm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.exm.model.Selectedcourse;

@Mapper
public interface SelectedCourseDao
{
	@Select("select * from selectedcourse where student_id=#{userId}")
	List<Selectedcourse> getStudentSelectedcoursesByUserId(Integer userId);

	@Insert("insert into selectedcourse (course_id,student_id) values (#{courseId},#{studentId})")
	void addSelectedCourse(Selectedcourse selectedcourse);

	@Select("select * from selectedcourse where course_id=#{courseId} and student_id=#{userId}")
	Selectedcourse isHaveThisCourse(@Param("courseId") Integer courseId, @Param("userId") Integer userId);
	
	@Update("update selectedcourse set mark=#{mark} where student_id=#{studentId} and course_id=#{courseId}")
	void updateMarkOfStudentByStudentId(@Param("mark")Integer mark,@Param("studentId")Integer studentId,@Param("courseId")Integer courseId);
	
	@Select("select * from selectedcourse where student_id=#{studentId} and course_id=#{courseId}")
	Selectedcourse getSelectedcourseByStudentIdAndCourseId(@Param("studentId")Integer studentId,@Param("courseId")Integer courseId);
	
	
	
	


}
