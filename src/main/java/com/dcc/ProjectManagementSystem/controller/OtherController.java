package com.dcc.ProjectManagementSystem.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dcc.ProjectManagementSystem.entity.Customer;
import com.dcc.ProjectManagementSystem.serviceImp.OtherImp;

@Controller
@RequestMapping("/other_controller")
public class OtherController {
	@Autowired
	private OtherImp otherimp;
	/*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*/
	@RequestMapping(value="/select_other_list",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> selectCustomerList(){
		Map<String, Object> map=new HashMap<>();
		List<Customer> list_other= (List<Customer>)otherimp.select_other_list();
		map.put("list_other", list_other);
		return map ;
	}
}
