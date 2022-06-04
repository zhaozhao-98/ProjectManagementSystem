package com.dcc.ProjectManagementSystem.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dcc.ProjectManagementSystem.entity.Chat;
import com.dcc.ProjectManagementSystem.serviceImp.ChatImp;

@Controller
@RequestMapping("/chat_controller")
public class ChatController {
	@Autowired
	private ChatImp chatImp;
	/*添加*添加*添加*添加*添加*添加*添加*添加*添加*添加*添加*添加*添加*添加*添加*添加*添加*添加*添加*添加*添加*添加*添加*添加*添加*添加*添加*/
	@RequestMapping(value="/insert_chat_one_list",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> insertChat(Chat chat) throws ParseException{
		Map<String, Object> map=new HashMap<>();
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month=cal.get(Calendar.MONTH)+1;
		int day=cal.get(Calendar.DATE);
		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		cal.set(year,month,day,00,00,00);
		String chat_date =cal.get(Calendar.YEAR)+"-"+cal.get(Calendar.MONTH)+"-"+cal.get(Calendar.DAY_OF_MONTH)+" 00:00:00";
		final Date chat_datetime = sdf.parse(chat_date);
		final long chat_time = chat_datetime.getTime();
		chat.setChat_time(chat_time);
		chatImp.insert_chat_one_list(chat);
		map.put("msg", "success");
		return map ;
	}
	/*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*查询*/
	@RequestMapping(value="/select_chat_one_list",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> selectChatList(int chat_tag){
		Map<String, Object> map=new HashMap<>();
		List<Chat> list_chat_one= (List<Chat>)chatImp.select_chat_one_list(chat_tag);
		map.put("list_chat_one", list_chat_one);
		return map ;
	}
}
