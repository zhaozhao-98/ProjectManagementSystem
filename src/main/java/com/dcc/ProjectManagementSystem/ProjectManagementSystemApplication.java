package com.dcc.ProjectManagementSystem;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
@MapperScan("com.dcc.ProjectManagementSystem.mapper")
public class ProjectManagementSystemApplication extends ServletInitializer{
    public static void main(String[] args) {
        SpringApplication.run(ProjectManagementSystemApplication.class, args);
    }
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder springApplicationBuilder){
        return springApplicationBuilder.sources(ProjectManagementSystemApplication.class);
    }

}
