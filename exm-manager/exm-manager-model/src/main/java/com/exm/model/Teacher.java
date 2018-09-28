package com.exm.model;

import java.util.Date;
import java.util.List;

public class Teacher
{

	private Integer id;

	private Integer userId;

	private String teacherName;

	private String sex;

	private Date birthYear;

	private String degree;

	private String title;

	private Date grade;

	private Integer collegeId;

	// 新增
	private List<Course> selectedCoursesOfStuden;

	public List<Course> getSelectedCoursesOfStuden()
	{
		return selectedCoursesOfStuden;
	}

	public void setSelectedCoursesOfStuden(List<Course> selectedCoursesOfStuden)
	{
		this.selectedCoursesOfStuden = selectedCoursesOfStuden;
	}

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public Integer getUserId()
	{
		return userId;
	}

	public void setUserId(Integer userId)
	{
		this.userId = userId;
	}

	public String getTeacherName()
	{
		return teacherName;
	}

	public void setTeacherName(String teacherName)
	{
		this.teacherName = teacherName == null ? null : teacherName.trim();
	}

	public String getSex()
	{
		return sex;
	}

	public void setSex(String sex)
	{
		this.sex = sex == null ? null : sex.trim();
	}

	public Date getBirthYear()
	{
		return birthYear;
	}

	public void setBirthYear(Date birthYear)
	{
		this.birthYear = birthYear;
	}

	public String getDegree()
	{
		return degree;
	}

	public void setDegree(String degree)
	{
		this.degree = degree == null ? null : degree.trim();
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title == null ? null : title.trim();
	}

	public Date getGrade()
	{
		return grade;
	}

	public void setGrade(Date grade)
	{
		this.grade = grade;
	}

	public Integer getCollegeId()
	{
		return collegeId;
	}

	public void setCollegeId(Integer collegeId)
	{
		this.collegeId = collegeId;
	}
}