package com.dcc.ProjectManagementSystem.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.dcc.ProjectManagementSystem.entity.User;

@Repository
public interface UserMapper {
	User single_select_user(@Param("email")String email, @Param("upwd")String upwd);
	List<User> single_select_user_permission(@Param("email")String email, @Param("upwd")String upwd);
	List<User> single_select_user_permission_big(@Param("email")String email);
	List<User> select_users_permissions();
	List<User> select_users();
}
