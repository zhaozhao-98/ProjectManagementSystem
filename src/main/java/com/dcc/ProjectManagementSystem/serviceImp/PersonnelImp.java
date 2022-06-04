package com.dcc.ProjectManagementSystem.serviceImp;

import java.util.List;

import com.dcc.ProjectManagementSystem.mapper.PersonnelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dcc.ProjectManagementSystem.entity.Personnel;
import com.dcc.ProjectManagementSystem.services.PersonnelService;
@Service
@Transactional
public class PersonnelImp implements PersonnelService {
	@Autowired
	protected PersonnelMapper personnelMapper;
	@Override
	public List<Personnel> select_personnel_list() {
		return personnelMapper.select_personnel_list();
	}
	@Override
	public List<Personnel> select_personnel_one(int pid) {
		// TODO Auto-generated method stub
		return personnelMapper.select_personnel_one(pid);
	}
	@Override
	public int update_stat(Personnel personnel) {
		// TODO Auto-generated method stub
		return personnelMapper.update_stat(personnel);
	}
	@Override
	public List<Personnel> select_personnel_one_info(int pid) {
		// TODO Auto-generated method stub
		return personnelMapper.select_personnel_one_info(pid);
	}
	@Override
	public int insert_personnel(Personnel personnel) {
		// TODO Auto-generated method stub
		return personnelMapper.insert_personnel(personnel);
	}
	@Override
	public Long select_personnel_rows() {
		// TODO Auto-generated method stub
		return 	personnelMapper.select_personnel_rows();
	}
}
