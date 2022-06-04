package com.dcc.ProjectManagementSystem.Interceptor;

import org.springframework.boot.web.server.ErrorPageRegistry;

@FunctionalInterface
public interface ErrorPageRegistrar {
	void registerErrorPages(ErrorPageRegistry registry);
}
