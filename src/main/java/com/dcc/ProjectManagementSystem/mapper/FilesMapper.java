package com.dcc.ProjectManagementSystem.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.dcc.ProjectManagementSystem.entity.Files;

@Repository
public interface FilesMapper {
	int insert_file(Files files);
	int update_file(Files files);
	List<Files> select_files(Files file);
}
