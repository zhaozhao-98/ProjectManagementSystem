package com.dcc.ProjectManagementSystem.utils;

import java.lang.reflect.Field;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;

import com.dcc.ProjectManagementSystem.entity.Project;

public class Md5Utils {
	static final char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
	static final char hexDigitsLower[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e',
			'f' };

	/**
	 * 对字符串 MD5 无盐值加密
	 * 
	 * @param plainText 传入要加密的字符串
	 * @return MD5加密后生成32位(小写字母+数字)字符串
	 */
	public static String MD5Lower(String plainText) {
		try {
			// 获得MD5摘要算法的 MessageDigest 对象
			MessageDigest md = MessageDigest.getInstance("MD5");

			// 使用指定的字节更新摘要
			md.update(plainText.getBytes());

			// digest()最后确定返回md5 hash值，返回值为8位字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
			// BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值。1 固定值
			return new BigInteger(1, md.digest()).toString(32);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 对字符串 MD5 加密
	 * 
	 * @param plainText 传入要加密的字符串
	 * @return MD5加密后生成32位(大写字母+数字)字符串
	 */
	public static String MD5Upper(String plainText) {
		try {
			// 获得MD5摘要算法的 MessageDigest 对象
			MessageDigest md = MessageDigest.getInstance("MD5");

			// 使用指定的字节更新摘要
			md.update(plainText.getBytes());

			// 获得密文
			byte[] mdResult = md.digest();
			// 把密文转换成十六进制的字符串形式
			int j = mdResult.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (byte byte0 : mdResult) {
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];// 取字节中高 4 位的数字转换, >>> 为逻辑右移，将符号位一起右移
				str[k++] = hexDigits[byte0 & 0xf]; // 取字节中低 4 位的数字转换
			}
			return new String(str);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 对字符串 MD5 加盐值加密
	 * 
	 * @param plainText 传入要加密的字符串
	 * @param saltValue 传入要加的盐值
	 * @return MD5加密后生成32位(小写字母+数字)字符串
	 */
	public static String MD5Lower(String plainText, String saltValue) {
		try {
			// 获得MD5摘要算法的 MessageDigest 对象
			MessageDigest md = MessageDigest.getInstance("MD5");

			// 使用指定的字节更新摘要
			md.update(plainText.getBytes());
			md.update(saltValue.getBytes());

			// digest()最后确定返回md5 hash值，返回值为8位字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
			// BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值。1 固定值
			return new BigInteger(1, md.digest()).toString(16);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 对字符串 MD5 加盐值加密
	 * 
	 * @param plainText 传入要加密的字符串
	 * @param saltValue 传入要加的盐值
	 * @return MD5加密后生成32位(大写字母+数字)字符串
	 */
	public static String MD5Upper(String plainText, String saltValue) {
		try {
			// 获得MD5摘要算法的 MessageDigest 对象
			MessageDigest md = MessageDigest.getInstance("MD5");

			// 使用指定的字节更新摘要
			md.update(plainText.getBytes());
			md.update(saltValue.getBytes());

			// 获得密文
			byte[] mdResult = md.digest();
			// 把密文转换成十六进制的字符串形式
			int j = mdResult.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (byte byte0 : mdResult) {
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * MD5加密后生成32位(小写字母+数字)字符串 同 MD5Lower() 一样
	 */
	public final static String MD5(String plainText) {
		try {
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");

			mdTemp.update(plainText.getBytes("UTF-8"));

			byte[] md = mdTemp.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (byte byte0 : md) {
				str[k++] = hexDigitsLower[byte0 >>> 4 & 0xf];
				str[k++] = hexDigitsLower[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 校验MD5码
	 * 
	 * @param text 要校验的字符串
	 * @param md5  md5值
	 * @return 校验结果
	 */
	public static boolean valid(String text, String md5) {
		return md5.equals(MD5(text)) || md5.equals(MD5(text).toUpperCase());
	}

	/**
	 * 测试
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		//表权限  md5(admin+默认密码+email+uid)   user>permission
		String[] str = new String[]{
				"zhao.zhao@dcconnect.cn",
				"haoquan.liao@dcconnect.cn",
				"huimin.z@dcconnect.cn",
				"wayne.chen@dcconnect.cn",
				"jing.yang@dcconnect.cn",
				"jinxin.jiang@dcconnect.cn",
				"billy.fung@dcconnectglobal.com",
				"muqing.chen@dcconnect.cn",
				"victor.ma@dcconnectglobal.com",
				"leon.phang@dcconnect.cn",
				"",
				"xiyun.zheng@dcconnect.cn",
				"",
				"charmond.tsang@dcconnectglobal.com",
				"henry.lam@dcconnect.cn",
				"yajun.he@dcconnect.cn"};
		
		List<String> list = Arrays.asList(str);
		String defaultupwd="*o02gF3j";
		for(int i=0;i<list.size();i++) {
			String tag;
			String sa;
			if(i==0) {
				sa="superadmin";
			}else {
				sa="admin";
			}
			if(list.get(i)=="") {
				tag="无效用户:";
			}else {
				tag="有效用户:";
			}
			String a=sa+defaultupwd+list.get(i).toString()+(i+1);
			/*
			 * System.err.println(str[i]+":"); System.out.println(MD5(a));
			 */
		}
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		//表字段权限 md5(uid+email+permission)    permission>sql_table
		String[]  str1=new String[]{
			"304c83350c984df28cdb07fcbfff50aa",
			"5811ae14c8e8dd3976e2521dbef769af",
			"4f0f9e0e158b518ba7eda6dbfcb7c343",
			"a300f027801327bf8d3016bed4ce1f99",
			"63bf6e0ac1a4e9d2a1650ca5d130dcc4",
			"80b4e6184438c927f699dc24816b5475",
			"10354395b43809bb2680df0a71a8fd94",
			"4e42c20d7cb0087006a50afae2667df8",
			"c954fb4f2c9a1fb6a812cf275a27dc00",
			"9a90242d922e0f0d677af2cba8e800dc",
			"5ddd50740843fa11ca64a7fc51c4f31b",
			"fe2a3ece7eccf4eecb02dc0550f16084",
			"97842d11ecc6aa4a112a21af3d21d15e",
			"53d4c535fb6f0c820da7c9093bfd8acc",
			"69d8002a99e3d5c7563064e35da47e8f",
			"9461f807ac571680b883e7f387a71634"
		};
		List<String> list1 = Arrays.asList(str1);
		for(int j=0;j<list1.size();j++) {
			String b=str[j]+(j+1)+list1.get(j);
			Field[] fields= Project.class.getDeclaredFields();
			for (Field field : fields) {
				String sql = "INSERT INTO `promgsys`.`projectfields` ( `table_fields`,`table_tag`,`fields_update`,`fields_delete`,`fields_select`,`table_id`)"
							+"VALUES  ( '" + field.getName() + "', '" + MD5(b) + "', 0, 0, 0,1);";
				System.out.println(sql);
			}
		}
	}

}