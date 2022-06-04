package com.dcc.ProjectManagementSystem.serviceImp;

import java.util.List;

import com.dcc.ProjectManagementSystem.mapper.FilesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dcc.ProjectManagementSystem.entity.Files;
import com.dcc.ProjectManagementSystem.services.FilesService;

@Service
@Transactional
public class FilesImp implements FilesService {
	@Autowired
	protected FilesMapper filesMapper;
	@Override
	public int insert_file(Files files) {
		// TODO Auto-generated method stub
		return filesMapper.insert_file(files);
	}
	@Override
	public int update_file(Files files) {
		// TODO Auto-generated method stub
		return filesMapper.update_file(files);
	}
	@Override
	public List<Files> select_files(Files file) {
		// TODO Auto-generated method stub
		return filesMapper.select_files(file);
	}
}
