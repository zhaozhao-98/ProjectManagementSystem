package com.dcc.ProjectManagementSystem.serviceImp;

import java.util.List;

import com.dcc.ProjectManagementSystem.mapper.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dcc.ProjectManagementSystem.entity.Customer;
import com.dcc.ProjectManagementSystem.services.CustomerService;

@Service
@Transactional
public class CustomerImp implements CustomerService {
	@Autowired
	protected CustomerMapper customerMapper;
	@Override
	public List<Customer>  select_customer_list() {
		// TODO Auto-generated method stub
		return customerMapper.select_customer_list();
	}
	@Override
	public int insert_customer(Customer customer) {
		// TODO Auto-generated method stub
		return customerMapper.insert_customer(customer);
	}
}
