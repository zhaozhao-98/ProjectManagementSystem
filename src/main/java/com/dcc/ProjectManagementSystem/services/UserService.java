package com.dcc.ProjectManagementSystem.services;

import java.util.List;

import com.dcc.ProjectManagementSystem.entity.User;

public interface UserService {
	User single_select_user(String email, String upwd);
	List<User> single_select_user_permission(String email, String upwd);
	List<User> single_select_user_permission_big(String email);
}
