package com.dcc.ProjectManagementSystem.services;

import com.dcc.ProjectManagementSystem.entity.AttachedProject;

import java.util.List;


public interface AttachedProjectService {
	List<AttachedProject> select_attached(int attachedid);
	List<AttachedProject> selectAttachedServiceNum();
	AttachedProject select_attached_forattaid(int attachedid);
	int insert_attached(AttachedProject attachedproject);
	int del_attached(int attachedid);
	int update_attachedProject_one(AttachedProject update_attachedProject_one);

	Long count_service_num(Long start_datetime,Long end_datetime);

	Long select_attached_by_service_num(String attached_services_num);
}
