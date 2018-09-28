package com.exm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.exm.model.Teacher;

@Mapper
public interface TeacherDao
{

	@Select("select * from teacher")
	List<Teacher> getAllTeachers();

	@Delete("delete from teacher where user_id=#{userId}")
	void deleteTeacherByUserId(Integer userId);

	@Insert("insert into teacher (user_id,teacher_name,sex,birth_year,degree,title,grade,college_id) values (#{userId},#{teacherName},#{sex},#{birthYear},#{degree},#{title},#{grade},#{collegeId})")
	void addTeacher(Teacher teacher);

	@Select("select * from teacher where user_id=#{userId}")
	Teacher getTeacherByUserId(Integer userId);

	@Select("select * from teacher where teacher_name like #{teacherName}")
	List<Teacher> getTeachersByTeacherName(String teacherName);

	@Select("select * from teacher limit #{start},#{end}")
	List<Teacher> getTeachersLimited(@Param("start")Integer start,@Param("end")Integer end);
	
	@Select("select count(id) from teacher")
	Integer getTotal();
	
	
}
