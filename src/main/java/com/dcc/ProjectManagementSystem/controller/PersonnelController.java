package com.dcc.ProjectManagementSystem.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jdom2.JDOMException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dcc.ProjectManagementSystem.entity.Customer;
import com.dcc.ProjectManagementSystem.entity.Personnel;
import com.dcc.ProjectManagementSystem.entity.Project;
import com.dcc.ProjectManagementSystem.serviceImp.CustomerImp;
import com.dcc.ProjectManagementSystem.serviceImp.OtherImp;
import com.dcc.ProjectManagementSystem.serviceImp.PersonnelImp;
import com.dcc.ProjectManagementSystem.serviceImp.ProjectImp;
import com.spire.ms.System.Collections.ArrayList;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/personnel_controller")
public class PersonnelController {
	@Autowired
	private PersonnelImp personnelimp;
	@Autowired
	private CustomerImp customerimp;
	@Autowired
	private OtherImp otherimp;
	@Autowired
	private ProjectImp projectimp;

	/*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*/
	@RequestMapping(value="/select_personnel_list",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> selectPersonnelList(HttpServletRequest request) throws IOException, JDOMException {
		Map<String, Object> map=new HashMap<>();
		if(BaseController.selectPermissionController(request)) {
			List<Personnel> list_personnel= (List<Personnel>)personnelimp.select_personnel_list();
			List<Customer> list_customer= (List<Customer>)customerimp.select_customer_list();
			List<Customer> list_other= (List<Customer>)otherimp.select_other_list();
			map.put("list_other", list_other);
			map.put("list_personnel", list_personnel);
			map.put("list_customer", list_customer);
			map.put("msg","success");
		}else {
			map.put("msg","RestrictedPermission");
		}
		return map ;
	}
	/*查询收藏项目*/
	@RequestMapping(value="/select_personnel_one",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> 	selectPersonnelOne(HttpServletRequest request,Integer pid,int limit,int item) throws IOException, JDOMException {
		Map<String, Object> map=new HashMap<>();
		if(BaseController.selectPermissionController(request)) {
			List<Personnel> list_stat= (List<Personnel>)personnelimp.select_personnel_one(pid);
			List<Project> project_list = new ArrayList();
			String this_user_stat=list_stat.get(0).getUser_stat();
			String[] retval = this_user_stat.split("\\|");
			for (String s : retval) {
				List<Project> project = projectimp.list_select_project_mod_all(0, 0, 0,s, limit, item);
				if (project != null) {
					project_list.addAll(project);
				}
			}
			map.put("project_list_rows", project_list.size());
			map.put("list_project_all", project_list);
			map.put("msg","success");
		}else {
			map.put("msg","RestrictedPermission");
		}
		return map ;
	}
	/*添加*添加*添加*添加*添加*添加*添加*添加*添加*添加*添加*添加*添加*添加*添加*添加*添加*添加*添加*添加*添加*添加*添加*添加*添加*添加*添加*添加*/
	@RequestMapping(value="/insert_personnel",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> insertPersonnel(HttpServletRequest request, Personnel personnel) throws JDOMException, IOException{
		Map<String, Object> map=new HashMap<>();
		if(BaseController.insertPermissionController(request)) {
			if(personnel.getPersonnel_name()==""||personnel.getPersonnel_name()==null) {
				map.put("msg","fail");
			}else if(personnel.getPersonnel_email()==""||personnel.getPersonnel_email()==null) {
				map.put("msg","fail");
			}else {
				if(personnel.getPersonnel_phone()==""||personnel.getPersonnel_phone()==null) {
					personnel.setPersonnel_phone("0");
				}
				personnel.setUser_stat("");
				personnel.setPersonnel_head_portrait("");
				personnel.setLock_door(1);
				Long rows=personnelimp.select_personnel_rows();
				personnel.setPid(rows.intValue()+1);
				personnelimp.insert_personnel(personnel);
				map.put("msg","success");
			}
		}else {
			map.put("msg","RestrictedPermission");
		}
		return map ;
	}
}
