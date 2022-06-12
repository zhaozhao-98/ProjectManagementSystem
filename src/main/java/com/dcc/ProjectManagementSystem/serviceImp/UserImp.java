package com.dcc.ProjectManagementSystem.serviceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dcc.ProjectManagementSystem.entity.User;
import com.dcc.ProjectManagementSystem.mapper.UserMapper;
import com.dcc.ProjectManagementSystem.services.UserService;

@Service
@Transactional
public class UserImp implements UserService {
	@Autowired
	protected UserMapper userMapper;

	@Override
	public User single_select_user(String email, String upwd) {
		// TODO Auto-generated method stub
		return userMapper.single_select_user(email,upwd);
	}

	@Override
	public List<User> single_select_user_permission(String email, String upwd) {
		// TODO Auto-generated method stub
		return userMapper.single_select_user_permission(email,upwd);
	}

	@Override
	public List<User> single_select_user_permission_big(String email) {
		return userMapper.single_select_user_permission_big(email);
	}

	@Override
	public List<User> select_users_permissions(int permission_level) {
		return userMapper.select_users_permissions(permission_level);
	}

	@Override
	public List<User> select_users(int permission_level) {
		return userMapper.select_users(permission_level);
	}

	@Override
	public int updatePermissions(String permission_code, int data_operation_value, int permission_id,int permission_insert,int permission_delete,int permission_update,int permission_select) {
		return userMapper.updatePermissions(permission_code,
				data_operation_value,permission_id,
				permission_insert,
				permission_delete,
				permission_update,
				permission_select);
	}
}
