package com.dcc.ProjectManagementSystem.entity;


import org.springframework.stereotype.Repository;

@Repository
public class Project {
	//ID
	private Integer pid;
	//项目编号
	private String od_num;
	//合同编号
	private String contract_num;
	//服务编号
	private String services_num;
	//项目名称
	private String project_name;
	//客户
	private Integer customer;
	//销售
	private Integer project_market;
	//项目经理
	private Integer project_pm;
	//启动日期
	private Long project_startdate;
	//计费日期
	private Long project_billdate;
	//终止日期
	private Long project_enddate;
	//完工日期
	private Long project_completion_date;
	//预计完成日期
	private Long project_expected_completion_date;
	//实际完成日期
	private Long project_actual_finishing_date;
	//创建时间
	private Long create_date;
	//项目状态
	private Integer project_status;
	//带宽
	private String project_speed;
	//带宽单位
	private Integer speed_unit;
	//未解决任务
	private String project_unresolved_task;
	//未解决问题
	private String project_unresolved_problem;
	//项目类别
	private Integer project_class;
	//安装地址A端
	private String project_install_address;
	//安装地址B端
	private String project_install_address_b;
	//合同附件
	private String project_contract_pdf;
	//开通单附件
	private String project_sailing_notice;
	//子项目
	private String project_soon;
	//一次性费用
	private Integer project_time_fee;
	//月费
	private Integer project_moon_fee;
	//我司签约主体
	private Integer project_contract_party;
	//进度更新
	private String project_progress_plan;
	//拓扑图
	private String project_gplot;
	//拓扑图
	private String project_other;
	//备注、简述
	private String project_remark;
	//币种
	private Integer currency;
	//客户
	private Customer customer_list;
	//人员
	private Personnel personnel_list;
	//类别（other）
	private Other class_other;
	//状态（other）
	private Other status_other;
	//项目集
	private Integer project_list;
	//服务期限
	private String service_hours;
	//服务期限单位
	private Integer services_deadline;
	//供应商
	private Integer project_suppliers;
	//供应商计费时间
	private Long project_suppliers_chargeable_time;
	//stat_tag
	private String stat_tag;
	//
	private Integer user_uid;
	//
	private Integer stat_val;


	public Integer getStat_val() {
		return stat_val;
	}
	public void setStat_val(Integer stat_val) {
		this.stat_val = stat_val;
	}
	public Integer getUser_uid() {
		return user_uid;
	}
	public void setUser_uid(Integer user_uid) {
		this.user_uid = user_uid;
	}
	public String getStat_tag() {
		return stat_tag;
	}
	public void setStat_tag(String stat_tag) {
		this.stat_tag = stat_tag;
	}
	public Integer getProject_suppliers() {
		return project_suppliers;
	}
	public void setProject_suppliers(Integer project_suppliers) {
		this.project_suppliers = project_suppliers;
	}
	public Long getProject_suppliers_chargeable_time() {
		return project_suppliers_chargeable_time;
	}
	public void setProject_suppliers_chargeable_time(Long project_suppliers_chargeable_time) {
		this.project_suppliers_chargeable_time = project_suppliers_chargeable_time;
	}
	public String getProject_install_address_b() {
		return project_install_address_b;
	}
	public void setProject_install_address_b(String project_install_address_b) {
		this.project_install_address_b = project_install_address_b;
	}
	public Integer getServices_deadline() {
		return services_deadline;
	}
	public void setServices_deadline(Integer services_deadline) {
		this.services_deadline = services_deadline;
	}
	public Integer getProject_list() {
		return project_list;
	}
	public void setProject_list(Integer project_list) {
		this.project_list = project_list;
	}
	public Personnel getPersonnel_list() {
		return personnel_list;
	}
	public void setPersonnel_list(Personnel personnel_list) {
		this.personnel_list = personnel_list;
	}
	public Customer getCustomer_list() {
		return customer_list;
	}
	public void setCustomer_list(Customer customer_list) {
		this.customer_list = customer_list;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public String getOd_num() {
		return od_num;
	}
	public void setOd_num(String od_num) {
		this.od_num = od_num;
	}
	public String getContract_num() {
		return contract_num;
	}
	public void setContract_num(String contract_num) {
		this.contract_num = contract_num;
	}
	public String getServices_num() {
		return services_num;
	}
	public void setServices_num(String services_num) {
		this.services_num = services_num;
	}
	public String getProject_name() {
		return project_name;
	}
	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}
	public Integer getCustomer() {
		return customer;
	}
	public void setCustomer(Integer customer) {
		this.customer = customer;
	}
	public Integer getProject_market() {
		return project_market;
	}
	public void setProject_market(Integer project_market) {
		this.project_market = project_market;
	}
	public Integer getProject_pm() {
		return project_pm;
	}
	public void setProject_pm(Integer project_pm) {
		this.project_pm = project_pm;
	}
	public Long getProject_startdate() {
		return project_startdate;
	}
	public void setProject_startdate(Long project_startdate) {
		this.project_startdate = project_startdate;
	}
	public Long getProject_billdate() {
		return project_billdate;
	}
	public void setProject_billdate(Long project_billdate) {
		this.project_billdate = project_billdate;
	}
	public Long getProject_enddate() {
		return project_enddate;
	}
	public void setProject_enddate(Long project_enddate) {
		this.project_enddate = project_enddate;
	}
	public Long getProject_completion_date() {
		return project_completion_date;
	}
	public void setProject_completion_date(Long project_completion_date) {
		this.project_completion_date = project_completion_date;
	}

	public Long getProject_expected_completion_date() {
		return project_expected_completion_date;
	}

	public void setProject_expected_completion_date(Long project_expected_completion_date) {
		this.project_expected_completion_date = project_expected_completion_date;
	}

	public Long getProject_actual_finishing_date() {
		return project_actual_finishing_date;
	}
	public void setProject_actual_finishing_date(Long project_actual_finishing_date) {
		this.project_actual_finishing_date = project_actual_finishing_date;
	}
	public Long getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Long create_date) {
		this.create_date = create_date;
	}
	public Integer getProject_status() {
		return project_status;
	}
	public void setProject_status(Integer project_status) {
		this.project_status = project_status;
	}
	public String getProject_speed() {
		return project_speed;
	}
	public void setProject_speed(String project_speed) {
		this.project_speed = project_speed;
	}
	public String getProject_unresolved_task() {
		return project_unresolved_task;
	}
	public void setProject_unresolved_task(String project_unresolved_task) {
		this.project_unresolved_task = project_unresolved_task;
	}
	public String getProject_unresolved_problem() {
		return project_unresolved_problem;
	}
	public void setProject_unresolved_problem(String project_unresolved_problem) {
		this.project_unresolved_problem = project_unresolved_problem;
	}
	public Integer getProject_class() {
		return project_class;
	}
	public void setProject_class(Integer project_class) {
		this.project_class = project_class;
	}
	public String getProject_install_address() {
		return project_install_address;
	}
	public void setProject_install_address(String project_install_address) {
		this.project_install_address = project_install_address;
	}
	public String getProject_contract_pdf() {
		return project_contract_pdf;
	}
	public void setProject_contract_pdf(String project_contract_pdf) {
		this.project_contract_pdf = project_contract_pdf;
	}

	public String getProject_sailing_notice() {
		return project_sailing_notice;
	}

	public void setProject_sailing_notice(String project_sailing_notice) {
		this.project_sailing_notice = project_sailing_notice;
	}

	public Integer getProject_moon_fee() {
		return project_moon_fee;
	}

	public void setProject_moon_fee(Integer project_moon_fee) {
		this.project_moon_fee = project_moon_fee;
	}

	public String getProject_soon() {
		return project_soon;
	}
	public void setProject_soon(String project_soon) {
		this.project_soon = project_soon;
	}
	public Integer getProject_time_fee() {
		return project_time_fee;
	}
	public void setProject_time_fee(Integer project_time_fee) {
		this.project_time_fee = project_time_fee;
	}
	public Integer getProject_contract_party() {
		return project_contract_party;
	}
	public void setProject_contract_party(Integer project_contract_party) {
		this.project_contract_party = project_contract_party;
	}
	public String getProject_progress_plan() {
		return project_progress_plan;
	}
	public void setProject_progress_plan(String project_progress_plan) {
		this.project_progress_plan = project_progress_plan;
	}
	public String getProject_gplot() {
		return project_gplot;
	}
	public void setProject_gplot(String project_gplot) {
		this.project_gplot = project_gplot;
	}
	public String getProject_other() {
		return project_other;
	}
	public void setProject_other(String project_other) {
		this.project_other = project_other;
	}
	public String getProject_remark() {
		return project_remark;
	}
	public void setProject_remark(String project_remark) {
		this.project_remark = project_remark;
	}
	public Integer getCurrency() {
		return currency;
	}
	public void setCurrency(Integer currency) {
		this.currency = currency;
	}
	public Integer getSpeed_unit() {
		return speed_unit;
	}
	public void setSpeed_unit(Integer speed_unit) {
		this.speed_unit = speed_unit;
	}
	public String getService_hours() {
		return service_hours;
	}
	public void setService_hours(String service_hours) {
		this.service_hours = service_hours;
	}
	public Other getClass_other() {
		return class_other;
	}
	public void setClass_other(Other class_other) {
		this.class_other = class_other;
	}
	public Other getStatus_other() {
		return status_other;
	}
	public void setStatus_other(Other status_other) {
		this.status_other = status_other;
	}

}
