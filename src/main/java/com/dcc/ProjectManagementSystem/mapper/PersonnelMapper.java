package com.dcc.ProjectManagementSystem.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.dcc.ProjectManagementSystem.entity.Personnel;

@Repository
public interface PersonnelMapper {
	List<Personnel> select_personnel_list();
	List<Personnel> select_personnel_one(@Param("pid")int pid);
	List<Personnel> select_personnel_one_info(@Param("pid")int pid);
	int update_stat(Personnel personnel);
	int insert_personnel(Personnel personnel);
	Long select_personnel_rows();
}
