package com.dcc.ProjectManagementSystem.Interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 86155
 *	配置类
 */
@Configuration
public class SessionConfiguration implements WebMvcConfigurer{
	//配置拦截器
	//白名单
	String[] whiteList= {"/**/ProMgUser/to_index",
			"/**/index.html",
			"/**/img/**",
			"/**/ProMgUser/login",
			"/**/*.css","/**/*.png",
			"/**/*.js",
			"/**/login/**",
			"/**/bootstrap/**",
			"/**/*.woff2",
			"/**/*.woff",
			"/**/*.ttf",
			"/**/temporary_pdf/**",
			"/**/primary_pdf/**",
			"/**/ProMgProject/HtmlToPDF",
			"/**/error/*.html","/*.ico"};
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
    	LoginInterceptor loginInterceptor = new LoginInterceptor();
    	InterceptorRegistration loginRegistry = registry.addInterceptor(loginInterceptor);
    	loginRegistry.addPathPatterns("/**");
    	//白名单
		for (String s : whiteList) {
			loginRegistry.excludePathPatterns(s);
		}
    }
}
