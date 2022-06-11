package com.dcc.ProjectManagementSystem.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dcc.ProjectManagementSystem.entity.Permission;
import com.dcc.ProjectManagementSystem.entity.User;
import com.dcc.ProjectManagementSystem.serviceImp.PermissionImp;
import com.dcc.ProjectManagementSystem.utils.SysVersion;

@Controller
@RequestMapping("/base_controller")
public class BaseController {
	@Autowired
	private PermissionImp permissionimp;
	public static BaseController permissionimp_backup;
    @PostConstruct // 初始化
    public void init(){
		permissionimp_backup = this;
		permissionimp_backup.permissionimp = this.permissionimp;
    }

    //主方法 用于判断用户是否有权限进行添加
	public static  boolean insertPermissionController(HttpServletRequest request) throws JDOMException, IOException {
		HttpSession session = request.getSession();
		List<User> login_user_permission=(List<User>) session.getAttribute("login_user_permission");
		SAXBuilder builder = new SAXBuilder();
		Map<String, Object> builder_path= SysVersion.permission_xml();
		Document operation = builder.build(builder_path.get("builder_path")+"permission_operation.xml");
		Element operation_beauties = operation.getRootElement();
		List<Element> user_inserts = operation_beauties.getChildren("user_insert");
		for(Element user_insert:user_inserts ) {
			String user_insert_name = user_insert.getChildText("name");
			Element user_op_name = user_insert.getChild("name");
			String permission_id = user_op_name.getAttributeValue("id");
			String mainController = user_op_name.getAttributeValue("mainController");

			if(request.getRequestURI().equals("/ProjectManagementSystem/"+mainController+"/"+user_insert_name)) {
				if(login_user_permission.get(Integer.parseInt(permission_id) - 1).getPermission_CRUD().getPermission_operate_table_num().equals(Integer.valueOf(permission_id))) {
					String permission=login_user_permission.get(0).getPermission();
					Integer table_id=Integer.valueOf(permission_id);
					Permission p = permissionimp_backup.permissionimp.select_this_user_permission(permission,table_id);
					if(p.getPermission_insert()==1) {
						//此账户拥有权限
						return true;
					}
				}
			}
		}
		return false;
	}
    //主方法 用于判断用户是否有权限进行查询
	public static  boolean selectPermissionController(HttpServletRequest request) throws JDOMException, IOException {
		HttpSession session = request.getSession();
		List<User> login_user_permission=(List<User>) session.getAttribute("login_user_permission");
		SAXBuilder builder = new SAXBuilder();
		Map<String, Object> builder_path= SysVersion.permission_xml();
		Document operation = builder.build(builder_path.get("builder_path")+"permission_operation.xml");
		Element operation_beauties = operation.getRootElement();
		List<Element> user_selects = operation_beauties.getChildren("user_select");
		for(Element user_select:user_selects ) {
			String user_select_name = user_select.getChildText("name");
			Element user_op_name = user_select.getChild("name");
			String permission_id = user_op_name.getAttributeValue("id");
			String mainController = user_op_name.getAttributeValue("mainController");
			if(request.getRequestURI().equals("/ProjectManagementSystem/"+mainController+"/"+user_select_name)) {
				if(login_user_permission.get(Integer.parseInt(permission_id) - 1).getPermission_CRUD().getPermission_operate_table_num().equals(Integer.valueOf(permission_id))) {
					String permission=login_user_permission.get(0).getPermission();
					Integer table_id=Integer.valueOf(permission_id);
					Permission p = permissionimp_backup.permissionimp.select_this_user_permission(permission,table_id);
					if(p.getPermission_select()==1) {
						//此账户拥有权限
						return true;
					}
				}
			}
		}
		return false;
	}
	//主方法 用于判断用户是否有权限进行查询
	public static  boolean updatePermissionController(HttpServletRequest request) throws JDOMException, IOException {
		HttpSession session = request.getSession();
		List<User> login_user_permission=(List<User>) session.getAttribute("login_user_permission");
		SAXBuilder builder = new SAXBuilder();
		Map<String, Object> builder_path= SysVersion.permission_xml();
		Document operation = builder.build(builder_path.get("builder_path")+"permission_operation.xml");
		Element operation_beauties = operation.getRootElement();
		List<Element> user_updates = operation_beauties.getChildren("user_update");
		for(Element user_update:user_updates ) {
			String user_update_name = user_update.getChildText("name");
			Element user_op_name = user_update.getChild("name");
			String permission_id = user_op_name.getAttributeValue("id");
			String mainController = user_op_name.getAttributeValue("mainController");

			if(request.getRequestURI().equals("/ProjectManagementSystem/"+mainController+"/"+user_update_name)) {
				if(login_user_permission.get(Integer.parseInt(permission_id) - 1).getPermission_CRUD().getPermission_operate_table_num().equals(Integer.valueOf(permission_id))) {
					String permission=login_user_permission.get(0).getPermission();
					Integer table_id=Integer.valueOf(permission_id);
					Permission p = permissionimp_backup.permissionimp.select_this_user_permission(permission,table_id);
					if(p.getPermission_update()==1) {
						//此账户拥有权限
						return true;
					}
				}
			}
		}
		return false;
	}
	public static  boolean deletePermissionController(HttpServletRequest request) throws JDOMException, IOException {
		HttpSession session = request.getSession();
		List<User> login_user_permission=(List<User>) session.getAttribute("login_user_permission");
		SAXBuilder builder = new SAXBuilder();
		Map<String, Object> builder_path= SysVersion.permission_xml();
		Document operation = builder.build(builder_path.get("builder_path")+"permission_operation.xml");
		Element operation_beauties = operation.getRootElement();
		List<Element> user_deletes = operation_beauties.getChildren("user_delete");
		for(Element user_delete:user_deletes ) {
			String user_delete_name = user_delete.getChildText("name");
			Element user_op_name = user_delete.getChild("name");
			String permission_id = user_op_name.getAttributeValue("id");
			String mainController = user_op_name.getAttributeValue("mainController");

			if(request.getRequestURI().equals("/ProjectManagementSystem/"+mainController+"/"+user_delete_name)) {
				if(login_user_permission.get(Integer.parseInt(permission_id) - 1).getPermission_CRUD().getPermission_operate_table_num().equals(Integer.valueOf(permission_id))) {
					String permission=login_user_permission.get(0).getPermission();
					Integer table_id=Integer.valueOf(permission_id);
					Permission p = permissionimp_backup.permissionimp.select_this_user_permission(permission,table_id);
					if(p.getPermission_delete()==1) {
						//此账户拥有权限
						return true;
					}
				}
			}
		}
		return false;
	}
	//初始化权限
	@RequestMapping(value="/initialize_permission",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> initializePermission(HttpServletRequest request,int table_id)  {
		Map<String,Object> map=new HashMap<>();
		HttpSession session=request.getSession();
		List<User> login_user_permission=(List<User>) session.getAttribute("login_user_permission");
		String permission=login_user_permission.get(table_id-1).getPermission();
		Permission p = permissionimp_backup.permissionimp.select_this_user_permission(permission,table_id);
		if(p.getPermission_insert()==1) {
			map.put("insert_project_msg","success");
		}else {
			map.put("insert_project_msg","fail");
		}
		return map;
	}
}
