package com.exm.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Student
{
	@Override
	public String toString()
	{
		return "Student [id=" + id + ", userId=" + userId + ", studentName=" + studentName + ", sex=" + sex
				+ ", birthYear=" + birthYear + ", grade=" + grade + ", collegeId=" + collegeId + "]";
	}

	private Integer id;

	private Long userId;

	private String studentName;

	private String sex;

	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private Date birthYear;

	private Date grade;

	private Integer collegeId;

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public Long getUserId()
	{
		return userId;
	}

	public void setUserId(Long userId)
	{
		this.userId = userId;
	}

	public String getStudentName()
	{
		return studentName;
	}

	public void setStudentName(String studentName)
	{
		this.studentName = studentName == null ? null : studentName.trim();
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