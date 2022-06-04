package com.dcc.ProjectManagementSystem.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.dcc.ProjectManagementSystem.entity.Customer;

@Repository
public interface CustomerMapper {
	List<Customer> select_customer_list();
	int insert_customer(Customer customer);
	int select_personnel_rows();
}
