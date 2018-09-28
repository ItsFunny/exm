package com.exm.search.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exm.dao.StudentDao;
import com.exm.model.Student;
import com.exm.search.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService
{
	@Autowired
	private StudentDao studentDao;

	@Override
	public List<Student> getStudentsByStudentName(String studentName)
	{

		List<Student> students = studentDao.getStudentsByUserNameAndLike("%" + studentName + "%");
		if (students == null)
		{
			return null;
		}
		return students;
	}

	// public static void main(String[] args)
	// {
	// int parseInt = Integer.parseInt("123");
	// System.out.println(parseInt);
	// }
	@Override
	public List<Student> getStudentsByUserId(Integer userId)
	{
		List<Student> students = new ArrayList<>();
		Student student = studentDao.getStudentByUserId(userId);
		if (student == null)
		{
			return null;
		}
		students.add(student);
		return students;
	}

	@Override
	public List<Student> searchStudents(String searchQuery)
	{
		List<Student> students = new ArrayList<Student>();
		try
		{
			int parseInt = Integer.parseInt(searchQuery);
			students = getStudentsByUserId(parseInt);
		} catch (Exception e)
		{
			students = getStudentsByStudentName(searchQuery);
		}
		return students;
	}

}
