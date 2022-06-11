package com.dcc.ProjectManagementSystem.utils;

import java.util.HashMap;
import java.util.Map;

public class SysVersion {
	private static  String sys_version="WINDOWS";
	public static  Map<String, Object> upload_file() {
		//上传文件所用字段
		String filePath;
		String 	filepath1;
		String 	filepath2;
		String 	filepath3;
		String 	filepath4;
		String 	append_file;
		Map<String, Object> upload_file_map=new HashMap<>();
		if(sys_version.equals("WINDOWS")) {
			filePath="D:\\Eclipse\\eclipse-work\\ProjectManagementSystem\\src\\main\\webapp\\primary_pdf";
			filepath1="\\project_contract_pdf\\";
			filepath2="\\project_sailing_notice\\";
			filepath3="\\project_gplot\\";
			filepath4="\\other_file\\";
			append_file="/";
		}else {
			filePath="/home/tomcat/file_backup/";
			filepath1="/project_contract_pdf/";
			filepath2="/project_sailing_notice/";
			filepath3="/project_gplot/";
			filepath4="/project_other/";
			append_file="\\";
		}
		upload_file_map.put("filePath", filePath);
		upload_file_map.put("filepath1", filepath1);
		upload_file_map.put("filepath2", filepath2);
		upload_file_map.put("filepath3", filepath3);
		upload_file_map.put("filepath4", filepath4);
		return upload_file_map;
	}
	public static Map<String ,Object> htmltopdf_file(){
		String srcPath="";
		String destPath="";
		String createfile_path="";
		String exe_pdf="";
		Map<String, Object> htmltopdf_file=new HashMap<>();
		if(sys_version.equals("WINDOWS")) {
			srcPath="http://localhost:3060/ProjectManagementSystem/primary_pdf/temporary_pdf_html/";
			destPath="D:\\Eclipse\\eclipse-work\\ProjectManagementSystem\\src\\main\\webapp\\primary_pdf\\temporary_pdf\\";
			createfile_path="D:\\Eclipse\\eclipse-work\\ProjectManagementSystem\\src\\main\\webapp\\primary_pdf\\temporary_pdf_html\\";
			exe_pdf= "C:\\Users\\admin\\Desktop\\html_to_pdf\\wkhtmltox\\bin\\wkhtmltopdf.exe";
		}else {
			srcPath="http://10.88.90.83:3060/ProjectManagementSystem/temporary_pdf_html/";
			destPath="/home/tomcat/file_backup/temporary_pdf/";
			createfile_path="/home/tomcat/file_backup/temporary_pdf_html/";
			exe_pdf= "/home/tomcat/wkhtmltox/bin/wkhtmltopdf";
		}
		htmltopdf_file.put("srcPath", srcPath);
		htmltopdf_file.put("destPath", destPath);
		htmltopdf_file.put("createfile_path", createfile_path);
		htmltopdf_file.put("exe_pdf", exe_pdf);
		return htmltopdf_file;
	}
	public static Map<String ,Object> del_file() {
		Map<String, Object> map=new HashMap<>();
		String path="";
		if(sys_version.equals("WINDOWS")) {
			path="D:\\Eclipse\\eclipse-work\\ProjectManagementSystem\\src\\main\\webapp\\primary_pdf\\other_file\\";
			map.put("path", path);
		}else {
			path="/home/tomcat/file_backup/project_other/";
			map.put("path", path);
		}
		return map;
	}
	public static Map<String ,Object> permission_xml(){
		String builder_path="";
		Map<String, Object> permission_xml=new HashMap<>();
		if(sys_version.equals("WINDOWS")) {
			builder_path="D:\\Eclipse\\eclipse-work\\ProjectManagementSystem\\src\\";
		}else {
			builder_path="/home/tomcat/file_backup/permission_xml/";
		}
		permission_xml.put("builder_path", builder_path);
		return permission_xml;
	}
}
