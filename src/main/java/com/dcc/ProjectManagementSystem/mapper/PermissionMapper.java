package com.dcc.ProjectManagementSystem.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.dcc.ProjectManagementSystem.entity.Permission;

@Repository
public interface PermissionMapper {
	Permission select_this_user_permission(@Param("permission_code")String permission_code, @Param("permission_operate_table_num")int permission_operate_table_num);
}
