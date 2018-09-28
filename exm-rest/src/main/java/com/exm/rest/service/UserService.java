package com.exm.rest.service;

import com.exm.model.Student;
import com.exm.model.Teacher;

public interface UserService
{
	void addUser(Student student);
	
	void addUser(Teacher teacher);
}
