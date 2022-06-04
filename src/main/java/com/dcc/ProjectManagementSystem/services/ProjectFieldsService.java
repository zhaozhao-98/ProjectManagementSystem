package com.dcc.ProjectManagementSystem.services;

import com.dcc.ProjectManagementSystem.entity.ProjectFields;

import java.util.List;

public interface ProjectFieldsService {
    List<ProjectFields> select_projectfields_permission(String table_tag, Integer table_id);
}
