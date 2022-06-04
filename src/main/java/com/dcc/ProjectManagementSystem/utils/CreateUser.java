package com.dcc.ProjectManagementSystem.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.XMLOutputter;

public class CreateUser {
	public static boolean xml_usercode(String eamil_str,Integer uid,String permission_str,String permission_uuid_str) {
		Element permission_user = new Element("permission_user");

		Element user = new Element("user");
		Attribute id = new Attribute("uid",uid.toString());
		user.setAttribute(id);
		permission_user.addContent(user);

		Element email = new Element("email");
		email.setText(eamil_str);
		user.addContent(email);

		Element permission = new Element("permission");
		permission.setText(permission_str);
		user.addContent(permission);

		Element permission_uuid = new Element("permission_uuid");
		permission_uuid.setText(permission_uuid_str);
		user.addContent(permission_uuid);

		Document document = new Document(permission_user);
		XMLOutputter out = new XMLOutputter();
		out.setFormat(out.getFormat().setEncoding("UTF-8"));
		Map<String,Object> builder_path= SysVersion.permission_xml();
		try {
			out.output(document, new FileOutputStream(builder_path.get("builder_path")+ Md5Utils.MD5(eamil_str)+".xml"));
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	public static void main(String[] args) {
		Element beauties = new Element("permission_user");

		Element user = new Element("user");
		beauties.addContent(user);

		Element email = new Element("email");
		Attribute id = new Attribute("id", "1");
		email.setAttribute(id);
		email.setText("zhao.zhao@dcconnect.cn");
		user.addContent(email);

		Element permission = new Element("permission");
		permission.setText("304c83350c984df28cdb07fcbfff50aa");
		user.addContent(permission);

		Element permission_uuid = new Element("permission_uuid");
		permission_uuid.setText("fbd8e94a-09cd-4963-ad45-5c643fca58c0");
		user.addContent(permission_uuid);

		Document document = new Document(beauties);
		XMLOutputter out = new XMLOutputter();
		out.setFormat(out.getFormat().setEncoding("UTF-8"));

		try {
			out.output(document, new FileOutputStream("src/zhao.zhao@dcconnect.cn.xml"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
