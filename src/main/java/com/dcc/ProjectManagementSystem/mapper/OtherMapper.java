package com.dcc.ProjectManagementSystem.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.dcc.ProjectManagementSystem.entity.Customer;

@Repository
public interface OtherMapper {
	List<Customer> select_other_list();
}
