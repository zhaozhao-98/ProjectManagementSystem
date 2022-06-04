package com.dcc.ProjectManagementSystem.entity;

public class Chat {
	private Integer cid;
	private String chat_text;
	private String chat_name;
	private Long chat_time;
	private Integer chat_tag;
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public String getChat_text() {
		return chat_text;
	}
	public void setChat_text(String chat_text) {
		this.chat_text = chat_text;
	}
	public String getChat_name() {
		return chat_name;
	}
	public void setChat_name(String chat_name) {
		this.chat_name = chat_name;
	}
	public Long getChat_time() {
		return chat_time;
	}
	public void setChat_time(Long chat_time) {
		this.chat_time = chat_time;
	}
	public Integer getChat_tag() {
		return chat_tag;
	}
	public void setChat_tag(Integer chat_tag) {
		this.chat_tag = chat_tag;
	}
	
}
