package com.dcc.ProjectManagementSystem.services;

import com.dcc.ProjectManagementSystem.entity.Permission;

public interface PermissionService {
	Permission select_this_user_permission(String permission_code, int permission_operate_table_num);
}
