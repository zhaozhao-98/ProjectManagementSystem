package com.dcc.ProjectManagementSystem.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSONObject;
import com.dcc.ProjectManagementSystem.entity.*;
import com.dcc.ProjectManagementSystem.serviceImp.*;
import com.dcc.ProjectManagementSystem.utils.*;
import org.jdom2.JDOMException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.web.multipart.MultipartFile;

import static com.dcc.ProjectManagementSystem.utils.CreateOdServicesNum.*;
import static com.dcc.ProjectManagementSystem.utils.TimeUtil.od_num_time;

@Controller
@RequestMapping("/project_controller")
public class ProjectController {
	@Autowired
	private ProjectImp projectimp;
	@Autowired
	private PersonnelImp personnelimp;
	@Autowired
	private CustomerImp customerimp;
	@Autowired
	private FilesImp filesimp;
	@Autowired
	private AttachedProjectImp attachedImp;
	/*添加*添加*添加*添加*添加*添加*添加*添加*添加*添加*添加*添加*添加*添加*添加*添加*添加*添加*添加*添加*添加*添加*添加*添加*添加*添加*/
	//新建项目
	@RequestMapping(value="/insert_project",method = RequestMethod.POST)
	@ResponseBody
    public Map<String, Object> insertProject(@RequestBody Project project_one, HttpServletRequest request) throws ParseException, JDOMException, IOException {
		Map<String, Object> map= new HashMap<>();
		if(BaseController.insertPermissionController(request)) {
			Map<String,Object> od_num_map=od_num_time();
			int  year= (int) od_num_map.get("year");
			int  month= (int) od_num_map.get("month");
			int  day= (int) od_num_map.get("day");
			final long  firstDayOfMonth= (long) od_num_map.get("firstDayOfMonth");
			final long  lastDayOfMonth= (long) od_num_map.get("lastDayOfMonth");
			final long  start_time_lon= (long) od_num_map.get("start_time_lon");
			final long  end_time_lon= (long) od_num_map.get("end_time_lon");
			List<Project> nums=projectimp.selectNums();
			//od编号
			if(project_one.getOd_num()==null||project_one.getOd_num()=="") {
				if(project_one.getProject_class()==10) {
					Long count=projectimp.selectCount_createdate(firstDayOfMonth,lastDayOfMonth,10);
					project_one.setOd_num(odTestNum(count,year,month,day,nums));
				}else {
					//odnum处理 以年计数递增，并且非测试单
					Long count=projectimp.selectCount_createdate(firstDayOfMonth,lastDayOfMonth,1);
					project_one.setOd_num(odRoutineNum(count,year,month,day,nums));
				}
			}
			//服务编号
			if(project_one.getServices_num()==null||project_one.getServices_num()==""){
				Long count=projectimp.selectCount_createdate(start_time_lon,end_time_lon,0);
				Long attached_count=attachedImp.count_service_num(start_time_lon,end_time_lon);
				//服务编号处理 重复时
				List<Project> project_service_num_list=projectimp.selectServiceNum();
				List<AttachedProject> attachedproject_service_num_list=attachedImp.selectAttachedServiceNum();
				String service_num=serviceNum(count,attached_count,year,month,day);
				for(int i=0;i<attachedproject_service_num_list.size();i++){
					/*重复时count++,重新生成*/
					if(service_num.equals(attachedproject_service_num_list.get(i).getAttached_services_num())){
						count++;
						service_num=serviceNum(count,attached_count,year,month,day);
					}
				}

				for(int i=0;i<project_service_num_list.size();i++){
					/*重复时count++,重新生成*/
					if(service_num.equals(project_service_num_list.get(i).getServices_num())){
						count++;
						service_num=serviceNum(count,attached_count,year,month,day);
					}
				}
				project_one.setServices_num(service_num);
			}
			if(project_one.getProject_contract_pdf()==null) {
				project_one.setProject_contract_pdf("null");
			}
			if(project_one.getProject_sailing_notice()==null) {
				project_one.setProject_sailing_notice("null");
			}
			if(project_one.getProject_gplot()==null) {
				project_one.setProject_gplot("null");
			}
			
			if(project_one.getContract_num().equals("null")|| project_one.getContract_num().equals("")) {
				project_one.setContract_num("null");
			}
			if(project_one.getProject_name().equals("null")|| project_one.getProject_name().equals("")) {
				project_one.setProject_name("null");
			}
			if(project_one.getProject_list()==0 || project_one.getProject_list()==null) {
				project_one.setProject_list(0);
			}
			if(project_one.getCustomer()==0 || project_one.getCustomer()==null) {
				project_one.setCustomer(0);
			}
			if(project_one.getProject_suppliers()==0 || project_one.getProject_suppliers()==null) {
				project_one.setProject_suppliers(0);
			}
			if(project_one.getProject_market()==0 || project_one.getProject_market()==null) {
				project_one.setProject_market(0);
			}
			if(project_one.getProject_pm()==0 || project_one.getProject_pm()==null) {
				project_one.setProject_pm(0);
			}
			if(project_one.getProject_startdate()==0 || project_one.getProject_startdate()==null) {
				project_one.setProject_startdate((long) 0);
			}
			if(project_one.getProject_enddate()==0 || project_one.getProject_enddate()==null) {
				project_one.setProject_enddate((long) 0);
			}
			if(project_one.getProject_billdate()==0 || project_one.getProject_billdate()==null) {
				project_one.setProject_billdate((long) 0);
			}
			if(project_one.getProject_suppliers_chargeable_time()==0 || project_one.getProject_suppliers_chargeable_time()==null) {
				project_one.setProject_suppliers_chargeable_time((long) 0);
			}
			if(project_one.getProject_completion_date()==0 || project_one.getProject_completion_date()==null) {
				project_one.setProject_completion_date((long) 0);
			}
			if(project_one.getProject_expected_completion_date()==0 || project_one.getProject_expected_completion_date()==null) {
				project_one.setProject_expected_completion_date((long) 0);
			}
			if(project_one.getProject_actual_finishing_date()==0 || project_one.getProject_actual_finishing_date()==null) {
				project_one.setProject_actual_finishing_date((long) 0);
			}
			if(project_one.getService_hours().equals("0") || project_one.getService_hours()==null || project_one.getService_hours().equals("")) {
				project_one.setService_hours("0");
			}
			if(project_one.getServices_deadline()==0 || project_one.getServices_deadline()==null) {
				project_one.setServices_deadline(0);
			}
			if(project_one.getProject_speed().equals("") || project_one.getProject_speed()==null || project_one.getProject_speed().equals("0") || project_one.getProject_speed().equals("null")) {
				project_one.setProject_speed("0");
			}
			if(project_one.getSpeed_unit()==0 || project_one.getSpeed_unit()==null ) {
				project_one.setSpeed_unit(0);
			}
			if(project_one.getProject_status()==0 || project_one.getProject_status()==null ) {
				project_one.setProject_status(0);
			}
			if(project_one.getProject_install_address().equals("")|| project_one.getProject_install_address()==null || project_one.getProject_install_address().equals("null")) {
				project_one.setProject_install_address("null");
			}
			if(project_one.getProject_install_address_b().equals("")|| project_one.getProject_install_address_b()==null || project_one.getProject_install_address_b().equals("null")) {
				project_one.setProject_install_address_b("null");
			}
			if(project_one.getCurrency()==0 || project_one.getCurrency()==null ) {
				project_one.setCurrency(0);
			}
			if(project_one.getProject_time_fee()==0 || project_one.getProject_time_fee()==null ) {
				project_one.setProject_time_fee(0);
			}
			if(project_one.getProject_moon_fee()==0 || project_one.getProject_moon_fee()==null ) {
				project_one.setProject_moon_fee(0);
			}
			if(project_one.getProject_contract_party()==0 || project_one.getProject_contract_party()==null ) {
				project_one.setProject_contract_party(0);
			}
			if(project_one.getProject_remark().equals("")|| project_one.getProject_remark()==null || project_one.getProject_remark().equals("null")) {
				project_one.setProject_remark("null");
			}
			project_one.setProject_soon("0");
			project_one.setProject_progress_plan("null");
			//收藏
			String reandom_num_letter=test.randomGen(6);
			project_one.setStat_tag(reandom_num_letter);
			projectimp.insert_project(project_one);
			if(project_one.getStat_val()!=0) {
				Personnel personnel_stat=new Personnel();
				personnel_stat.setPid(project_one.getUser_uid());
				List<Personnel> select_personnel_one= personnelimp.select_personnel_one(project_one.getUser_uid());
				if(select_personnel_one.get(0)!=null) {
					if(select_personnel_one.get(0).getUser_stat()!=null&&!select_personnel_one.get(0).getUser_stat().equals("")) {
						personnel_stat.setUser_stat(select_personnel_one.get(0).getUser_stat()+"|"+reandom_num_letter);
					}else {
			       		personnel_stat.setUser_stat(reandom_num_letter);
			       	 }
		       	 }else {
		       		personnel_stat.setUser_stat(reandom_num_letter);
		       	 }
				personnelimp.update_stat(personnel_stat);
			}else {
				map.put("msg_stat","unfavorite");
			}
			map.put("msg","success");
		}else {
			map.put("msg","RestrictedPermission");
		}
		return map;
    }
	/*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*/
	/**
	 * @主界面项目展示
	 */
	@RequestMapping(value="/list_select_project_main",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> listSelectProjectMain(HttpServletRequest request,int limit,int item) throws IOException, JDOMException {
		Map<String, Object> map=new HashMap<>();
		if(BaseController.selectPermissionController(request)) {
			List<Project> list_project= projectimp.list_select_project_main(limit,item);
			Long rows=projectimp.selectRows();

			Long test_orders=projectimp.selectRowsByid(2,0,null);
			Long cooperative_orders=projectimp.selectRowsByid(4,0,null);
			Long official_orders=projectimp.selectRowsByid(5,0,null);
			Long remotehand_orders=projectimp.selectRowsByid(3,0,null);
			map.put("list_project", list_project);
			map.put("test_orders", test_orders);
			map.put("cooperative_orders", cooperative_orders);
			map.put("official_orders", official_orders);
			map.put("remotehand_orders", remotehand_orders);
			map.put("rows", rows);
			map.put("limit", limit);
			map.put("item", item);
			map.put("msg", "success");
		}else {
			map.put("msg", "RestrictedPermission");
		}
		return map ;
	}
	@RequestMapping(value="/select_timeout_project",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> selectTimeoutProject(HttpServletRequest request,int project_pm,int limit,int item) throws IOException, JDOMException, ParseException {
		Map<String, Object> map=new HashMap<>();
		if(BaseController.selectPermissionController(request)) {
			Map<String,Object> today_time_map=od_num_time();
			int year= (int) today_time_map.get("year");
			int month= (int) today_time_map.get("month");
			int day= (int) today_time_map.get("day");
			final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			final long start_datetime = sdf.parse(year+"-"+month+"-"+day+" 00:00:00").getTime();
			List<Project> list_timeout_project= projectimp.select_timeout_project(project_pm,start_datetime,limit,item);
			map.put("list_timeout_project", list_timeout_project);
			map.put("msg", "success");
		}else {
			map.put("msg", "RestrictedPermission");
		}
		return map ;
	}
	/**
	 * @所有、测试、正式、RemoteHand、合作项目展示
	 */
	@RequestMapping(value="/list_select_project_mod_all",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> listSelectProjectModAll(HttpServletRequest request, Project project, int limit, int item) throws IOException, JDOMException {
		Map<String, Object> map=new HashMap<>();
		if(BaseController.selectPermissionController(request)) {
			List<Project> list_project_all= (List<Project>)projectimp.list_select_project_mod_all(project.getProject_list(),project.getProject_pm(),null,limit,item);
			Long rows=projectimp.selectRows();
			if(project.getProject_list()!=null && project.getProject_pm()!=0 &&  project.getProject_pm()!=null) {
				Long project_list_rows=projectimp.selectRowsByid(project.getProject_list(),project.getProject_pm(),null);
				map.put("project_list_rows", project_list_rows);
			}else if(project.getProject_list()!=null && project.getProject_list()!=0){
				Long project_list_rows=projectimp.selectRowsByid(project.getProject_list(),0,null);
				map.put("project_list_rows", project_list_rows);
			}else {
				map.put("project_list_rows", rows);
			}
			map.put("list_project_all", list_project_all);
			map.put("rows", rows);
			map.put("msg", "success");
		}else{
			map.put("msg", "RestrictedPermission");
		}
		return map ;
	}
	/**
	 * @模糊查询
	 */
	@RequestMapping(value="/list_project_fuzzy_query_mod_all",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> listProjectFuzzyQueryModAll(HttpServletRequest request,String keyword) throws IOException, JDOMException {
		Map<String, Object> map=new HashMap<>();
		if(BaseController.selectPermissionController(request)) {
			List<Project> list_project_fuzzy_query_mod_all= projectimp.list_project_fuzzy_query_mod_all(keyword);
			map.put("list_project_fuzzy_query_mod_all", list_project_fuzzy_query_mod_all);
			map.put("msg", "success");
		}else{
			map.put("msg", "RestrictedPermission");
		}
		return map ;
	}
	/**
	 * @主界面搜索框
	 */
	@RequestMapping(value="/search_input_main",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> searchInputMain(HttpServletRequest request) throws IOException, JDOMException {
		Map<String, Object> map=new HashMap<>();
		if(BaseController.selectPermissionController(request)) {
			List<Project> search_project= projectimp.search_project();
			List<Personnel> list_personnel= personnelimp.select_personnel_list();
			List<Customer> list_customer= customerimp.select_customer_list();
			map.put("search_project", search_project);
			map.put("list_personnel", list_personnel);
			map.put("list_customer", list_customer);
			map.put("msg", "success");
		}else{
			map.put("msg", "RestrictedPermission");
		}
		return map ;
	}

	/**
	 * @项目编号session,用于查询项目
	 */
	@RequestMapping(value="/select_session_pid_one",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> selectSessionPidOne(HttpServletRequest request,int pid) throws IOException, JDOMException {
		HttpSession session = request.getSession();
		session.setAttribute("pid", 0);
		Map<String, Object> select_pid_one=new HashMap<>();
		if(BaseController.selectPermissionController(request)) {
			session.setAttribute("pid", pid);
			if(session.getAttribute("pid")!=null) {
				select_pid_one.put("msg","success");
			}else {
				select_pid_one.put("msg","fail");
			}
		}else{
			select_pid_one.put("msg", "RestrictedPermission");
		}
		return select_pid_one;
	}
	/*查询this单*/
	@RequestMapping(value="/select_project_pid_one",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> selectProjectPidOne(HttpServletRequest request,int pid) throws IOException, JDOMException, NoSuchFieldException, ClassNotFoundException, IllegalAccessException {
		Map<String, Object> project_one_map=new HashMap<>();
		if(BaseController.selectPermissionController(request)) {
			List<Project> p= projectimp.select_project_pid_one(pid);
			Map<String,Object> new_p=ProjectFieldsController.selectProjectfieldsPermission(request,1,p.get(0),"1",null);
			project_one_map.put("project_one", new_p.get("project_one"));
			project_one_map.put("list_projectfields", new_p.get("list_projectfields"));
			project_one_map.put("msg", "success");
		}else{
			project_one_map.put("msg", "RestrictedPermission");
		}
		return project_one_map;
	}
	@RequestMapping(value="/select_About_me_conduct_project",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> selectAboutMeConductProject(HttpServletRequest request,int project_pm) throws IOException, JDOMException {
		Map<String, Object> project_abouts_me=new HashMap<>();
		if(BaseController.selectPermissionController(request)) {
			project_abouts_me.put("project_abouts_me", projectimp.select_About_me_conduct_project(project_pm));
			project_abouts_me.put("msg", "success");
		}else{
			project_abouts_me.put("msg", "RestrictedPermission");
		}
		return project_abouts_me;
	}

	/*更新*更新*更新*更新*更新*更新*更新*更新*更新*更新*更新*更新*更新*更新*更新*更新*更新*更新*更新*更新*更新*更新*更新*更新*更新*更新*更新*/
	@RequestMapping(value="/upload_file_pdf",method = RequestMethod.POST)
	@ResponseBody
	//上传合同,开通单等
	public Map<String, Object> uploadFilePdf(MultipartFile contract, MultipartFile notice, MultipartFile gplot, MultipartFile other, int pid, HttpServletRequest request) throws ParseException, IOException, JDOMException, NoSuchFieldException, ClassNotFoundException, IllegalAccessException {
		Map<String, Object> map= new HashMap<>();
		if(BaseController.updatePermissionController(request)){
			List<Project> p= projectimp.select_project_pid_one(pid);
			Map<String, Object> list_projectfields=ProjectFieldsController.selectProjectfieldsPermission(request,1,p.get(0),"1",null);
			List<ProjectFields> projectfields= (List<ProjectFields>) list_projectfields.get("list_projectfields");

			int project_contract_pdf_permisstion = 0;
			int project_sailing_notice_permisstion = 0;
			int project_gplot_permisstion = 0;
			int project_other_permisstion = 0;
			int project_contract_pdf_permisstion_update = 0;
			int project_sailing_notice_permisstion_update = 0;
			int project_gplot_permisstion_update = 0;
			int project_other_permisstion_update = 0;
			for(ProjectFields projectfield:projectfields){
				if(projectfield.getTable_fields().contains("project_contract_pdf")){
					if(projectfield.getFields_select()!=1){
						project_contract_pdf_permisstion=0;
					}else{
						project_contract_pdf_permisstion=1;
					}
					if(projectfield.getFields_update()!=1){
						project_contract_pdf_permisstion_update=0;
					}else{
						project_contract_pdf_permisstion_update=1;
					}
				}
				if(projectfield.getTable_fields().contains("project_sailing_notice")){
					if(projectfield.getFields_select()!=1){
						project_sailing_notice_permisstion=0;
					}else{
						project_sailing_notice_permisstion=1;
					}
					if(projectfield.getFields_update()!=1){
						project_sailing_notice_permisstion_update=0;
					}else{
						project_sailing_notice_permisstion_update=1;
					}
				}
				if(projectfield.getTable_fields().contains("project_gplot")){
					if(projectfield.getFields_select()!=1){
						project_gplot_permisstion=0;
					}else{
						project_gplot_permisstion=1;
					}
					if(projectfield.getFields_update()!=1){
						project_gplot_permisstion_update=0;
					}else{
						project_gplot_permisstion_update=1;
					}
				}
				if(projectfield.getTable_fields().contains("project_other")){
					if(projectfield.getFields_select()!=1){
						project_other_permisstion=0;
					}else{
						project_other_permisstion=1;
					}
					if(projectfield.getFields_update()!=1){
						project_other_permisstion_update=0;
					}else{
						project_other_permisstion_update=1;
					}
				}
			}


			SysVersion sysversion = new SysVersion();
			Map<String, Object> upload_file_map=sysversion.upload_file();

			if(pid==0) {
				map.put("msg","fail");
			}else {
				String filePath =upload_file_map.get("filePath").toString();
				Project project=new Project();
				project.setPid(pid);
				List<Project> project_one=projectimp.select_project_pid_one(pid);
				if(project_contract_pdf_permisstion!=1){
					map.put("msg_contract_select","RestrictedPermission");
				}else{
					if(project_contract_pdf_permisstion_update!=1){
						map.put("msg_contract","RestrictedPermission");
					}else{
						if(contract!=null) {
							String fileName = contract.getOriginalFilename();
							Upload.upLoadFile(contract, filePath, upload_file_map.get("filepath1").toString()+fileName);
							if(project_one.get(0)!=null) {
								if(!project_one.get(0).getProject_contract_pdf().equals("")) {
									//添加新文件
									Files file=new Files();
									file.setFile_address(fileName);
									file.setFile_pid(pid);
									file.setFile_pm_id(project_one.get(0).getProject_pm());
									file.setFile_tag(1);
									file.setFile_name(UUID.randomUUID().toString());
									file.setFile_valid(1);
									SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
									String s = sdf.format(new Date());
									Long ps = sdf.parse(s).getTime();
									file.setCreate_date(ps);
									filesimp.insert_file(file);
									//取消以前的文件有效性
									file.setFile_address(project_one.get(0).getProject_contract_pdf());
									file.setFile_valid(0);
									file.setCreate_date(ps);
									filesimp.update_file(file);
								}
							}
							project.setProject_contract_pdf(fileName);
							projectimp.update_Attachment(project);
							map.put("msg_contract","success");
						}
					}
				}
				if(project_sailing_notice_permisstion!=1){
					map.put("msg_sailing_select","RestrictedPermission");
				}else{
					if(project_sailing_notice_permisstion_update!=1){
						map.put("msg_sailing","RestrictedPermission");
					}else{
						if(notice!=null) {
							String fileName2 = notice.getOriginalFilename();
							Upload.upLoadFile(notice, filePath, upload_file_map.get("filepath2").toString()+fileName2);
							if(project_one.get(0)!=null) {
								if(!project_one.get(0).getProject_sailing_notice().equals("")) {
									Files file=new Files();
									file.setFile_address(fileName2);
									file.setFile_pid(pid);
									file.setFile_pm_id(project_one.get(0).getProject_pm());
									file.setFile_tag(2);
									file.setFile_name(UUID.randomUUID().toString());
									file.setFile_valid(1);
									SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
									String s = sdf.format(new Date());
									Long ps = sdf.parse(s).getTime();
									file.setCreate_date(ps);
									filesimp.insert_file(file);
									//取消以前的文件有效性
									file.setFile_address(project_one.get(0).getProject_sailing_notice());
									file.setFile_valid(0);
									filesimp.update_file(file);
								}
							}
							project.setProject_sailing_notice(fileName2);
							projectimp.update_Attachment(project);
							map.put("msg_sailing","success");
						}
					}
				}
				if(project_gplot_permisstion!=1){
					map.put("msg_gplot_select","RestrictedPermission");
				}else{
					if(project_gplot_permisstion_update!=1){
						map.put("msg_gplot","RestrictedPermission");
					}else {
						if(gplot!=null) {
							String fileName3 = gplot.getOriginalFilename();
							Upload.upLoadFile(gplot, filePath, upload_file_map.get("filepath3").toString()+fileName3);
							if(project_one.get(0)!=null) {
								if(!project_one.get(0).getProject_gplot().equals("")) {
									Files file=new Files();
									file.setFile_address(fileName3);
									file.setFile_pid(pid);
									file.setFile_pm_id(project_one.get(0).getProject_pm());
									file.setFile_tag(3);
									file.setFile_name(UUID.randomUUID().toString());
									file.setFile_valid(1);
									SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
									String s = sdf.format(new Date());
									Long ps = sdf.parse(s).getTime();
									file.setCreate_date(ps);
									filesimp.insert_file(file);
									//取消以前的文件有效性
									file.setFile_address(project_one.get(0).getProject_gplot());
									file.setFile_valid(0);
									filesimp.update_file(file);
								}
							}
							project.setProject_gplot(fileName3);
							projectimp.update_Attachment(project);
							map.put("msg_gplot","success");
						}
					}
				}


				if(project_other_permisstion!=1){
					map.put("msg_other_select","RestrictedPermission");
				}else{
					if(project_other_permisstion_update!=1){
						map.put("msg_other","RestrictedPermission");
					}else{
						if(other!=null) {
							String fileName4 = other.getOriginalFilename();
							Upload.upLoadFile(other, filePath, upload_file_map.get("filepath4").toString()+fileName4);
							System.out.println((projectimp.select_project_pid_one(pid)).get(0).getProject_other());
							if((projectimp.select_project_pid_one(pid)).get(0).getProject_other()!=null &&  !(projectimp.select_project_pid_one(pid)).get(0).getProject_other().equals("")) {
								project.setProject_other((projectimp.select_project_pid_one(pid)).get(0).getProject_other()+"|"+fileName4);
							}else {
								project.setProject_other(fileName4);
							}
							projectimp.update_Attachment(project);
							map.put("msg_other","success");
						}
					}
				}
				map.put("Attachment_name", project);
			}
		}else{
			map.put("msg","RestrictedPermission");
		}
		return map;
	}
	//更新订单
	@RequestMapping(value="/update_project_one",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateProjectOne(HttpServletRequest req) throws ParseException, IOException, JDOMException, NoSuchFieldException, ClassNotFoundException, IllegalAccessException {
		Map<String, Object> map=new HashMap<>();

		if(BaseController.updatePermissionController(req)){
			String ajax_str =  req.getParameter("ajax_str");
			JSONObject jsonObject = JSONObject.parseObject(ajax_str);
			Project update_project_one=new Project();
			update_project_one.setPid(jsonObject.getInteger("pid"));

			List<Project> p= projectimp.select_project_pid_one(jsonObject.getInteger("pid"));
			Map<String,Object> update_fiedlds=ProjectFieldsController.selectProjectfieldsUpdatePermission(req,1,p.get(0),"1",null);
			//更改字段的总数
			int fields_count=jsonObject.getInteger("fields_count");
			int val=0;
			if(fields_count!=0) {
				for(int i=0;i<fields_count;i++) {
					//获取key
					String key=jsonObject.getJSONArray("change_value_ajax").getJSONObject(i).getString("key");
					switch (key) {
						case "od_num": {
							//获取key对应的值
							String value = jsonObject.getJSONArray("change_value_ajax").getJSONObject(i).getString("value");
							if (value != null && !value.equals("") && !value.equals("null")) {
								if(update_fiedlds.get("od_numval")!=null){
									/*不可查既不可更改*/
									update_project_one.setOd_num(null);
								}else{
									if(update_fiedlds.get("od_num")!=null){
										/*限制更改*/
										update_project_one.setOd_num(null);
									}else{
										if(projectimp.selectNumsCount(value)>0){
											map.put("msg","od_num_repetition");
											return map;
										}else{
											if(value.contains("PO")&&p.get(0).getProject_class()==10){
												update_project_one.setOd_num(value);
												val++;
											}else if(value.contains("OD")&&p.get(0).getProject_class()!=10){
												update_project_one.setOd_num(value);
												val++;
											}else {
												update_project_one.setOd_num(value);
											}
										}
									}
								}
							} else {
								update_project_one.setOd_num(null);
							}
							break;
						}
						case "project_name": {
							//获取key对应的值
							String value = jsonObject.getJSONArray("change_value_ajax").getJSONObject(i).getString("value");
							if (value != null && !value.equals("") && !value.equals("null")) {
								if(update_fiedlds.get("project_nameval")!=null){
									/*不可查既不可更改*/
									update_project_one.setProject_name(null);
								}else{
									if(update_fiedlds.get("project_name")!=null){
										/*限制更改*/
										update_project_one.setProject_name(null);
									}else{
										update_project_one.setProject_name(value);
										val++;
									}
								}
							} else {
								update_project_one.setProject_name(null);
							}
							break;
						}
						case "project_list": {
							//获取key对应的值
							Integer value = jsonObject.getJSONArray("change_value_ajax").getJSONObject(i).getInteger("value");
							if (value != null && value != 0) {
								if(update_fiedlds.get("project_listval")!=null){
									/*不可查既不可更改*/
									update_project_one.setProject_list(null);
								}else{
									if(update_fiedlds.get("project_list")!=null){
										/*限制更改*/
										update_project_one.setProject_list(null);
									}else{
										update_project_one.setProject_list(value);
										val++;
									}
								}
							} else {
								update_project_one.setProject_list(0);
							}
							break;
						}
						case "customer": {
							//获取key对应的值
							Integer value = jsonObject.getJSONArray("change_value_ajax").getJSONObject(i).getInteger("value");
							if (value != null && value != 0) {
								if(update_fiedlds.get("customerval")!=null){
									/*不可查既不可更改*/
									update_project_one.setCustomer(null);
								}else{
									if(update_fiedlds.get("customer")!=null){
										/*限制更改*/
										update_project_one.setCustomer(0);
									}else{
										update_project_one.setCustomer(value);
										val++;
									}
								}
							} else {
								update_project_one.setCustomer(0);
							}
							break;
						}
						case "project_suppliers": {
							//获取key对应的值
							Integer value = jsonObject.getJSONArray("change_value_ajax").getJSONObject(i).getInteger("value");
							if (value != null && value != 0) {
								if(update_fiedlds.get("project_suppliersval")!=null){
									/*不可查既不可更改*/
									update_project_one.setProject_suppliers(null);
								}else{
									if(update_fiedlds.get("project_suppliers")!=null){
										/*限制更改*/
										update_project_one.setProject_suppliers(0);
									}else{
										update_project_one.setProject_suppliers(value);
										val++;
									}
								}
							} else {
								update_project_one.setProject_suppliers(0);
							}
							break;
						}
						case "project_pm": {
							//获取key对应的值
							Integer value = jsonObject.getJSONArray("change_value_ajax").getJSONObject(i).getInteger("value");
							if (value != null && value != 0) {
								if(update_fiedlds.get("project_pmval")!=null){
									/*不可查既不可更改*/
									update_project_one.setProject_pm(null);
								}else{
									if(update_fiedlds.get("project_pm")!=null){
										/*限制更改*/
										update_project_one.setProject_pm(0);
									}else{
										update_project_one.setProject_pm(value);
										val++;
									}
								}
							} else {
								update_project_one.setProject_pm(0);
							}
							break;
						}
						case "project_market": {
							//获取key对应的值
							Integer value = jsonObject.getJSONArray("change_value_ajax").getJSONObject(i).getInteger("value");
							if (value != null && value != 0) {
								if(update_fiedlds.get("project_marketval")!=null){
									/*不可查既不可更改*/
									update_project_one.setProject_market(null);
								}else{
									if(update_fiedlds.get("project_market")!=null){
										/*限制更改*/
										update_project_one.setProject_market(0);
									}else{
										update_project_one.setProject_market(value);
										val++;
									}
								}

							} else {
								update_project_one.setProject_market(0);
							}
							break;
						}
						case "contract_num": {
							//获取key对应的值
							String value = jsonObject.getJSONArray("change_value_ajax").getJSONObject(i).getString("value");
							if (value != null && !value.equals("") && !value.equals("null")) {
								if(update_fiedlds.get("contract_numval")!=null){
									/*不可查既不可更改*/
									update_project_one.setContract_num(null);
								}else{
									if(update_fiedlds.get("contract_num")!=null){
										/*限制更改*/
										update_project_one.setContract_num(null);
									}else{
										update_project_one.setContract_num(value);
										val++;
									}
								}
							} else {
								update_project_one.setContract_num(null);
							}
							break;
						}
						case "services_num": {
							//获取key对应的值
							String value = jsonObject.getJSONArray("change_value_ajax").getJSONObject(i).getString("value");
							if (value != null && !value.equals("") && !value.equals("null")) {
								if(update_fiedlds.get("services_numval")!=null){
									/*不可查既不可更改*/
									update_project_one.setServices_num(null);
								}else{
									if(update_fiedlds.get("services_num")!=null){
										/*限制更改*/
										update_project_one.setServices_num(null);
									}else{
										/*服务编号需要保持不重复（主项目&&附属项目）*/
										Long project_count=projectimp.selectRowsByid(0,0,value);
										Long attached_count=attachedImp.select_attached_by_service_num(value);
										Long allcount=project_count+attached_count;
										if(allcount>0){
											map.put("msg","service_num_repetition");
											return map;
										}else{
											update_project_one.setServices_num(value);
											val++;
										}
									}
								}

							} else {
								update_project_one.setServices_num(null);
							}
							break;
						}
						case "project_speed": {
							//获取key对应的值
							String value = jsonObject.getJSONArray("change_value_ajax").getJSONObject(i).getString("value");
							if (value != null && !value.equals("0") && !value.equals("") && !value.equals("null")) {
								if(update_fiedlds.get("project_speedval")!=null){
									/*不可查既不可更改*/
									update_project_one.setProject_speed(null);
								}else {
									if (update_fiedlds.get("project_speed")!=null) {
										/*限制更改*/
										update_project_one.setProject_speed(null);
									} else {
										update_project_one.setProject_speed(value);
										val++;
									}
								}
							} else {
								update_project_one.setProject_speed(null);
							}
							break;
						}
						case "project_startdate": {
							//获取key对应的值
							String value = jsonObject.getJSONArray("change_value_ajax").getJSONObject(i).getString("value");

							if (value != null && !value.equals("")) {
								if(update_fiedlds.get("project_startdateval")!=null){
									/*不可查既不可更改*/
									update_project_one.setProject_startdate(null);
								}else {
									if (update_fiedlds.get("project_startdate") != null) {
										/*限制更改*/
										update_project_one.setProject_startdate((long) 0);
									} else {
										Long ps;
										ps = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(value+" 00:00:00").getTime();
										update_project_one.setProject_startdate(ps);
										val++;
									}
								}
							} else {
								update_project_one.setProject_startdate((long) 0);
							}
							break;
						}
						case "project_billdate": {
							//获取key对应的值
							String value = jsonObject.getJSONArray("change_value_ajax").getJSONObject(i).getString("value");
							if (value != null && !value.equals("")) {
								if(update_fiedlds.get("project_billdateval")!=null){
									/*不可查既不可更改*/
									update_project_one.setProject_billdate(null);
								}else {
									if (update_fiedlds.get("project_billdate") != null) {
										/*限制更改*/
										update_project_one.setProject_billdate((long) 0);
									} else {
										Long pb;
										pb = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(value+" 00:00:00").getTime();
										update_project_one.setProject_billdate(pb);
										val++;
									}
								}
							} else {
								update_project_one.setProject_billdate((long) 0);
							}
							break;
						}
						case "project_suppliers_chargeable_time": {
							//获取key对应的值
							String value = jsonObject.getJSONArray("change_value_ajax").getJSONObject(i).getString("value");
							if (value != null && !value.equals("")) {
								if(update_fiedlds.get("project_suppliers_chargeable_timeval")!=null){
									/*不可查既不可更改*/
									update_project_one.setProject_suppliers_chargeable_time(null);
								}else {
									if (update_fiedlds.get("project_suppliers_chargeable_time") != null) {
										/*限制更改*/
										update_project_one.setProject_suppliers_chargeable_time((long) 0);
									} else {
										Long psct;
										psct = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(value+" 00:00:00").getTime();
										update_project_one.setProject_suppliers_chargeable_time(psct);
										val++;
									}
								}
							} else {
								update_project_one.setProject_suppliers_chargeable_time((long) 0);
							}
							break;
						}
						case "project_enddate": {
							//获取key对应的值
							String value = jsonObject.getJSONArray("change_value_ajax").getJSONObject(i).getString("value");
							if (value != null && !value.equals("")) {
								if(update_fiedlds.get("project_enddateval")!=null){
									/*不可查既不可更改*/
									update_project_one.setProject_enddate(null);
								}else {
									if(update_fiedlds.get("project_enddate")!=null){
										/*限制更改*/
										update_project_one.setProject_enddate((long) 0);
									}else{
										Long pe;
										pe = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(value+" 00:00:00").getTime();
										update_project_one.setProject_enddate(pe);
										val++;
									}
								}
							} else {
								update_project_one.setProject_enddate((long) 0);
							}
							break;
						}
						case "project_expected_completion_date": {
							//获取key对应的值
							String value = jsonObject.getJSONArray("change_value_ajax").getJSONObject(i).getString("value");
							if (value != null && !value.equals("")) {
								if(update_fiedlds.get("project_expected_completion_dateval")!=null){
									/*不可查既不可更改*/
									update_project_one.setProject_expected_completion_date(null);
								}else {
									if (update_fiedlds.get("project_expected_completion_date") != null) {
										/*限制更改*/
										update_project_one.setProject_expected_completion_date((long) 0);
									} else {
										Long pecd;
										pecd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(value+" 00:00:00").getTime();
										update_project_one.setProject_expected_completion_date(pecd);
										val++;
									}
								}
							} else {
								update_project_one.setProject_expected_completion_date((long) 0);
							}
							break;
						}
						case "project_actual_finishing_date": {
							//获取key对应的值
							String value = jsonObject.getJSONArray("change_value_ajax").getJSONObject(i).getString("value");

							if (value != null && !value.equals("")) {
								if(update_fiedlds.get("project_actual_finishing_dateval")!=null){
									/*不可查既不可更改*/
									update_project_one.setProject_actual_finishing_date(null);
								}else {
									if (update_fiedlds.get("project_actual_finishing_date") != null) {
										/*限制更改*/
										update_project_one.setProject_actual_finishing_date((long) 0);
									} else {
										Long pafd;
										pafd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(value+" 00:00:00").getTime();
										update_project_one.setProject_actual_finishing_date(pafd);
										val++;
									}
								}
							} else {
								update_project_one.setProject_actual_finishing_date((long) 0);
							}
							break;
						}
						case "project_status": {
							//获取key对应的值
							Integer value = jsonObject.getJSONArray("change_value_ajax").getJSONObject(i).getInteger("value");
							if (value != null && value != 0) {
								if(update_fiedlds.get("project_statusval")!=null){
									/*不可查既不可更改*/
									update_project_one.setProject_status(null);
								}else {
									if (update_fiedlds.get("project_status") != null) {
										/*限制更改*/
										update_project_one.setProject_status(0);
									} else {
										update_project_one.setProject_status(value);
										val++;
									}
								}
							} else {
								update_project_one.setProject_status(0);
							}
							break;
						}
						case "project_class": {
							//获取key对应的值
							Integer value = jsonObject.getJSONArray("change_value_ajax").getJSONObject(i).getInteger("value");
							Map<String,Object> od_num_map=od_num_time();
							int  year= (int) od_num_map.get("year");
							int  month= (int) od_num_map.get("month");
							int  day= (int) od_num_map.get("day");
							final long  firstDayOfMonth= (long) od_num_map.get("firstDayOfMonth");
							final long  lastDayOfMonth= (long) od_num_map.get("lastDayOfMonth");
							List<Project> nums=projectimp.selectNums();

							if (value != null && value != 0) {
								if(value!=10){
									if(update_fiedlds.get("project_classval")!=null){
										/*不可查既不可更改*/
										update_project_one.setProject_class(null);
									}else {
										if (update_fiedlds.get("project_class") != null) {
											/*限制更改*/
											update_project_one.setProject_class(0);
										} else {
											if(update_project_one.getOd_num()!=null&&!update_project_one.getOd_num().equals("")){
												//od编号
												Long count=projectimp.selectCount_createdate(firstDayOfMonth,lastDayOfMonth,10);
												update_project_one.setOd_num(odRoutineNum(count,year,month,day,nums));
												update_project_one.setProject_class(value);
												val++;
											}
										}
									}
								}else{
									if(update_project_one.getOd_num()!=null&&!update_project_one.getOd_num().equals("")){
										//od编号
										Long count=projectimp.selectCount_createdate(firstDayOfMonth,lastDayOfMonth,10);
										update_project_one.setOd_num(odTestNum(count,year,month,day,nums));
										update_project_one.setProject_class(value);
										val++;
									}else{
										update_project_one.setOd_num(null);
									}
								}
							} else {
								update_project_one.setProject_class(0);
							}
							break;
						}
						case "project_install_address": {
							//获取key对应的值
							String value = jsonObject.getJSONArray("change_value_ajax").getJSONObject(i).getString("value");
							if (value != null && !value.equals("") && !value.equals("null")) {
								if(update_fiedlds.get("project_install_addressval")!=null){
									/*不可查既不可更改*/
									update_project_one.setProject_install_address(null);
								}else {
									if (update_fiedlds.get("project_install_address") != null) {
										/*限制更改*/
										update_project_one.setProject_install_address(null);
									} else {
										update_project_one.setProject_install_address(value);
										val++;
									}
								}
							} else {
								update_project_one.setProject_install_address(null);
							}
							break;
						}
						case "currency": {
							//获取key对应的值
							Integer value = jsonObject.getJSONArray("change_value_ajax").getJSONObject(i).getInteger("value");
							if (value != null && value != 0) {
								if(update_fiedlds.get("currencyval")!=null){
									/*不可查既不可更改*/
									update_project_one.setCurrency(null);
								}else {
									if (update_fiedlds.get("currency") != null) {
										/*限制更改*/
										update_project_one.setCurrency(0);
									} else {
										update_project_one.setCurrency(value);
										val++;
									}
								}
							} else {
								update_project_one.setCurrency(0);
							}
							break;
						}
						case "project_time_fee": {
							//获取key对应的值
							Integer value = jsonObject.getJSONArray("change_value_ajax").getJSONObject(i).getInteger("value");
							if (value != null && value != 0) {
								if(update_fiedlds.get("project_time_feeval")!=null){
									/*不可查既不可更改*/
									update_project_one.setProject_time_fee(null);
								}else {
									if (update_fiedlds.get("project_time_fee") != null) {
										/*限制更改*/
										update_project_one.setProject_time_fee(0);
									} else {
										update_project_one.setProject_time_fee(value);
										val++;
									}
								}
							} else {
								update_project_one.setProject_time_fee(0);
							}
							break;
						}
						case "projetc_moon_fee": {
							//获取key对应的值
							Integer value = jsonObject.getJSONArray("change_value_ajax").getJSONObject(i).getInteger("value");
							if (value != null && value != 0) {
								if(update_fiedlds.get("projetc_moon_feeval")!=null){
									/*不可查既不可更改*/
									update_project_one.setProject_moon_fee(null);
								}else {
									if (update_fiedlds.get("projetc_moon_fee") != null) {
										/*限制更改*/
										update_project_one.setProject_moon_fee(0);
									} else {
										update_project_one.setProject_moon_fee(value);
										val++;
									}
								}
							} else {
								update_project_one.setProject_moon_fee(0);
							}
							break;
						}
						case "project_contract_party": {
							//获取key对应的值
							Integer value = jsonObject.getJSONArray("change_value_ajax").getJSONObject(i).getInteger("value");
							if (value != null && value != 0) {
								if(update_fiedlds.get("project_contract_partyval")!=null){
									/*不可查既不可更改*/
									update_project_one.setProject_contract_party(null);
								}else {
									if (update_fiedlds.get("project_contract_party") != null) {
										/*限制更改*/
										update_project_one.setProject_contract_party(0);
									} else {
										update_project_one.setProject_contract_party(value);
										val++;
									}
								}
							} else {
								update_project_one.setProject_contract_party(0);
							}
							break;
						}
						case "project_progress_plan": {
							//获取key对应的值
							String value = jsonObject.getJSONArray("change_value_ajax").getJSONObject(i).getString("value");
							if (value != null && !value.equals("") && !value.equals("null")) {
								if(update_fiedlds.get("project_progress_planval")!=null){
									/*不可查既不可更改*/
									update_project_one.setProject_progress_plan(null);
								}else {
									if (update_fiedlds.get("project_progress_plan") != null) {
										/*限制更改*/
										update_project_one.setProject_progress_plan(null);
									} else {
										update_project_one.setProject_progress_plan(value);
										val++;
									}
								}
							} else {
								update_project_one.setProject_progress_plan(null);
							}
							break;
						}
						case "project_remark": {
							//获取key对应的值
							String value = jsonObject.getJSONArray("change_value_ajax").getJSONObject(i).getString("value");
							if (value != null && !value.equals("") && !value.equals("null")) {
								if(update_fiedlds.get("project_remarkval")!=null){
									/*不可查既不可更改*/
									update_project_one.setProject_remark(null);
								}else {
									if (update_fiedlds.get("project_remark") != null) {
										/*限制更改*/
										update_project_one.setProject_remark(null);
									} else {
										update_project_one.setProject_remark(value);
										val++;
									}
								}
							} else {
								update_project_one.setProject_remark(null);
							}
							break;
						}
						case "speed_unit": {
							//获取key对应的值
							Integer value = jsonObject.getJSONArray("change_value_ajax").getJSONObject(i).getInteger("value");
							if (value != null && value != 0) {
								if(update_fiedlds.get("speed_unitval")!=null){
									/*不可查既不可更改*/
									update_project_one.setSpeed_unit(null);
								}else {
									if (update_fiedlds.get("speed_unit") != null) {
										/*限制更改*/
										update_project_one.setSpeed_unit(null);
									} else {
										update_project_one.setSpeed_unit(value);
										val++;
									}
								}
							} else {
								update_project_one.setSpeed_unit(0);
							}
							break;
						}
						case "service_hours": {
							//获取key对应的值
							String value = jsonObject.getJSONArray("change_value_ajax").getJSONObject(i).getString("value");
							if (value != null && !value.equals("0") && !value.equals("") && !value.equals("null")) {
								if(update_fiedlds.get("service_hoursval")!=null){
									/*不可查既不可更改*/
									update_project_one.setService_hours(null);
								}else {
									if (update_fiedlds.get("service_hours") != null) {
										/*限制更改*/
										update_project_one.setService_hours(null);
									} else {
										update_project_one.setService_hours(value);
										val++;
									}
								}
							} else {
								update_project_one.setService_hours(null);
							}
							break;
						}
						case "services_deadline": {
							//获取key对应的值
							Integer value = jsonObject.getJSONArray("change_value_ajax").getJSONObject(i).getInteger("value");
							if (value != null && value != 0) {
								if(update_fiedlds.get("services_deadlineval")!=null){
									/*不可查既不可更改*/
									update_project_one.setServices_deadline(null);
								}else {
									if (update_fiedlds.get("services_deadline") != null) {
										/*限制更改*/
										update_project_one.setServices_deadline(null);
									} else {
										update_project_one.setServices_deadline(value);
										val++;
									}
								}
							} else {
								update_project_one.setServices_deadline(0);
							}
							break;
						}
						case "project_install_address_b": {
							//获取key对应的值
							String value = jsonObject.getJSONArray("change_value_ajax").getJSONObject(i).getString("value");
							if (value != null && !value.equals("") && !value.equals("null")) {
								if(update_fiedlds.get("project_install_address_bval")!=null){
									/*不可查既不可更改*/
									update_project_one.setProject_install_address_b(null);
								}else {
									if (update_fiedlds.get("project_install_address_b") != null) {
										/*限制更改*/
										update_project_one.setProject_install_address_b(null);
									} else {
										update_project_one.setProject_install_address_b(value);
										val++;
									}
								}
							} else {
								update_project_one.setProject_install_address_b(null);
							}
							break;
						}
					}
				}
				if(val>0){
					projectimp.update_project_one(update_project_one);
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

	/*删除*删除*删除*删除*删除*删除*删除*删除*删除*删除*删除*删除*删除*删除*删除*删除*删除*删除*删除*删除*删除*删除*删除*删除*删除*删除*删除*/
	@RequestMapping(value="/del_file",method = RequestMethod.POST)
	@ResponseBody
	//删除其他附件
	public Map<String, Object> delFile(int pid,String tag_add){
		Map<String, Object> map= new HashMap<>();
		String project_other_list=(projectimp.select_project_pid_one(pid)).get(0).getProject_other();
		List<Object> new_project_other=new ArrayList<>();
		if(!project_other_list.equals("") || project_other_list!=null) {
			String[] retval = project_other_list.split("\\|");
			for (String s : retval) {
				if (s.equals(tag_add) == false) {
					//比对不存在，追加字符串，得到新的子项目集合
					new_project_other.add(s);
				}
			}
			String new_project_other_list="";
			for(int t=0;t<new_project_other.size();t++) {
				if(t==0) {
					new_project_other_list+=new_project_other.get(t);
				}else {
					new_project_other_list+="|"+new_project_other.get(t);
				}
			}
			Project new_project_one=new Project();
			new_project_one.setProject_other(new_project_other_list);
			new_project_one.setPid(pid);
			projectimp.update_project_one(new_project_one);
			//修改后，删除附属项目
			CreateFile.delFile_other(SysVersion.del_file().get("path").toString(),tag_add);
			map.put("msg", "success");
		}else {
			//不可能为空，服务器错误
			map.put("msg", "ServerError");
		}
		return map;
	}
	/*其他*其他*其他*其他*其他*其他*其他*其他*其他*其他*其他*其他*其他*其他*其他*其他*其他*其他*其他*其他*其他*其他*其他*其他*其他*其他*其他*/
	@RequestMapping(value="/html_to_pdf",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> htmlToPDF(String html_pdf) throws  InterruptedException{
		Map<String, Object> pdf=new HashMap<>();
		SysVersion sysversion = new SysVersion();
		Map<String, Object> htmltopdf_file=sysversion.htmltopdf_file();
		UUID uuid = UUID.randomUUID();
		//创建html 追加table
		CreateFile.createFile(htmltopdf_file.get("createfile_path").toString(),uuid.toString(),html_pdf);
		Thread.currentThread().sleep(5000);
		//转储为PDF
		String srcPath=htmltopdf_file.get("srcPath").toString()+uuid.toString()+".html";
		String destPath=htmltopdf_file.get("destPath").toString()+uuid.toString()+".pdf";
		String createfile_path=htmltopdf_file.get("createfile_path").toString();
		boolean convert= JavaToPdf.convert(htmltopdf_file.get("exe_pdf").toString(),srcPath,destPath);
		if(convert==true) {
			pdf.put("msg","success");
			pdf.put("uuid",uuid);
			CreateFile.delFile(createfile_path,uuid.toString());
		}
		return pdf;
	}
}
