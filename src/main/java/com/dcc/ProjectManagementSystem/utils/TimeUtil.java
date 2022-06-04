package com.dcc.ProjectManagementSystem.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TimeUtil {
	public static long yearlasttime(int year,int month) throws ParseException {
		Calendar cal = Calendar.getInstance();
	    //设置年份
	    cal.set(Calendar.YEAR,year);
	    //设置月份
	    cal.set(Calendar.MONTH, month-1);
	    //获取某月最大天数
	    int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
	    //设置日历中月份的最大天数
	    cal.set(Calendar.DAY_OF_MONTH, lastDay);
	    //格式化日期
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    String lastDayOfMonth = sdf.format(cal.getTime());
	    sdf.parse(lastDayOfMonth).getTime();
        return sdf.parse(lastDayOfMonth).getTime();
	}

	public static Map<String ,Object> od_num_time() throws ParseException {
		Map<String ,Object> map=new HashMap<>();
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		map.put("year",year);
		int month=cal.get(Calendar.MONTH)+1;
		map.put("month",month);
		int day=cal.get(Calendar.DATE);
		map.put("day",day);
		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String start_date = year+"-"+month+"-"+day+" 00:00:00";
		cal.set(year,month,day,00,00,00);
		cal.add(cal.DATE,1);
		String end_date =cal.get(Calendar.YEAR)+"-"+cal.get(Calendar.MONTH)+"-"+cal.get(Calendar.DAY_OF_MONTH)+" 00:00:00";
		final Date start_datetime = sdf.parse(start_date);
		final Date end_datetime = sdf.parse(end_date);
		final long start_time_lon = start_datetime.getTime();
		final long end_time_lon = end_datetime.getTime();
		map.put("start_time_lon",start_time_lon);
		map.put("end_time_lon",end_time_lon);
		final long firstDayOfMonth=sdf.parse(cal.get(Calendar.YEAR)+"-"+1+"-"+1+" 00:00:00").getTime();
		final long lastDayOfMonth= TimeUtil.yearlasttime(year, month);
		map.put("firstDayOfMonth",firstDayOfMonth);
		map.put("lastDayOfMonth",lastDayOfMonth);
		return map;
	}
}
