package com.example.demo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.common.DataNotFoundException;
import com.example.demo.entity.Chat;
import com.example.demo.repository.ChatRepository;

@Repository
public class ChatDao implements BaseDao<Chat> {
	@Autowired
	ChatRepository repository;
	
	@Override
	public List<Chat> findAll() {
		// TODO 自動生成されたメソッド・スタブ
		return repository.findAll();
	}

	@Override
	public Chat findById(Integer id) throws DataNotFoundException {
		// TODO 自動生成されたメソッド・スタブ
		return this.repository.findById(id).orElseThrow(() -> new DataNotFoundException());
	}

	@Override
	public void save(Chat chat) {
		// TODO 自動生成されたメソッド・スタブ
		this.repository.save(chat);
	}

	@Override
	public void deleteById(Integer id) {
		// TODO 自動生成されたメソッド・スタブ
		try {
			Chat chat = this.findById(id);
			this.repository.deleteById(chat.getId());
		} catch (DataNotFoundException e) {
			System.out.println("no data");
		}
	}

}
