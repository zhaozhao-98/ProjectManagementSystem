package com.dcc.ProjectManagementSystem.Interceptor;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dcc.ProjectManagementSystem.utils.IpAddressUtil;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.dcc.ProjectManagementSystem.entity.User;
import com.dcc.ProjectManagementSystem.serviceImp.PermissionImp;
import com.dcc.ProjectManagementSystem.utils.Md5Utils;
import com.dcc.ProjectManagementSystem.utils.SysVersion;

/**
 * @author admin
 *
 */
@Service
public class LoginInterceptor implements HandlerInterceptor {
	@Autowired
	protected PermissionImp permissionimp;
	
	/**
	 * @author admin
	 *在 @controller 视图渲染之后执行
	 */
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
	{
			
	}
	/**
	 * @author admin
	 *在 @Controller方法执行之后，在视图渲染之前执行
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
	{
		// 在处理过程中，执行拦截
		
		
	}
	/**
	 * @author admin
	 *在 @controller 方法之前执行
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		HttpSession session = request.getSession();
		if(session.getAttribute("user")!=null){
			 // 登录成功
			System.out.println("被拦截了："+request.getRequestURI());
			String str = request.getRequestURI();
			if(request.getRequestURI().equals("/ProjectManagementSystem/user_controller/in_main")
					|| str.contains(".html") ||request.getRequestURI().equals("/ProjectManagementSystem/user_controller/login_session")
					||request.getRequestURI().equals("/ProjectManagementSystem/user_controller/login_session_out")
					||request.getRequestURI().equals("/ProjectManagementSystem/user_controller/login_permission_session")) {
				//登录相关
				return true;
			}else if(str.contains("controller")){
				String userSession = request.getSession().getAttribute("user_index").toString();
			 	User login_user=(User) session.getAttribute("user");
			 	Map<String, Object> builder_path= SysVersion.permission_xml();

				if(Md5Utils.MD5("super"+"mwfDCCSx@ZI"+userSession).equals("7ff491a585c47e0f0256280fa72930ea")) {
					//超级管理员
					SAXBuilder builder = new SAXBuilder();
					Document document = builder.build(builder_path.get("builder_path")+ Md5Utils.MD5(login_user.getEmail())+".xml");
					if(document!=null){
						Element beauties = document.getRootElement();
						List<Element> users = (List<Element>) beauties.getChildren("user");
						String email_this = users.get(0).getChildText("email");
						String permission_this = users.get(0).getChildText("permission");
						String permission_uuid_this = users.get(0).getChildText("permission_uuid");
						return session.getAttribute("user_uuid").equals(permission_uuid_this) && login_user.getEmail().equals(email_this) && login_user.getPermission().equals(permission_this);
					}else{
						return false;
					}
				}else if(Md5Utils.MD5("super"+"mwfDCCSx@ZI"+userSession).equals("d73110b578af2ba7967d866d754834a5")) {
					//管理员
					SAXBuilder builder = new SAXBuilder();
					Document document = builder.build(builder_path.get("builder_path")+ Md5Utils.MD5(login_user.getEmail())+".xml");
					if(document!=null){
						Element beauties = document.getRootElement();
						List<Element> users = (List<Element>) beauties.getChildren("user");
						String  email_this = users.get(0).getChildText("email");
						String permission_this = users.get(0).getChildText("permission");
						String permission_uuid_this = users.get(0).getChildText("permission_uuid");
						return session.getAttribute("user_uuid").equals(permission_uuid_this) && login_user.getEmail().equals(email_this) && login_user.getPermission().equals(permission_this);
					}else{
						return false;
					}
				}else if(Md5Utils.MD5("super"+"mwfDCCSx@ZI"+userSession).equals("9e922299fe554a767569a39735b6b5bf")) {
					//用户
					SAXBuilder builder = new SAXBuilder();
					Document document = builder.build(builder_path.get("builder_path")+ Md5Utils.MD5(login_user.getEmail())+".xml");
					if(document!=null){
						Element beauties = document.getRootElement();
						List<Element> users = (List<Element>) beauties.getChildren("user");
						String  email_this = users.get(0).getChildText("email");
						String permission_this = users.get(0).getChildText("permission");
						String permission_uuid_this = users.get(0).getChildText("permission_uuid");
						return session.getAttribute("user_uuid").equals(permission_uuid_this) && login_user.getEmail().equals(email_this) && login_user.getPermission().equals(permission_this);
					}else{
						return false;
					}
				}else{
					//其他
					return false;
				}
			}
		}else{
			// 拦截后进入登录页面
			response.sendRedirect(request.getContextPath() + "/index.html");
			return false;
		}
		return false;
	}
}
