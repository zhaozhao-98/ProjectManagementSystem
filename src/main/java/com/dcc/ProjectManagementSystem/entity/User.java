package com.dcc.ProjectManagementSystem.entity;

/**
 * @author 86155
 * @用户实体类
 */
public class User {
	//id
	private Integer uid;
	//用户名
	private String uname;
	//密码
	private String upwd;
	//权限>permission
	private String permission;
	//权限等级
	private int permission_level;
	//电话
	private String phone;
	//邮箱
	private String email;
	//部门
	private Integer department;
	//真实姓名
	private String realname;
	//是否有效 1有效0无效
	private Integer lockdoor;
	
	private Permission permission_i_d_u_s;
	public Permission getPermission_i_d_u_s() {
		return permission_i_d_u_s;
	}
	public void setPermission_i_d_u_s(Permission permission_i_d_u_s) {
		this.permission_i_d_u_s = permission_i_d_u_s;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getUpwd() {
		return upwd;
	}
	public void setUpwd(String upwd) {
		this.upwd = upwd;
	}
	public String getPermission() {
		return permission;
	}
	public void setPermission(String permission) {
		this.permission = permission;
	}
	public int getPermission_level() {
		return permission_level;
	}
	public void setPermission_level(int permission_level) {
		this.permission_level = permission_level;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getDepartment() {
		return department;
	}
	public void setDepartment(Integer department) {
		this.department = department;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public Integer getLockdoor() {
		return lockdoor;
	}
	public void setLockdoor(Integer lockdoor) {
		this.lockdoor = lockdoor;
	}
}
