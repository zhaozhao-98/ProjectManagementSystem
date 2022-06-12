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
	List<User> select_users_permissions(int permission_level);
	List<User> select_users(int permission_level);
	int updatePermissions(@Param("permission_code")String permission_code,@Param("data_operation_value")int data_operation_value
							,@Param("permission_id")int permission_id
							,@Param("permission_insert")int permission_insert
							,@Param("permission_delete")int permission_delete
							,@Param("permission_update")int permission_update
							,@Param("permission_select")int permission_select);
}
