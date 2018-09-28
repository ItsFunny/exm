package com.exm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.exm.model.User;

@Mapper
public interface UserDao
{
	@Select("select * from user")
	List<User> findAllUsers();

	// get user
	@Select("select * from user where user_id =#{userId}")
	User getUserByUserId(Integer userId);

	@Insert("insert into user values (null,#{userId},#{password},#{roleId})")
	void addUser(User user);

	@Delete("delete from user where user_id=#{userId}")
	void deleteUser(Integer userId);

	@Update("update user set password=#{password} where user_id=#{userId}")
	void changePwd(@Param("password")String password,@Param("userId")Integer userId);
}
