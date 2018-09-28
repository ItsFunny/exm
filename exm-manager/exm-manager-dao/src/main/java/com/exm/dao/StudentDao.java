package com.exm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.exm.model.Student;

@Mapper
public interface StudentDao
{
	@Select("select count(id) from student")
	int getTotalCount();
	@Select("select * from student")
	List<Student> getAllStudents();
	@Select("select * from student limit #{start},#{end}")
	List<Student>getStudentsLimited(@Param("start")Integer start,@Param("end")Integer end);
	@Select("select * from student where user_id=#{userId}")
	Student getStudentByUserId(Integer userId);
	
	@Select("select * from student where student_name=#{studentName}")
	List<Student>getStudentsByUserName(String userName);
	
	@Select("select * from student where student_name like #{studentName}")
	List<Student>getStudentsByUserNameAndLike(String studentName);
	
	@Update("update student set user_id=#{userId},student_name=#{studentName},sex=#{sex},college_id=#{collegeId} where user_id=#{userId}")
	void updateStudent(Student student);
	
	@Delete("delete from student where user_id=#{userId}")
	void deleteStudentByUserId(Integer userId);
	
	@Insert("insert into student (user_id,student_name,sex,birth_year,grade,college_id) values (#{userId},#{studentName},#{sex},#{birthYear},#{grade},#{collegeId})")
	void addStudent(Student student);
	
	
}
