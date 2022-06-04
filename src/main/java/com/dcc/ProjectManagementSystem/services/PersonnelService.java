package com.dcc.ProjectManagementSystem.services;

import java.util.List;

import com.dcc.ProjectManagementSystem.entity.Personnel;

public interface PersonnelService {
	List<Personnel> select_personnel_list();
	List<Personnel> select_personnel_one(int pid);
	List<Personnel> select_personnel_one_info(int pid);
	int update_stat(Personnel personnel);
	int insert_personnel(Personnel personnel);
	Long select_personnel_rows();
}
