package com.dcc.ProjectManagementSystem.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.jdom2.JDOMException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dcc.ProjectManagementSystem.entity.Customer;
import com.dcc.ProjectManagementSystem.serviceImp.CustomerImp;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/customer_controller")
public class CustomerController {
	@Autowired
	private CustomerImp customerimp;
	/*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*/
	@RequestMapping(value="/select_customer_list",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> selectCustomerList(HttpServletRequest request) throws IOException, JDOMException {
		Map<String, Object> map=new HashMap<>();
		if(BaseController.selectPermissionController(request)) {
			List<Customer> list_customer= (List<Customer>)customerimp.select_customer_list();
			map.put("list_customer", list_customer);
			map.put("msg","success");
		}else {
			map.put("msg","RestrictedPermission");
		}
		return map ;
	}

	/*添加*添加*添加*添加*添加*添加*添加*添加*添加*添加*添加*添加*添加*添加*添加*添加*添加*添加*添加*添加*添加*添加*添加*添加*添加*添加*添加*/
	@RequestMapping(value="/insert_customer",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> insertCustomer(HttpServletRequest request, Customer customer) throws JDOMException, IOException{
		Map<String, Object> map=new HashMap<>();
		if(BaseController.insertPermissionController(request)) {
			if(customer.getCustomer_name().equals("")||customer.getCustomer_name()==null) {
				map.put("msg","fail");
			}else{
				if(Objects.equals(customer.getCustomer_address(), "") ||customer.getCustomer_address()==null) {
					customer.setCustomer_address("");
				}
				if(Objects.equals(customer.getCustomer_email(), "") ||customer.getCustomer_email()==null) {
					customer.setCustomer_email("");
				}
				if(Objects.equals(customer.getCustomer_phone(), "") ||customer.getCustomer_phone()==null) {
					customer.setCustomer_phone("");
				}
				customer.setCustomer_head_portrait("");
				customerimp.insert_customer(customer);
				map.put("msg","success");
			}
		}else {
			map.put("msg","RestrictedPermission");
		}
		return map ;
	}
}
