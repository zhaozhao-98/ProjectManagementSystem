package com.dcc.ProjectManagementSystem.services;

import java.util.List;

import com.dcc.ProjectManagementSystem.entity.Files;

public interface FilesService {
	int insert_file(Files files);
	int update_file(Files files);
	List<Files> select_files(Files file);
}
