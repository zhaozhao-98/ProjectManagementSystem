package com.dcc.ProjectManagementSystem.utils;

import com.dcc.ProjectManagementSystem.entity.Project;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class test {
	/**
	 * 随机数
	 * @param place 定义随机数的位数
	 */
	public static String randomGen(int place) {
		String base = "wqetryuioplkjhgfdsazxcvbmnABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		StringBuilder sb = new StringBuilder();
		Random rd = new Random();
		for(int i=0;i<place;i++) {
			sb.append(base.charAt(rd.nextInt(base.length())));
		}
		return sb.toString();
	}
	public static void main(String args[]) throws ParseException {
		String str = "PO20220601099";
		System.out.println(Long.parseLong(str.substring(str.length()-3,str.length())));
	}
}
