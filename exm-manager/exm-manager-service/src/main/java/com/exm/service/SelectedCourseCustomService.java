package com.exm.service;

import java.util.List;

import com.exm.model.SelectedCourseCustom;

public interface SelectedCourseCustomService
{

	
	List<SelectedCourseCustom> getStudentsSelectedTheCourseByCourseId(Integer courseId);
}
