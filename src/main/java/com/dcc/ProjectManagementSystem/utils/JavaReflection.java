package com.dcc.ProjectManagementSystem.utils;

import com.dcc.ProjectManagementSystem.entity.AttachedProject;
import com.dcc.ProjectManagementSystem.entity.Project;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

public class JavaReflection {
    public static Project fieldPermisstion(String fieldName,String fields_value_string,Integer fields_value_int,Long fields_value_long,Project pr) throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException, ClassNotFoundException {

        Field f= Project.class.getDeclaredField(fieldName);
        //私有属性设置可访问
        f.setAccessible(true);

        if(f.getType().getName().contains("java.lang.Integer")){
            f.set(pr, fields_value_int);
        }else if(f.getType().getName().contains("java.lang.Long")){
            f.set(pr, fields_value_long);
        }else if(f.getType().getName().contains("java.lang.String")){
            f.set(pr, fields_value_string);
        }else{
            System.out.println("");
        }
        return pr;
    }
    public static AttachedProject fieldAttachedProjectPermisstion(String fieldName, String fields_value_string, Integer fields_value_int, Long fields_value_long, AttachedProject ap) throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException, ClassNotFoundException {

        Field f= AttachedProject.class.getDeclaredField(fieldName);
        //私有属性设置可访问
        f.setAccessible(true);

        if(f.getType().getName().contains("java.lang.Integer")){
            f.set(ap, fields_value_int);
        }else if(f.getType().getName().contains("java.lang.Long")){
            f.set(ap, fields_value_long);
        }else if(f.getType().getName().contains("java.lang.String")){
            f.set(ap, fields_value_string);
        }else{
            System.out.println("");
        }

        return ap;
    }
}
