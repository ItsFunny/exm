package com.exm.service;

import com.exm.common.model.ResponseResultVo;
import com.exm.model.Student;
import com.exm.model.User;

public interface UserService
{
	User getUserByUserId(Integer userid);

	boolean checkUser(Integer userid, String password);

	void addUser(Student student);

	void changePwd(String newPwd,Integer userId);
	
	ResponseResultVo doChangePwd(Integer userId,String newPwd);
	
	
	boolean checkPwd(String passoword,User user);
	ResponseResultVo updatePwd(String newPwd,String oldPwd,User user);

}
