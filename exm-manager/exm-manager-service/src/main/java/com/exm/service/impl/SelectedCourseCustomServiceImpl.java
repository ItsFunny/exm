package com.exm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exm.dao.SelectedCourseCustomDao;
import com.exm.model.SelectedCourseCustom;
import com.exm.service.SelectedCourseCustomService;

@Service
public class SelectedCourseCustomServiceImpl implements SelectedCourseCustomService
{

	@Autowired
	private SelectedCourseCustomDao selectedCourseCustomDao;
	@Override
	public List<SelectedCourseCustom> getStudentsSelectedTheCourseByCourseId(Integer courseId)
	{
		List<SelectedCourseCustom> studentsSelectedTheCourseByCourseId = selectedCourseCustomDao.getStudentsSelectedTheCourseByCourseId(courseId);
		return studentsSelectedTheCourseByCourseId;
	}

}
