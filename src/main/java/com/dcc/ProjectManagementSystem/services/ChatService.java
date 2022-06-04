package com.dcc.ProjectManagementSystem.services;

import java.util.List;

import com.dcc.ProjectManagementSystem.entity.Chat;

public interface ChatService {
	List<Chat> select_chat_one_list(int chat_tag);
	int insert_chat_one_list(Chat chat);
}
