package com.dcc.ProjectManagementSystem.serviceImp;

import java.util.List;

import com.dcc.ProjectManagementSystem.entity.Project;
import com.dcc.ProjectManagementSystem.mapper.ProjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dcc.ProjectManagementSystem.services.ProjectServices;

@Service
@Transactional
public class ProjectImp implements ProjectServices {
	@Autowired
	protected ProjectMapper projectMapper;
	@Override
	public List<Project> list_select_project_main(int limit, int item){
		return projectMapper.list_select_project_main(limit,item);
	}
	@Override
	public Long selectRows() {
		// TODO Auto-generated method stub
		return 	projectMapper.selectRows();
	}
	@Override
	public List<Project> list_select_project_mod_all(int project_list, int project_pm, String stat_tag, int limit, int item) {
		// TODO Auto-generated method stub
		return projectMapper.list_select_project_mod_all(project_list,project_pm,stat_tag,limit,item);
	}
	@Override
	public int insert_project(Project pro) {
		// TODO Auto-generated method stub
		return projectMapper.insert_project(pro);
	}
	@Override
	public Long selectCount_createdate(Long start_datetime,Long end_datetime,int project_class) {
		// TODO Auto-generated method stub
		return 	 projectMapper.selectCount_createdate(start_datetime,end_datetime,project_class);
	}
	@Override
	public List<Project> select_project_pid_one(int pid) {
		// TODO Auto-generated method stub
		return projectMapper.select_project_pid_one(pid);
	}
	@Override
	public List<Project> select_About_me_conduct_project(int project_pm) {
		// TODO Auto-generated method stub
		return projectMapper.select_About_me_conduct_project(project_pm);
	}
	@Override
	public int update_Attachment(Project project) {
		// TODO Auto-generated method stub
		return projectMapper.update_Attachment(project);
	}
	@Override
	public int update_project_one(Project project) {
		// TODO Auto-generated method stub
		return projectMapper.update_project_one(project);
	}
	@Override
	public List<Project> list_project_fuzzy_query_mod_all(String keyword) {
		// TODO Auto-generated method stub
		return projectMapper.list_project_fuzzy_query_mod_all(keyword);
	}
	@Override
	public int update_project_attproject(Project project) {
		// TODO Auto-generated method stub
		return projectMapper.update_project_attproject(project);
	}
	@Override
	public Long selectRowsByid(int project_list,int project_pm,String services_num) {
		// TODO Auto-generated method stub
		return projectMapper.selectRowsByid(project_list,project_pm,services_num);
	}
	@Override
	public List<Project> search_project() {
		// TODO Auto-generated method stub
		return projectMapper.search_project();
	}

	@Override
	public List<Project> selectNums() {
		return projectMapper.selectNums();
	}

	@Override
	public List<Project> selectServiceNum() {
		return projectMapper.selectServiceNum();
	}

	@Override
	public List<Project> select_timeout_project(int project_pm, Long today_time,int limit,int item) {
		return projectMapper.select_timeout_project(project_pm,today_time,limit,item);
	}

	@Override
	public Long selectNumsCount(String od_num) {
		return projectMapper.selectNumsCount(od_num);
	}
}
