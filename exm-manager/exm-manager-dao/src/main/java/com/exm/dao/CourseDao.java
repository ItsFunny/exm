package com.exm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.exm.model.Course;

@Mapper
public interface CourseDao
{
	@Select("select * from course")
	List<Course> getAllCourses();

	@Select("delete from course where id=#{id}")
	void deleteById(Integer id);

	@Insert("insert into course values (null,#{course_name},#{teacher_id},#{course_time},#{class_room},#{course_week},#{course_type},#{college_id},#{score})")
	public void insertCourse(Course course);

	@Select("select count(id) from course")
	Integer getTotalCount();

	@Select("select * from course limit #{start},#{end}")
	List<Course> getCoursesLimited(@Param("start") Integer start, @Param("end") Integer end);

	@Select("select * from course where id=#{id}")
	Course getCourseById(Integer id);
	
	
	void updateCourse(Course course);
	
	void addCourse(Course course);
	
	
	@Select("select * from course where teacher_id=#{teacherId}")
	List<Course> getCoursesByTeacherId(Integer teacherId);
	
	@Select("select  count(id) from course where teacher_id=#{teacherId}")
	Integer getTeacherCoursesTotalCount(Integer teacherId);
	
	
}
