package com.dcc.ProjectManagementSystem.serviceImp;

import java.util.List;

import com.dcc.ProjectManagementSystem.mapper.ChatMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dcc.ProjectManagementSystem.entity.Chat;
import com.dcc.ProjectManagementSystem.services.ChatService;

@Service
@Transactional
public class ChatImp implements ChatService {
	@Autowired
	protected ChatMapper chatMapper;
	@Override
	public List<Chat> select_chat_one_list(int chat_tag) {
		// TODO Auto-generated method stub
		return chatMapper.select_chat_one_list(chat_tag);
	}
	@Override
	public int insert_chat_one_list(Chat chat) {
		// TODO Auto-generated method stub
		return chatMapper.insert_chat_one_list(chat);
	}
	
}
