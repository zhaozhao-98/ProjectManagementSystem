package com.dcc.ProjectManagementSystem.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.alibaba.fastjson.JSONObject;
import com.dcc.ProjectManagementSystem.entity.AttachedProject;
import com.dcc.ProjectManagementSystem.entity.Project;
import com.dcc.ProjectManagementSystem.entity.ProjectFields;
import com.dcc.ProjectManagementSystem.serviceImp.ProjectImp;
import org.jdom2.JDOMException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dcc.ProjectManagementSystem.serviceImp.AttachedProjectImp;

import javax.servlet.http.HttpServletRequest;

import static com.dcc.ProjectManagementSystem.utils.CreateOdServicesNum.serviceNum;
import static com.dcc.ProjectManagementSystem.utils.TimeUtil.od_num_time;

@Controller
@RequestMapping("/attached_project_controller")
public class AttachedProjectController {
	@Autowired
	private AttachedProjectImp attachedImp;
	@Autowired
	private ProjectImp projectimp;

	/*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*/
	//查询副项目
	@RequestMapping(value="/select_attached",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> selectAttached(HttpServletRequest request,int pid) throws IOException, JDOMException, NoSuchFieldException, ClassNotFoundException, IllegalAccessException {
		Map<String, Object> map=new HashMap<>();
		Project p=new Project();
		p.setPid(pid);
		if(BaseController.selectPermissionController(request)) {
			/*查询全局模式查看附属项目权限，这里当作是查询project中字段的0或1，0>只能查看自己的项目，1>能查看所有项目的附属项目*/
			List<AttachedProject> ap=attachedImp.select_attached(pid);
			Map<String, Object> list_projectfields=ProjectFieldsController.selectProjectfieldsPermission(request,1,p,"1",null);
			List<ProjectFields> projectfields= (List<ProjectFields>) list_projectfields.get("list_projectfields");
			System.out.println(projectfields);
			for(ProjectFields projectfield:projectfields){
				if(projectfield.getTable_fields().contains("project_soon")){
					if(projectfield.getFields_select()!=1){
						map.put("msg", "RestrictedPermission");
					}else{
						map.put("attached_list",attachedImp.select_attached(pid));
						map.put("msg", "success");
					}
				}
			}
		}else{
			map.put("msg", "RestrictedPermission");
		}
		return map;
	}
	//根据attachedid查询副项目
	@RequestMapping(value="/select_attached_forattaid",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> selectAttachedForAttaId(HttpServletRequest request,int attachedid) throws IOException, JDOMException {
		Map<String, Object> map=new HashMap<>();
		if(BaseController.selectPermissionController(request)) {
			map.put("attached_one", attachedImp.select_attached_forattaid(attachedid));
			map.put("msg", "success");
		}else{
			map.put("msg", "RestrictedPermission");
		}
		return map;
	}
	/*删除*删除*删除*删除*删除*删除*删除*删除*删除*删除*删除*删除*删除*删除*删除*删除*删除*删除*删除*删除*删除*删除*删除*删除*删除*/
	//删除附属项目
	@RequestMapping(value="/del_attached",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> delAttached(HttpServletRequest request,int attachedid,int pid) throws IOException, JDOMException, NoSuchFieldException, ClassNotFoundException, IllegalAccessException {
		Map<String, Object> del_attached_msg=new HashMap<>();
		Project p=new Project();
		p.setPid(pid);
		if(BaseController.deletePermissionController(request)){
			Map<String, Object> list_projectfields=ProjectFieldsController.selectProjectfieldsDeletePermission(request,2,p);
			System.out.println("#########################");
			System.out.println(list_projectfields.get("project_soon"));
			if(list_projectfields.get("project_soon")!=null){
				del_attached_msg.put("msg", "RestrictedPermission");
			}else{
				List<Project> project_one=projectimp.select_project_pid_one(pid);
				String attr_project=project_one.get(0).getProject_soon();
				List<Object>  new_project_soon=new ArrayList<>();
				if(!attr_project.equals("")) {
					String[] retval = attr_project.split("\\|");
					for (String s : retval) {
						if (Integer.parseInt(s) != attachedid) {
							//比对不存在，追加字符串，得到新的子项目集合
							new_project_soon.add(s);
						}
					}
					String new_attachedproject_soon="";
					for(int t=0;t<new_project_soon.size();t++) {
						if(t==0) {
							new_attachedproject_soon+=new_project_soon.get(t);
						}else {
							new_attachedproject_soon+="|"+new_project_soon.get(t);
						}
					}
					Project new_project_one=new Project();
					new_project_one.setProject_soon(new_attachedproject_soon);
					new_project_one.setPid(pid);
					projectimp.update_project_attproject(new_project_one);
					//修改后，删除附属项目
					attachedImp.del_attached(attachedid);
					del_attached_msg.put("msg", "success");
				}
			}
		}else{
			del_attached_msg.put("msg", "RestrictedPermission");
		}
		return del_attached_msg;
	}
	/*修改*修改*修改*修改*修改*修改*修改*修改*修改*修改*修改*修改*修改*修改*修改*修改*修改*修改*修改*修改*修改*修改*修改*修改*修改*修改*修改*/
	//更新附属项目
	@RequestMapping(value="/update_attached_project_one",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateAttachedProjectOne(HttpServletRequest req) throws ParseException, IOException, JDOMException, NoSuchFieldException, ClassNotFoundException, IllegalAccessException {
		Map<String, Object> map=new HashMap<>();
		if(BaseController.updatePermissionController(req)){
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String ajax_str =  req.getParameter("ajax_str");
			JSONObject jsonObject = JSONObject.parseObject(ajax_str);
			AttachedProject update_attachedProject_one =new AttachedProject();
			update_attachedProject_one.setAttached_id(jsonObject.getInteger("attachedid"));

			AttachedProject ap= attachedImp.select_attached_forattaid(jsonObject.getInteger("attachedid"));
			Map<String,Object> update_fiedlds=ProjectFieldsController.selectProjectfieldsUpdatePermission(req,2,null,"2",ap);
			//更改字段的总数
			int fields_count=jsonObject.getInteger("fields_count");
			int val=0;
			if(fields_count>0) {
				for(int i=0;i<fields_count;i++) {
					//获取key
					String key=jsonObject.getJSONArray("change_value_ajax").getJSONObject(i).getString("key");

					switch (key) {
						case "attached_address": {
							//获取key对应的值
							String value = jsonObject.getJSONArray("change_value_ajax").getJSONObject(i).getString("value");
							if (value != null && !value.equals("") && !value.equals("null")) {
								if(update_fiedlds.get("attached_addressval")!=null){
									/*不可查既不可更改*/
									update_attachedProject_one.setAttached_address(null);
								}else{
									if(update_fiedlds.get("attached_address")!=null){
										/*限制更改*/
										update_attachedProject_one.setAttached_address(null);
									}else{
										update_attachedProject_one.setAttached_address(value);
										val++;
									}
								}
							} else {
								update_attachedProject_one.setAttached_address(null);
							}
							break;
						}
						case "attached_project_name": {
							//获取key对应的值
							String value = jsonObject.getJSONArray("change_value_ajax").getJSONObject(i).getString("value");
							if (value != null && !value.equals("") && !value.equals("null")) {
								if(update_fiedlds.get("attached_project_nameval")!=null){
									/*不可查既不可更改*/
									update_attachedProject_one.setAttached_project_name(null);
								}else{
									if(update_fiedlds.get("attached_project_name")!=null){
										/*限制更改*/
										update_attachedProject_one.setAttached_project_name(null);
									}else{
										update_attachedProject_one.setAttached_project_name(value);
										val++;
									}
								}
							} else {
								update_attachedProject_one.setAttached_project_name(null);
							}
							break;
						}
						case "attached_status": {
							//获取key对应的值
							Integer value = jsonObject.getJSONArray("change_value_ajax").getJSONObject(i).getInteger("value");
							if (value != null && value != 0) {
								if(update_fiedlds.get("attached_statusval")!=null){
									/*不可查既不可更改*/
									update_attachedProject_one.setAttached_status(null);
								}else{
									if(update_fiedlds.get("attached_status")!=null){
										/*限制更改*/
										update_attachedProject_one.setAttached_status(null);
									}else{
										update_attachedProject_one.setAttached_status(value);
										val++;
									}
								}
							} else {
								update_attachedProject_one.setAttached_status(0);
							}
							break;
						}
						case "attached_bill_time": {
							//获取key对应的值
							String value = jsonObject.getJSONArray("change_value_ajax").getJSONObject(i).getString("value");
							if (value != null && !value.equals("")) {
								if(update_fiedlds.get("attached_bill_timeval")!=null){
									/*不可查既不可更改*/
									update_attachedProject_one.setAttached_bill_time(null);
								}else {
									if (update_fiedlds.get("attached_bill_time") != null) {
										/*限制更改*/
										update_attachedProject_one.setAttached_bill_time((long) 0);
									} else {
										Long ps;
										ps = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(value+" 00:00:00").getTime();
										update_attachedProject_one.setAttached_bill_time(ps);
										val++;
									}
								}
							} else {
								update_attachedProject_one.setAttached_bill_time((long) 0);
							}
							break;
						}
						case "attached_end_time": {
							//获取key对应的值
							String value = jsonObject.getJSONArray("change_value_ajax").getJSONObject(i).getString("value");
							if (value != null && !value.equals("")) {
								if(update_fiedlds.get("attached_end_timeval")!=null){
									/*不可查既不可更改*/
									update_attachedProject_one.setAttached_end_time(null);
								}else {
									if (update_fiedlds.get("attached_end_time") != null) {
										/*限制更改*/
										update_attachedProject_one.setAttached_end_time((long) 0);
									} else {
										Long ps;
										ps = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(value+" 00:00:00").getTime();
										update_attachedProject_one.setAttached_end_time(ps);
										val++;
									}
								}
							} else {
								update_attachedProject_one.setAttached_end_time((long) 0);
							}
							break;
						}
						case "attached_start_time": {
							//获取key对应的值
							String value = jsonObject.getJSONArray("change_value_ajax").getJSONObject(i).getString("value");
							if (value != null && !value.equals("")) {
								if(update_fiedlds.get("attached_start_timeval")!=null){
									/*不可查既不可更改*/
									update_attachedProject_one.setAttached_start_time(null);
								}else {
									if (update_fiedlds.get("attached_start_time") != null) {
										/*限制更改*/
										update_attachedProject_one.setAttached_start_time((long) 0);
									} else {
										Long ps;
										ps = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(value+" 00:00:00").getTime();
										update_attachedProject_one.setAttached_start_time(ps);
										val++;
									}
								}
							} else {
								update_attachedProject_one.setAttached_start_time((long) 0);
							}
							break;
						}
						case "attached_expecte_dcompletion_date": {
							//获取key对应的值
							String value = jsonObject.getJSONArray("change_value_ajax").getJSONObject(i).getString("value");
							if (value != null && !value.equals("")) {
								if(update_fiedlds.get("attached_expecte_dcompletion_dateval")!=null){
									/*不可查既不可更改*/
									update_attachedProject_one.setAttached_expected_completion_date(null);
								}else {
									if (update_fiedlds.get("attached_expecte_dcompletion_date") != null) {
										/*限制更改*/
										update_attachedProject_one.setAttached_expected_completion_date((long) 0);
									} else {
										Long ps;
										ps = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(value+" 00:00:00").getTime();
										update_attachedProject_one.setAttached_expected_completion_date(ps);
										val++;
									}
								}
							} else {
								update_attachedProject_one.setAttached_expected_completion_date((long) 0);
							}
							break;
						}
						case "attached_actual_finishing_date": {
							//获取key对应的值
							String value = jsonObject.getJSONArray("change_value_ajax").getJSONObject(i).getString("value");
							if (value != null && !value.equals("")) {
								if(update_fiedlds.get("attached_actual_finishing_dateval")!=null){
									/*不可查既不可更改*/
									update_attachedProject_one.setAttached_actual_finishing_date(null);
								}else {
									if (update_fiedlds.get("attached_actual_finishing_date") != null) {
										/*限制更改*/
										update_attachedProject_one.setAttached_actual_finishing_date((long) 0);
									} else {
										Long ps;
										ps = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(value+" 00:00:00").getTime();
										update_attachedProject_one.setAttached_actual_finishing_date(ps);
										val++;
									}
								}
							} else {
								update_attachedProject_one.setAttached_actual_finishing_date((long) 0);
							}
							break;
						}
						case "attached_time_fee": {
							//获取key对应的值
							Integer value = jsonObject.getJSONArray("change_value_ajax").getJSONObject(i).getInteger("value");
							update_attachedProject_one.setAttached_time_fee(value);
							break;
						}
						case "attached_moon_fee": {
							//获取key对应的值
							Integer value = jsonObject.getJSONArray("change_value_ajax").getJSONObject(i).getInteger("value");
							if (value != null && value != 0) {
								if(update_fiedlds.get("attached_moon_feeval")!=null){
									/*不可查既不可更改*/
									update_attachedProject_one.setAttached_moon_fee(null);
								}else {
									if (update_fiedlds.get("attached_moon_fee") != null) {
										/*限制更改*/
										update_attachedProject_one.setAttached_moon_fee(0);
									} else {
										update_attachedProject_one.setAttached_moon_fee(value);
										val++;
									}
								}
							} else {
								update_attachedProject_one.setAttached_moon_fee(0);
							}
							break;
						}
						case "attached_currency": {
							//获取key对应的值
							Integer value = jsonObject.getJSONArray("change_value_ajax").getJSONObject(i).getInteger("value");
							if (value != null && value != 0) {
								if(update_fiedlds.get("attached_currencyval")!=null){
									/*不可查既不可更改*/
									update_attachedProject_one.setAttached_currency(null);
								}else {
									if (update_fiedlds.get("attached_currency") != null) {
										/*限制更改*/
										update_attachedProject_one.setAttached_currency(0);
									} else {
										update_attachedProject_one.setAttached_currency(value);
										val++;
									}
								}
							} else {
								update_attachedProject_one.setAttached_currency(0);
							}
							break;
						}
						case "attached_remark": {
							//获取key对应的值
							String value = jsonObject.getJSONArray("change_value_ajax").getJSONObject(i).getString("value");
							if (value != null && !value.equals("") && !value.equals("null")) {
								if(update_fiedlds.get("attached_remarkval")!=null){
									/*不可查既不可更改*/
									update_attachedProject_one.setAttached_remark(null);
								}else {
									if (update_fiedlds.get("attached_remark") != null) {
										/*限制更改*/
										update_attachedProject_one.setAttached_remark(null);
									} else {
										update_attachedProject_one.setAttached_remark(value);
										val++;
									}
								}
							} else {
								update_attachedProject_one.setAttached_remark(null);
							}
							break;
						}
						case "attached_b_address": {
							//获取key对应的值
							String value = jsonObject.getJSONArray("change_value_ajax").getJSONObject(i).getString("value");
							if (value != null && !value.equals("") && !value.equals("null")) {
								if(update_fiedlds.get("attached_b_addressval")!=null){
									/*不可查既不可更改*/
									update_attachedProject_one.setAttached_b_address(null);
								}else {
									if (update_fiedlds.get("attached_b_address") != null) {
										/*限制更改*/
										update_attachedProject_one.setAttached_b_address(null);
									} else {
										update_attachedProject_one.setAttached_b_address(value);
										val++;
									}
								}
							} else {
								update_attachedProject_one.setAttached_b_address(null);
							}
							break;
						}
						case "attached_services_num": {
							//获取key对应的值
							String value = jsonObject.getJSONArray("change_value_ajax").getJSONObject(i).getString("value");
							if (value != null && !value.equals("") && !value.equals("null")) {
								if(update_fiedlds.get("attached_services_numval")!=null){
									/*不可查既不可更改*/
									update_attachedProject_one.setAttached_services_num(null);
								}else{
									if(update_fiedlds.get("attached_services_num")!=null){
										/*限制更改*/
										update_attachedProject_one.setAttached_services_num(null);
									}else{
										Long project_count=projectimp.selectRowsByid(0,0,value);
										Long attached_count=attachedImp.select_attached_by_service_num(value);
										Long allcount=project_count+attached_count;
										if(allcount>0){
											map.put("msg","service_num_repetition");
											return map;
										}else{
											update_attachedProject_one.setAttached_services_num(value);
											val++;
										}
									}
								}
							} else {
								update_attachedProject_one.setAttached_services_num(null);
							}
							break;
						}
					}
				}
				if(val>0){
					attachedImp.update_attachedProject_one(update_attachedProject_one);
					map.put("msg", "success");
				}else{
					map.put("msg", "ThereIsNoUpdateOrRestrictedPermission");
				}
			}else{map.put("msg", "RestrictedPermission");}
		}else {
			map.put("msg", "fail");
		}
		return map ;
	}
	/*添加*添加*添加*添加*添加*添加*添加*添加*添加*添加*添加*添加*添加*添加*添加*添加*添加*添加*添加*添加*添加*添加*添加*添加*添加*添加*添加*/
	//添加附属项目
	@RequestMapping(value="/insert_attached",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> insertAttached(@RequestBody AttachedProject attached_json, HttpServletRequest request) throws JDOMException, IOException, ParseException {
		Map<String, Object> map=new HashMap<>();
		if(BaseController.insertPermissionController(request)) {
			/*附属项目服务编号*/
			Map<String,Object> od_num_map=od_num_time();
			int  year= (int) od_num_map.get("year");
			int  month= (int) od_num_map.get("month");
			int  day= (int) od_num_map.get("day");
			final long  start_time_lon= (long) od_num_map.get("start_time_lon");
			final long  end_time_lon= (long) od_num_map.get("end_time_lon");
			/*副项目服务编号*/
			Long count=projectimp.selectCount_createdate(start_time_lon,end_time_lon,0);
			Long attached_count=attachedImp.count_service_num(start_time_lon,end_time_lon);

			List<AttachedProject> attachedproject_service_num_list=attachedImp.selectAttachedServiceNum();
			String service_num=serviceNum(count,attached_count,year,month,day);
			for(int i=0;i<attachedproject_service_num_list.size();i++){
				/*重复时count++,重新生成*/
				if(service_num.equals(attachedproject_service_num_list.get(i).getAttached_services_num())){
					count++;
					service_num=serviceNum(count,attached_count,year,month,day);
				}
			}
			List<Project> project_service_num_list=projectimp.selectServiceNum();
			for(int i=0;i<project_service_num_list.size();i++){
				/*重复时count++,重新生成*/
				if(service_num.equals(project_service_num_list.get(i).getServices_num())){
					count++;
					service_num=serviceNum(count,attached_count,year,month,day);
				}
			}
			attached_json.setAttached_services_num(service_num);
			//主要添加的方法
			attachedImp.insert_attached(attached_json);
			//更新主项目，防止其他地方用的上
			List<Project> project_one=projectimp.select_project_pid_one(attached_json.getProject_pid());
			String attr_project=project_one.get(0).getProject_soon();
			String  new_project_soon="";
			List<AttachedProject> list_atta=attachedImp.select_attached(attached_json.getProject_pid());
			List v  = new ArrayList<>();
			for (AttachedProject attachedProject : list_atta) {
				v.add(attachedProject.getAttached_id());
			}
			if(attr_project!=null&&!attr_project.equals("null")&&!attr_project.equals("")) {
				int max = (int) Collections.max(v);
				new_project_soon=attr_project+"|"+max;
			}else {
				Integer max=(Integer) Collections.max(v);
				new_project_soon=max.toString();
			}
			Project new_project_one=new Project();
			new_project_one.setProject_soon(new_project_soon);
			new_project_one.setPid(attached_json.getProject_pid());
			projectimp.update_project_attproject(new_project_one);
			map.put("msg","success");
		}else {
			map.put("msg","RestrictedPermission");
		}
		return map;
	}
}
