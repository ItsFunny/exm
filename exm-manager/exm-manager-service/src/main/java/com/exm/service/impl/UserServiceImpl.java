package com.exm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.exm.common.model.ResponseResultVo;
import com.exm.dao.UserDao;
import com.exm.model.Student;
import com.exm.model.User;
import com.exm.service.UserService;

@Service
public class UserServiceImpl implements UserService
{

	@Value("${SEARCH_URL}")
	private String SEARCH_URL;
	@Autowired
	private UserDao userDao;

	@Override
	public User getUserByUserId(Integer userid)
	{
		return userDao.getUserByUserId(userid);
	}

	@Override
	public boolean checkUser(Integer userid, String password)
	{
		User user = userDao.getUserByUserId(userid);

		return user.getPassword().equals(password);
	}

	@Override
	public void addUser(Student student)
	{
		User user = new User();
		user.setRoleId(2);
		user.setPassword("123");
		user.setUserId(student.getUserId());
		userDao.addUser(user);
	}

	@Override
	public void changePwd(String newPwd, Integer userId)
	{
		userDao.changePwd(newPwd, userId);
	}

	@Override
	public ResponseResultVo doChangePwd(Integer userId, String newPwd)
	{
		User user = getUserByUserId(userId);
		if (user == null)
		{
			return new ResponseResultVo(500, "账户不存在", "/user/change/pwd");
		} else if (user.getRoleId().equals(1))
		{
			return new ResponseResultVo(500, "不能修改管理员的密码", "/user/change/pwd");
		}
		user.setPassword(newPwd);
		changePwd(newPwd, userId);
		return new ResponseResultVo(200, "修改成功", "/index");
	}

	@Override
	public boolean checkPwd(String oldPwd, User user)
	{
		return user.getPassword().equals(oldPwd);
	}

	@Override
	public ResponseResultVo updatePwd(String newPwd, String oldPwd, User user)
	{
		if (!checkPwd(oldPwd, user))
		{
			return new ResponseResultVo(500, "old pwd is not right", "/change/pwd");
		}
		changePwd(newPwd, Integer.parseInt(user.getUserId().toString()));
		return new ResponseResultVo(200, "update sucess", "/index");
	}

}
