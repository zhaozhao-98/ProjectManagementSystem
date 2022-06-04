package com.dcc.ProjectManagementSystem.entity;

public class Customer {
	private Integer cid;
	private String customer_name;
	private String customer_email;
	private String customer_address;
	private String customer_phone;
	private String customer_head_portrait;


	public String getCustomer_head_portrait() {
		return customer_head_portrait;
	}

	public void setCustomer_head_portrait(String customer_head_portrait) {
		this.customer_head_portrait = customer_head_portrait;
	}

	public String getCustomer_address() {
		return customer_address;
	}
	public void setCustomer_address(String customer_address) {
		this.customer_address = customer_address;
	}
	public String getCustomer_phone() {
		return customer_phone;
	}
	public void setCustomer_phone(String customer_phone) {
		this.customer_phone = customer_phone;
	}
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public String getCustomer_name() {
		return customer_name;
	}
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}
	public String getCustomer_email() {
		return customer_email;
	}
	public void setCustomer_email(String customer_email) {
		this.customer_email = customer_email;
	}
}
