package com.dcc.ProjectManagementSystem.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dcc.ProjectManagementSystem.entity.Files;
import com.dcc.ProjectManagementSystem.entity.Personnel;
import com.dcc.ProjectManagementSystem.serviceImp.FilesImp;

@Controller
@RequestMapping("/files_controller")
public class FilesController {
	@Autowired
	private FilesImp filesimp;
	/*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*/
	@RequestMapping(value="/select_files",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> selectFiles(int file_tag,HttpServletRequest request){
		Map<String, Object> map=new HashMap<>();
		HttpSession session = request.getSession();
		Files files=new Files();
		if(session.getAttribute("user_index")!=null) {
			String user_index=(String) session.getAttribute("user_index");
			files.setFile_tag(file_tag);
			List<Personnel> personnel= (List<Personnel>) session.getAttribute("personnel");
			//验证通过   验证权限等级
			switch (user_index) {
				case "c4ca4238a0b923820dcc509a6f75849b":
					/* 可管理所有用户不包含同级别 */
					files.setFile_pm_id(0);
					files.setFile_valid(0);
					break;
				case "c81e728d9d4c2f636f067f89cc14862c":
					/* 可管理二级用户不包含同级 */
					files.setFile_pm_id(0);
					files.setFile_valid(1);
					break;
				case "eccbc87e4b5ce2fe28308fd9f2a7baf3":
					/* 无权限 */
					files.setFile_pm_id(personnel.get(0).getPid());
					files.setFile_valid(1);
					break;
				default:
					map.put("msg", "fail");
					break;
			}
		}
		List<Files> files_map= (List<Files>)filesimp.select_files(files);
		map.put("files_map", files_map);
		return map ;
	}
}
