package com.dcc.ProjectManagementSystem.utils;

import com.dcc.ProjectManagementSystem.entity.Project;

import java.text.ParseException;
import java.util.List;

public class CreateOdServicesNum {
    public static String odTestNum(Long count,int year,int month,int day, List<Project> nums) {
        count=VerifyTheNumber.VerifyOdNum(nums,year,month,day);
        //od编号 测试订单
        String odTestNum = null;
        //odnum处理 以年计数并且是测试单
        if(month<10) {
            if(day<10) {
                if(count<10) {
                    odTestNum = "PO"+year+"0"+month+"0"+day+"00"+count;
                }else if(count<100&&count>=10) {
                    odTestNum="PO"+year+"0"+month+"0"+day+"0"+count;
                }else if(count<1000&&count>=100) {
                    odTestNum="PO"+year+"0"+month+"0"+day+""+count;
                }else if(count<10000&&count>=1000) {
                    odTestNum="PO"+year+"0"+month+"0"+day+""+count;
                }
            }else {
                if(count<10) {
                    odTestNum="PO"+year+"0"+month+""+day+"00"+count;
                }else if(count<100&&count>=10) {
                    odTestNum="PO"+year+"0"+month+""+day+"0"+count;
                }else if(count<1000&&count>=100) {
                    odTestNum="PO"+year+"0"+month+""+day+""+count;
                }else if(count<10000&&count>=1000) {
                    odTestNum="PO"+year+"0"+month+""+day+""+count;
                }
            }
        }else {
            if(day<10) {
                if(count<10) {
                    odTestNum="PO"+year+""+month+"0"+day+"00"+count;
                }else if(count<100&&count>=10) {
                    odTestNum="PO"+year+""+month+"0"+day+"0"+count;
                }else if(count<1000&&count>=100) {
                    odTestNum="PO"+year+""+month+"0"+day+""+count;
                }else if(count<10000&&count>=1000) {
                    odTestNum="PO"+year+""+month+"0"+day+""+count;
                }
            }else {
                if(count<10) {
                    odTestNum="PO"+year+""+month+""+day+"00"+count;
                }else if(count<100&&count>=10) {
                    odTestNum="PO"+year+""+month+""+day+"0"+count;
                }else if(count<1000&&count>=100) {
                    odTestNum="PO"+year+""+month+""+day+""+count;
                }else if(count<10000&&count>=1000) {
                    odTestNum="PO"+year+""+month+""+day+""+count;
                }
            }
        }
        return odTestNum;
    }
    public static String odRoutineNum(Long count, int year, int month, int day, List<Project> nums) {
        //常规订单
        count=VerifyTheNumber.VerifyNum(nums,year);
        String odRoutineNum = null;
        if(count<10) {
            odRoutineNum="OD"+year+"-00"+count;
        }else if(count<100&&count>=10) {
            odRoutineNum="OD"+year+"-0"+count;
        }else if(count<1000&&count>=100) {
            odRoutineNum="OD"+year+"-"+count;
        }else if(count<10000&&count>=1000) {
            odRoutineNum="OD"+year+"-"+count;
        }
        return odRoutineNum;
    }

    public static String serviceNum(Long count,Long attached_count,int year,int month,int day){
        Long all_count=count+attached_count+1;
        String serviceNum=null;
        if(month<10) {
            if(day<10) {
                if(all_count<10) {
                    serviceNum="SDPP"+year+"0"+month+"0"+day+"000"+all_count;
                }else if(all_count<100&&all_count>=10) {
                    serviceNum="SDPP"+year+"0"+month+"0"+day+"00"+all_count;
                }else if(all_count<1000&&all_count>=100) {
                    serviceNum="SDPP"+year+"0"+month+"0"+day+"0"+all_count;
                }else if(all_count<10000&&all_count>=1000) {
                    serviceNum="SDPP"+year+"0"+month+"0"+day+""+all_count;
                }
            }else {
                if(all_count<10) {
                    serviceNum="SDPP"+year+"0"+month+""+day+"000"+all_count;
                }else if(all_count<100&&all_count>=10) {
                    serviceNum="SDPP"+year+"0"+month+""+day+"00"+all_count;
                }else if(all_count<1000&&all_count>=100) {
                    serviceNum="SDPP"+year+"0"+month+""+day+"0"+all_count;
                }else if(all_count<10000&&all_count>=1000) {
                    serviceNum="SDPP"+year+"0"+month+""+day+""+all_count;
                }
            }
        }else {
            if(day<10) {
                if(all_count<10) {
                    serviceNum="SDPP"+year+""+month+"0"+day+"000"+all_count;
                }else if(all_count<100&&all_count>=10) {
                    serviceNum="SDPP"+year+""+month+"0"+day+"00"+count;
                }else if(all_count<1000&&all_count>=100) {
                    serviceNum="SDPP"+year+""+month+"0"+day+"0"+all_count;
                }else if(all_count<10000&&all_count>=1000) {
                    serviceNum="SDPP"+year+""+month+"0"+day+""+all_count;
                }
            }else {
                if(all_count<10) {
                    serviceNum="SDPP"+year+""+month+""+day+"000"+count;
                }else if(all_count<100&&all_count>=10) {
                    serviceNum="SDPP"+year+""+month+""+day+"00"+count;
                }else if(all_count<1000&&all_count>=100) {
                    serviceNum="SDPP"+year+""+month+""+day+"0"+all_count;
                }else if(all_count<10000&&all_count>=1000) {
                    serviceNum="SDPP"+year+""+month+""+day+""+all_count;
                }
            }
        }
        return serviceNum;
    }
}
