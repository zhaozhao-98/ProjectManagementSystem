package com.dcc.ProjectManagementSystem.mapper;

import com.dcc.ProjectManagementSystem.entity.ProjectFields;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProjectfieldsMapper {
    List<ProjectFields> select_projectfields_permission(@Param("table_tag")String table_tag, @Param("table_id")Integer table_id);
}
