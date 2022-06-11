package com.dcc.ProjectManagementSystem.controller;

import com.dcc.ProjectManagementSystem.entity.AttachedProject;
import com.dcc.ProjectManagementSystem.entity.Personnel;
import com.dcc.ProjectManagementSystem.entity.Project;
import com.dcc.ProjectManagementSystem.entity.ProjectFields;
import com.dcc.ProjectManagementSystem.serviceImp.ProjectFieldsImp;
import com.dcc.ProjectManagementSystem.utils.JavaReflection;
import com.dcc.ProjectManagementSystem.utils.Md5Utils;
import com.dcc.ProjectManagementSystem.utils.SysVersion;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/project_fields_controller")
public class ProjectFieldsController {
    @Autowired
    private  ProjectFieldsImp projectfieldsimp;
    public static ProjectFieldsController projectionist_backup;
    @PostConstruct // 初始化
    public void init(){
        projectionist_backup = this;
        projectionist_backup.projectfieldsimp = this.projectfieldsimp;
    }
    /*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*/
    public static Map<String,Object> selectProjectFieldsPermission(HttpServletRequest request, Integer table_id, Project p,String project_id,AttachedProject ap) throws IOException, JDOMException, NoSuchFieldException, ClassNotFoundException, IllegalAccessException {
        /*数据处理*/
        Map<String, Object> map=new HashMap<>();
        Map<String, Object> builder_path= SysVersion.permission_xml();
        HttpSession session = request.getSession();
        SAXBuilder builder = new SAXBuilder();

        List<Personnel> personnel_one= (List<Personnel>) session.getAttribute("personnel");
        String p_email=personnel_one.get(0).getPersonnel_email();
        Document document = builder.build(builder_path.get("builder_path")+ Md5Utils.MD5(p_email)+".xml");
        Element beauties = document.getRootElement();


        List<Element> users = beauties.getChildren("user");
        String uid = users.get(0).getAttributeValue("uid");
        String email_this = users.get(0).getChildText("email");
        String permission_this = users.get(0).getChildText("permission");
        String table_tag= Md5Utils.MD5(email_this+uid+permission_this);
        List<ProjectFields> list_project_fields = projectionist_backup.projectfieldsimp.select_projectfields_permission(table_tag,table_id);
        /*当this单的pm是登录用户时，都可查，反之限制权限*/
        if(project_id.equals("1")){
            if(p.getProject_pm()!=personnel_one.get(0).getPid()){
                for (ProjectFields list_project_field : list_project_fields) {
                    String fieldName=list_project_field.getTable_fields();
                    if (list_project_field.getFields_select() != 1) {
                        long fields_value_long=-1;
                        JavaReflection.fieldPermisstion(fieldName,"NullPointerValue",-1,fields_value_long,p);
                    }
                }
            }else  if(p.getProject_pm()==personnel_one.get(0).getPid()){
                for (ProjectFields list_project_field : list_project_fields) {
                    list_project_field.setFields_update(1);
                    list_project_field.setFields_select(1);
                }
            }
            map.put("project_one",p);
            map.put("list_project_fields",list_project_fields);
        }else if(project_id.equals("2")){
            if(p.getProject_pm()!=personnel_one.get(0).getPid()){
                for (ProjectFields list_project_field : list_project_fields) {
                    String fieldName=list_project_field.getTable_fields();
                    if (list_project_field.getFields_select() != 1) {
                        long fields_value_long=-1;
                        JavaReflection.fieldAttachedProjectPermisstion(fieldName,"NullPointerValue",-1,fields_value_long,ap);
                    }
                }
            }else if(p.getProject_pm()==personnel_one.get(0).getPid()){
                for (ProjectFields list_project_field : list_project_fields) {
                    list_project_field.setFields_update(1);
                    list_project_field.setFields_select(1);
                }
            }
            map.put("attached_list",ap);
            map.put("list_project_fields_AttachedProject",list_project_fields);
        }
        return map;
    }

    public static Map<String,Object> selectProjectFieldsUpdatePermission(HttpServletRequest request, Integer table_id, Project p, String project_id, AttachedProject ap) throws IOException, JDOMException, NoSuchFieldException, ClassNotFoundException, IllegalAccessException {
        /*数据处理*/
        Map<String, Object> map=new HashMap<>();
        Map<String, Object> builder_path= SysVersion.permission_xml();
        HttpSession session = request.getSession();
        SAXBuilder builder = new SAXBuilder();

        List<Personnel> personnel_one= (List<Personnel>) session.getAttribute("personnel");
        String p_email=personnel_one.get(0).getPersonnel_email();
        Document document = builder.build(builder_path.get("builder_path")+ Md5Utils.MD5(p_email)+".xml");
        Element beauties = document.getRootElement();


        List<Element> users = beauties.getChildren("user");
        String uid = users.get(0).getAttributeValue("uid");
        String email_this = users.get(0).getChildText("email");
        String permission_this = users.get(0).getChildText("permission");
        String table_tag= Md5Utils.MD5(email_this+uid+permission_this);

        List<ProjectFields> update_fiedlds = projectionist_backup.projectfieldsimp.select_projectfields_permission(table_tag,table_id);
        if(project_id.equals("1")){
            /*当this单的pm是登录用户时，都可改，反之限制权限*/
            if(p.getProject_pm()!=personnel_one.get(0).getPid()){
                for (ProjectFields update_fiedld : update_fiedlds) {
                    String fieldName=update_fiedld.getTable_fields();
                    if (update_fiedld.getFields_update() != 1) {
                        map.put("update"+fieldName,fieldName);
                    }
                    if(update_fiedld.getFields_select() != 1){
                        map.put("update"+fieldName+"val",fieldName);
                    }
                }
            }
        }else if(project_id.equals("2")){
            /*附属单*/
            if(p.getProject_pm()!=personnel_one.get(0).getPid()){
                for (ProjectFields update_fiedld : update_fiedlds) {
                    String fieldName=update_fiedld.getTable_fields();
                    if (update_fiedld.getFields_update() != 1) {
                        map.put("update"+fieldName,fieldName);
                    }
                    if(update_fiedld.getFields_select() != 1){
                        map.put("update"+fieldName+"val",fieldName);
                    }
                }
            }
        }


        return map;
    }


    public static Map<String,Object> selectProjectFieldsDeletePermission(HttpServletRequest request, Integer table_id, Project p) throws IOException, JDOMException, NoSuchFieldException, ClassNotFoundException, IllegalAccessException {
        /*数据处理*/
        Map<String, Object> map=new HashMap<>();
        Map<String, Object> builder_path= SysVersion.permission_xml();
        HttpSession session = request.getSession();
        SAXBuilder builder = new SAXBuilder();

        List<Personnel> personnel_one= (List<Personnel>) session.getAttribute("personnel");
        String p_email=personnel_one.get(0).getPersonnel_email();
        Document document = builder.build(builder_path.get("builder_path")+ Md5Utils.MD5(p_email)+".xml");
        Element beauties = document.getRootElement();


        List<Element> users = beauties.getChildren("user");
        String uid = users.get(0).getAttributeValue("uid");
        String email_this = users.get(0).getChildText("email");
        String permission_this = users.get(0).getChildText("permission");
        String table_tag= Md5Utils.MD5(email_this+uid+permission_this);

        List<ProjectFields> delete_fiedlds = projectionist_backup.projectfieldsimp.select_projectfields_permission(table_tag,table_id);

        if(p.getProject_pm()!=personnel_one.get(0).getPid()){
            for (ProjectFields delete_fiedld : delete_fiedlds) {
                String fieldName=delete_fiedld.getTable_fields();
                if (delete_fiedld.getFields_delete() != 1) {
                    map.put("del_"+fieldName,fieldName);
                }
            }
        }
        return map;
    }
}
