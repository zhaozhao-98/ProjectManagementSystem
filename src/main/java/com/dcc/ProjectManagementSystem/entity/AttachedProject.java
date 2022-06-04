package com.dcc.ProjectManagementSystem.entity;

public class AttachedProject {
	private Integer attached_id;
	private Long attached_start_time;
	private Long attached_end_time;
	private Long attached_expected_completion_date;
	private Long attached_actual_finishing_date;
	private Long attached_bill_time;
	private Integer attached_time_fee;
	private Integer attached_moon_fee;
	private Integer attached_currency;
	private Integer attached_status;
	private Integer project_pid;
	private String attached_project_name;
	private String attached_remark;
	private String attached_address;
	private String attached_b_address;
	private Long create_time;
	private String attached_services_num;

	public Long getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Long create_time) {
		this.create_time = create_time;
	}

	public String getAttached_services_num() {
		return attached_services_num;
	}

	public void setAttached_services_num(String attached_services_num) {
		this.attached_services_num = attached_services_num;
	}

	public String getAttached_b_address() {
		return attached_b_address;
	}
	public void setAttached_b_address(String attached_b_address) {
		this.attached_b_address = attached_b_address;
	}
	//状态（other）
	private Other status_other;
	
	public Other getStatus_other() {
		return status_other;
	}
	public void setStatus_other(Other status_other) {
		this.status_other = status_other;
	}


	public Long getAttached_start_time() {
		return attached_start_time;
	}
	public void setAttached_start_time(Long attached_start_time) {
		this.attached_start_time = attached_start_time;
	}
	public Long getAttached_end_time() {
		return attached_end_time;
	}
	public void setAttached_end_time(Long attached_end_time) {
		this.attached_end_time = attached_end_time;
	}

	public Integer getAttached_id() {
		return attached_id;
	}

	public void setAttached_id(Integer attached_id) {
		this.attached_id = attached_id;
	}

	public Long getAttached_expected_completion_date() {
		return attached_expected_completion_date;
	}

	public void setAttached_expected_completion_date(Long attached_expected_completion_date) {
		this.attached_expected_completion_date = attached_expected_completion_date;
	}

	public Long getAttached_actual_finishing_date() {
		return attached_actual_finishing_date;
	}
	public void setAttached_actual_finishing_date(Long attached_actual_finishing_date) {
		this.attached_actual_finishing_date = attached_actual_finishing_date;
	}
	public Integer getAttached_time_fee() {
		return attached_time_fee;
	}
	public void setAttached_time_fee(Integer attached_time_fee) {
		this.attached_time_fee = attached_time_fee;
	}
	public Integer getAttached_moon_fee() {
		return attached_moon_fee;
	}
	public void setAttached_moon_fee(Integer attached_moon_fee) {
		this.attached_moon_fee = attached_moon_fee;
	}
	public Integer getAttached_currency() {
		return attached_currency;
	}
	public void setAttached_currency(Integer attached_currency) {
		this.attached_currency = attached_currency;
	}
	public Integer getAttached_status() {
		return attached_status;
	}
	public void setAttached_status(Integer attached_status) {
		this.attached_status = attached_status;
	}

	public Long getAttached_bill_time() {
		return attached_bill_time;
	}
	public void setAttached_bill_time(Long attached_bill_time) {
		this.attached_bill_time = attached_bill_time;
	}
	public Integer getProject_pid() {
		return project_pid;
	}
	public void setProject_pid(Integer project_pid) {
		this.project_pid = project_pid;
	}
	public String getAttached_project_name() {
		return attached_project_name;
	}
	public void setAttached_project_name(String attached_project_name) {
		this.attached_project_name = attached_project_name;
	}
	public String getAttached_remark() {
		return attached_remark;
	}
	public void setAttached_remark(String attached_remark) {
		this.attached_remark = attached_remark;
	}
	public String getAttached_address() {
		return attached_address;
	}
	public void setAttached_address(String attached_address) {
		this.attached_address = attached_address;
	}

}
