package com.dcc.ProjectManagementSystem.serviceImp;

import java.util.List;

import com.dcc.ProjectManagementSystem.entity.AttachedProject;
import com.dcc.ProjectManagementSystem.mapper.AttachedProjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dcc.ProjectManagementSystem.services.AttachedProjectService;
@Service
@Transactional
public class AttachedProjectImp implements AttachedProjectService {
	@Autowired
	protected AttachedProjectMapper AttachedProjectMapper;
	@Override
	public List<AttachedProject> select_attached(int attachedid) {
		// TODO Auto-generated method stub
		return AttachedProjectMapper.select_attached(attachedid);
	}

	@Override
	public List<AttachedProject> selectAttachedServiceNum() {
		return AttachedProjectMapper.selectAttachedServiceNum();
	}

	@Override
	public int insert_attached(AttachedProject attachedproject) {
		// TODO Auto-generated method stub
		return AttachedProjectMapper.insert_attached(attachedproject);
	}
	@Override
	public int del_attached(int attachedid) {
		// TODO Auto-generated method stub
		return AttachedProjectMapper.del_attached(attachedid);
	}

	@Override
	public AttachedProject select_attached_forattaid(int attachedid) {
		// TODO Auto-generated method stub
		return AttachedProjectMapper.select_attached_forattaid(attachedid);
	}

	@Override
	public int update_attachedProject_one(AttachedProject update_attachedProject_one) {
		// TODO Auto-generated method stub
		return AttachedProjectMapper.update_attachedProject_one(update_attachedProject_one);
	}

	@Override
	public Long count_service_num(Long start_datetime, Long end_datetime) {
		return AttachedProjectMapper.count_service_num(start_datetime,end_datetime);
	}

	@Override
	public Long select_attached_by_service_num(String attached_services_num) {
		return AttachedProjectMapper.select_attached_by_service_num(attached_services_num);
	}
}
