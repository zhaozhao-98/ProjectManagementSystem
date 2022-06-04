package com.dcc.ProjectManagementSystem.serviceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dcc.ProjectManagementSystem.entity.Permission;
import com.dcc.ProjectManagementSystem.mapper.PermissionMapper;
import com.dcc.ProjectManagementSystem.services.PermissionService;

@Service
@Transactional
public class PermissionImp implements PermissionService {
	@Autowired
	protected PermissionMapper permissionmapper;
	@Override
	public Permission select_this_user_permission(String permission_code, int permission_operate_table_num) {
		// TODO Auto-generated method stub
		return permissionmapper.select_this_user_permission(permission_code, permission_operate_table_num);
	}

}
