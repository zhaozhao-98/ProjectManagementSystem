package com.dcc.ProjectManagementSystem.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.dcc.ProjectManagementSystem.entity.Chat;

@Repository
public interface ChatMapper {
	List<Chat> select_chat_one_list(int chat_tag);
	int insert_chat_one_list(Chat chat);
}
