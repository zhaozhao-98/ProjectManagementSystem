package com.dcc.ProjectManagementSystem.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.dcc.ProjectManagementSystem.utils.SysVersion;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dcc.ProjectManagementSystem.entity.User;
import com.dcc.ProjectManagementSystem.entity.Personnel;
import com.dcc.ProjectManagementSystem.serviceImp.PersonnelImp;
import com.dcc.ProjectManagementSystem.serviceImp.UserImp;
import com.dcc.ProjectManagementSystem.utils.Md5Utils;
import com.dcc.ProjectManagementSystem.utils.CreateUser;

//用户控制层

@Controller
@RequestMapping("/user_controller")
public class UserController {
	@Autowired
	private UserImp userimp;
	@Autowired
	private PersonnelImp personnelimp;
	/**
	 * @用户登录
	 */
	@RequestMapping(value="/login",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> login(String email,String upwd,HttpServletRequest request){
		HttpSession session = request.getSession();
		upwd= Md5Utils.MD5(upwd);
		User login_user=userimp.single_select_user(email, upwd);
		Map<String, Object> login_user_map=new HashMap<>();
		if(login_user!=null) {
			if(login_user.getUid()!=0) {
				session.setAttribute("user", login_user);
				session.setAttribute("personnel", personnelimp.select_personnel_one_info(login_user.getUid()));
				Integer level=login_user.getPermission_level();
				String uuid_tag=UUID.randomUUID().toString();
				if(Md5Utils.MD5("super"+"mwfDCCSx@ZI"+ Md5Utils.MD5(level.toString())).equals("7ff491a585c47e0f0256280fa72930ea")) {
					//超级管理员
					CreateUser.xml_usercode(login_user.getEmail(),login_user.getUid(),login_user.getPermission(),uuid_tag);
					session.setAttribute("user_uuid",uuid_tag);
					session.setAttribute("user_index", "c4ca4238a0b923820dcc509a6f75849b");
				}else if(Md5Utils.MD5("super"+"mwfDCCSx@ZI"+ Md5Utils.MD5(level.toString())).equals("d73110b578af2ba7967d866d754834a5")) {
					//管理员
					CreateUser.xml_usercode(login_user.getEmail(),login_user.getUid(),login_user.getPermission(),uuid_tag);
					session.setAttribute("user_uuid",uuid_tag);
					session.setAttribute("user_index","c81e728d9d4c2f636f067f89cc14862c");
				}else if(Md5Utils.MD5("super"+"mwfDCCSx@ZI"+ Md5Utils.MD5(level.toString())).equals("9e922299fe554a767569a39735b6b5bf")) {
					//用户
					CreateUser.xml_usercode(login_user.getEmail(),login_user.getUid(),login_user.getPermission(),uuid_tag);
					session.setAttribute("user_uuid",uuid_tag);
					session.setAttribute("user_index","eccbc87e4b5ce2fe28308fd9f2a7baf3");
				}else{
					//其他
					session.setAttribute("user_uuid",uuid_tag);
					session.setAttribute("user_index","334c4a4c42fdb79d7ebc3e73b517e6f8");
				}
				List<User> login_user_permission=userimp.single_select_user_permission(email, upwd);
				session.setAttribute("login_user_permission",login_user_permission);
				login_user_map.put("msg", "success");
			}else {
				login_user_map.put("msg", "fail");
			}
		}else {
			login_user_map.put("msg", "fail");
		}
		return login_user_map;
	}
	/*查询所有用户权限 Admin界面*/
	@RequestMapping(value="/select_users_permissions",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> selectUsersPermissions(HttpServletRequest request){
		List<User> selectUsersPermissions=userimp.select_users_permissions();
		List<User> selectUsers=userimp.select_users();
		Map<String, Object> map=new HashMap<>();
		HttpSession session=request.getSession();
		Integer level= (Integer) session.getAttribute("level");


		if(Md5Utils.MD5("super"+"mwfDCCSx@ZI"+ Md5Utils.MD5(level.toString())).equals("7ff491a585c47e0f0256280fa72930ea")) {
			//超级管理员
			map.put("selectUsers",selectUsers);
			map.put("selectUsersPermissions",selectUsersPermissions);
		}else if(Md5Utils.MD5("super"+"mwfDCCSx@ZI"+ Md5Utils.MD5(level.toString())).equals("d73110b578af2ba7967d866d754834a5")) {
			//管理员
			map.put("selectUsers",selectUsers);
			map.put("selectUsersPermissions",selectUsersPermissions);
		}else if(Md5Utils.MD5("super"+"mwfDCCSx@ZI"+ Md5Utils.MD5(level.toString())).equals("9e922299fe554a767569a39735b6b5bf")) {
			//用户
			map.put("msg","RestrictedPermission");
		}else{
			//其他
			map.put("msg","RestrictedPermission");
		}
		return map;
	}
	//进入登录页面
	@RequestMapping(value="/in_index",method = RequestMethod.GET)
	public String inIndex() {
		return "index";
	}
	//进入主页面
	@RequestMapping(value="/in_main",method = RequestMethod.GET)
	public String inMain() {
		return "main";
	}
	/**
	 * @获取当前登录的用户
	 */
	@RequestMapping(value="/login_session",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> loginSession(HttpServletRequest request) throws IOException, JDOMException {
		HttpSession session = request.getSession();
		Map<String, Object> login_user_session_map=new HashMap<>();
		List<Personnel> personnel_one= (List<Personnel>) session.getAttribute("personnel");
		String p_email=personnel_one.get(0).getPersonnel_email();
		String p_uuid= (String) session.getAttribute("user_uuid");

		Map<String, Object> builder_path= SysVersion.permission_xml();
		SAXBuilder builder = new SAXBuilder();
		Document document = builder.build(builder_path.get("builder_path")+ Md5Utils.MD5(p_email)+".xml");
		if(document!=null){
			Element beauties = document.getRootElement();
			List<Element> users = (List<Element>) beauties.getChildren("user");
			String email_this = users.get(0).getChildText("email");
			String permission_uuid_this = users.get(0).getChildText("permission_uuid");
			if(email_this.equals(p_email)||permission_uuid_this.equals(p_uuid)){
				login_user_session_map.put("personnel", session.getAttribute("personnel"));
			}else{
				login_user_session_map.put("msg","fail");
			}
		}else{
			login_user_session_map.put("msg","fail");
		}
		return login_user_session_map;
	}
	/**
	 * @获取当前登录的用户权限
	 */
	@RequestMapping(value="/login_permission_session",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> loginPermissionSession(HttpServletRequest request) throws IOException, JDOMException {
		HttpSession session = request.getSession();
		Map<String, Object> login_permission_session=new HashMap<>();
		List<Personnel> personnel_one= (List<Personnel>) session.getAttribute("personnel");
		String p_email=personnel_one.get(0).getPersonnel_email();
		String p_uuid= (String) session.getAttribute("user_uuid");

		Map<String, Object> builder_path= SysVersion.permission_xml();
		SAXBuilder builder = new SAXBuilder();
		Document document = builder.build(builder_path.get("builder_path")+ Md5Utils.MD5(p_email)+".xml");
		if(document!=null){
			Element beauties = document.getRootElement();
			List<Element> users = (List<Element>) beauties.getChildren("user");
			String email_this = users.get(0).getChildText("email");
			String permission_uuid_this = users.get(0).getChildText("permission_uuid");
			if(email_this.equals(p_email)||permission_uuid_this.equals(p_uuid)){
				login_permission_session.put("permission",userimp.single_select_user_permission_big(p_email));
			}else{
				login_permission_session.put("msg","fail");
			}
		}else{
			login_permission_session.put("msg","fail");
		}
		return login_permission_session;
	}
	/**
	 * @退出当前登录的用户
	 */
	@RequestMapping(value="/login_session_out",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> loginSessionOut(HttpServletRequest request){
		HttpSession session = request.getSession();
		Map<String, Object> login_user_session_map=new HashMap<>();
		session.setAttribute("user_uuid",null);
		session.setAttribute("user_index",null);
		session.setAttribute("login_user",null);
		session.setAttribute("user",null);
		session.setAttribute("personnel",null);
		login_user_session_map.put("msg", "success");
		return login_user_session_map;
	}
}
