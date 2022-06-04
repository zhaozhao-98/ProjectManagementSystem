package com.dcc.ProjectManagementSystem.mapper;

import java.util.List;

import com.dcc.ProjectManagementSystem.entity.Project;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectMapper {
	List<Project> list_select_project_main(@Param("limit")int limit, @Param("item")int item);
	List<Project> list_select_project_mod_all(@Param("project_list")int project_list, @Param("project_pm")int project_pm, @Param("stat_tag")String  stat_tag, @Param("limit")int limit, @Param("item")int item);
	List<Project> list_project_fuzzy_query_mod_all(String keyword);
	List<Project> select_project_pid_one(int pid);
	List<Project> select_About_me_conduct_project(int project_pm);
	List<Project> search_project();
	List<Project> selectNums();
	List<Project> selectServiceNum();
	List<Project> select_timeout_project(int project_pm,Long today_time,int limit,int item);
	Long selectNumsCount(String od_num);
	int insert_project(Project pro);
	Long selectRows();
	Long selectRowsByid(int project_list,int project_pm,String services_num);
	Long selectCount_createdate(Long start_datetime,Long end_datetime,int project_class);
	int update_Attachment(Project project);
	int update_project_one(Project project);
	int update_project_attproject(Project project);
}
