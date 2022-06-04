package com.dcc.ProjectManagementSystem.utils;

import com.dcc.ProjectManagementSystem.entity.Project;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VerifyTheNumber {
    public static Long VerifyNum(List<Project> num_list,int year){
        /*未使用过的num*/
        List furthest_nums=new ArrayList<>();
        /*最大使用限度600个num*/
        for(int i=1;i<600;i++){
            furthest_nums.add(""+i+"");
        }
        String pattern = "[-][0-9]+([1-9]\\d*\\.?\\d*)|(0\\.\\d*[1-9])";
        String regEx = "[\n`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。， 、？-]";
        for(int j=0;j<num_list.size();j++){
            String content=num_list.get(j).getOd_num();
            Pattern compile = Pattern.compile(pattern);
            Matcher matcher = compile.matcher(content);
            if (matcher.find()) {
                String newString = matcher.group().replaceAll(regEx, "");
                String Od_Num="OD"+String.valueOf(year)+"-"+newString;
                if(Od_Num.equals(num_list.get(j).getOd_num())){
                    Long use_num = Long.valueOf(newString);
                    for(int o=0;o<furthest_nums.size();o++){
                        if(use_num.equals(Long.valueOf(furthest_nums.get(o).toString()))){
                            furthest_nums.remove(""+use_num+"");
                            break;
                        }
                    }
                }
            }
        }
        Long VerifyNum = Long.valueOf(furthest_nums.get(0).toString());
        return VerifyNum;
    }

    public static Long VerifyOdNum(List<Project> od_num_list,int year,int month,int day){
        /*未使用过的num*/
        List furthest_nums=new ArrayList<>();
        /*最大使用限度600个num*/
        for(int i=1;i<199;i++){
            furthest_nums.add(""+i+"");
        }
        String PoNum=null;
        if(month<10) {
            if(day<10) {
                PoNum="PO"+year+"0"+month+"0"+day;
            }else {
                PoNum="PO"+year+"0"+month+""+day;
            }
        }else {
            if(day<10) {
                PoNum="PO"+year+""+month+"0"+day;
            }else {
                PoNum="PO"+year+""+month+""+day;
            }
        }


        for(int j=0;j<od_num_list.size();j++){
            String od_num=od_num_list.get(j).getOd_num();
            if(od_num_list.get(j).getOd_num().contains("PO")){
                String po_num=PoNum+od_num.substring(od_num.length()-3,od_num.length());
                if(po_num.equals(od_num_list.get(j).getOd_num())){
                    long use_po_num_lon = Long.parseLong(od_num.substring(od_num.length()-3,od_num.length()));
                    furthest_nums.remove(""+use_po_num_lon+"");
                }
            }
        }

        Long VerifyOdNum = Long.valueOf(furthest_nums.get(0).toString());
        return VerifyOdNum;
    }
}
