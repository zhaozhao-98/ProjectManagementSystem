package com.dcc.ProjectManagementSystem.services;

import java.util.List;

import com.dcc.ProjectManagementSystem.entity.Customer;

public interface CustomerService {
	List<Customer> select_customer_list();
	int insert_customer(Customer customer);
}
