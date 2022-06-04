package com.dcc.ProjectManagementSystem.serviceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dcc.ProjectManagementSystem.entity.Customer;
import com.dcc.ProjectManagementSystem.mapper.OtherMapper;

@Service
@Transactional
public class OtherImp implements OtherMapper {
	@Autowired
	protected OtherMapper otherMapper;
	@Override
	public List<Customer> select_other_list() {
		// TODO Auto-generated method stub
		return otherMapper.select_other_list();
	}

}
