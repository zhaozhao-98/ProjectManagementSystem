package com.dcc.ProjectManagementSystem.serviceImp;

import com.dcc.ProjectManagementSystem.entity.ProjectFields;
import com.dcc.ProjectManagementSystem.mapper.ProjectfieldsMapper;
import com.dcc.ProjectManagementSystem.services.ProjectFieldsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProjectFieldsImp implements ProjectFieldsService {
    @Autowired
    protected ProjectfieldsMapper projectfieldsmapper;
    @Override
    public List<ProjectFields> select_projectfields_permission(String table_tag, Integer table_id) {
        return projectfieldsmapper.select_projectfields_permission(table_tag,table_id);
    }
}
