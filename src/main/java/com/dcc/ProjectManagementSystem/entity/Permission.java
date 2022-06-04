package com.dcc.ProjectManagementSystem.entity;

/**
 * @author 86155
 * @权限实体类
 */
public class Permission {
	//id
	private Integer permission_id;
	//插入权限
	private Integer permission_insert;
	//删除权限
	private Integer permission_delete;
	//查询权限
	private Integer permission_select;
	//修改权限
	private Integer permission_update;
	//表id
	private Integer permission_operate_table_num;
	//对应某人的权限标识
	private String permission_code;
	private PointListing pointListing_list;

	public PointListing getPointListing_list() {
		return pointListing_list;
	}

	public void setPointListing_list(PointListing pointListing_list) {
		this.pointListing_list = pointListing_list;
	}

	public Integer getPermission_id() {
		return permission_id;
	}
	public void setPermission_id(Integer permission_id) {
		this.permission_id = permission_id;
	}
	public Integer getPermission_insert() {
		return permission_insert;
	}
	public void setPermission_insert(Integer permission_insert) {
		this.permission_insert = permission_insert;
	}
	public Integer getPermission_delete() {
		return permission_delete;
	}
	public void setPermission_delete(Integer permission_delete) {
		this.permission_delete = permission_delete;
	}
	public Integer getPermission_select() {
		return permission_select;
	}
	public void setPermission_select(Integer permission_select) {
		this.permission_select = permission_select;
	}
	public Integer getPermission_update() {
		return permission_update;
	}
	public void setPermission_update(Integer permission_update) {
		this.permission_update = permission_update;
	}
	public Integer getPermission_operate_table_num() {
		return permission_operate_table_num;
	}
	public void setPermission_operate_table_num(Integer permission_operate_table_num) {
		this.permission_operate_table_num = permission_operate_table_num;
	}
	public String getPermission_code() {
		return permission_code;
	}
	public void setPermission_code(String permission_code) {
		this.permission_code = permission_code;
	}
}
