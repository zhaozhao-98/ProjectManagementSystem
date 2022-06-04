package com.dcc.ProjectManagementSystem.entity;

public class Files {
	private Integer file_id;
	private String file_name;
	private String file_address;
	private Integer file_pid;
	private Integer file_tag;
	private Integer file_pm_id;
	private Integer file_valid;
	private Long create_date;
	private Project project_files;
	private Personnel personnel_files;
	

	public Project getProject_files() {
		return project_files;
	}
	public void setProject_files(Project project_files) {
		this.project_files = project_files;
	}
	public Personnel getPersonnel_files() {
		return personnel_files;
	}
	public void setPersonnel_files(Personnel personnel_files) {
		this.personnel_files = personnel_files;
	}
	public Long getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Long create_date) {
		this.create_date = create_date;
	}
	public Integer getFile_valid() {
		return file_valid;
	}
	public void setFile_valid(Integer file_valid) {
		this.file_valid = file_valid;
	}
	public Integer getFile_pm_id() {
		return file_pm_id;
	}
	public void setFile_pm_id(Integer file_pm_id) {
		this.file_pm_id = file_pm_id;
	}
	public Integer getFile_id() {
		return file_id;
	}
	public void setFile_id(Integer file_id) {
		this.file_id = file_id;
	}
	public String getFile_name() {
		return file_name;
	}
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}
	public String getFile_address() {
		return file_address;
	}
	public void setFile_address(String file_address) {
		this.file_address = file_address;
	}
	public Integer getFile_pid() {
		return file_pid;
	}
	public void setFile_pid(Integer file_pid) {
		this.file_pid = file_pid;
	}
	public Integer getFile_tag() {
		return file_tag;
	}
	public void setFile_tag(Integer file_tag) {
		this.file_tag = file_tag;
	}
	
}
