package com.dcc.ProjectManagementSystem.entity;

public class Personnel {
	private Integer pid;
	private String personnel_name;
	private String personnel_phone;
	private String personnel_email;
	private String user_stat;
	private String personnel_head_portrait;
	private Integer lock_door;

	public String getPersonnel_head_portrait() {
		return personnel_head_portrait;
	}

	public void setPersonnel_head_portrait(String personnel_head_portrait) {
		this.personnel_head_portrait = personnel_head_portrait;
	}

	public Integer getLock_door() {
		return lock_door;
	}

	public void setLock_door(Integer lock_door) {
		this.lock_door = lock_door;
	}

	public String getUser_stat() {
		return user_stat;
	}
	public void setUser_stat(String user_stat) {
		this.user_stat = user_stat;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public String getPersonnel_name() {
		return personnel_name;
	}
	public void setPersonnel_name(String personnel_name) {
		this.personnel_name = personnel_name;
	}
	public String getPersonnel_phone() {
		return personnel_phone;
	}
	public void setPersonnel_phone(String personnel_phone) {
		this.personnel_phone = personnel_phone;
	}
	public String getPersonnel_email() {
		return personnel_email;
	}
	public void setPersonnel_email(String personnel_email) {
		this.personnel_email = personnel_email;
	}

	
}
