package com.dcc.ProjectManagementSystem.services;

import java.util.List;

import com.dcc.ProjectManagementSystem.entity.User;

public interface UserService {
	User single_select_user(String email, String upwd);
	List<User> single_select_user_permission(String email, String upwd);
	List<User> single_select_user_permission_big(String email);
	List<User> select_users_permissions(int permission_level);
	List<User> select_users(int permission_level);
	int updatePermissions(String permission_code,int data_operation_value,int permission_id,int permission_insert,int permission_delete,int permission_update,int permission_select);
}
