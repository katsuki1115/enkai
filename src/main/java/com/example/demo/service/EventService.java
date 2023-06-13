package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.common.DataNotFoundException;
import com.example.demo.dao.EventDao;
import com.example.demo.entity.Event;
import com.example.demo.entity.User;

@Service
public class EventService implements BaseService<Event> {
	@Autowired
	private EventDao dao;
	
	@Override
	public List<Event> findAll() {
		// TODO 自動生成されたメソッド・スタブ
		return dao.findAll();
	}

	@Override
	public Event findById(Integer id) throws DataNotFoundException {
		// TODO 自動生成されたメソッド・スタブ
		return dao.findById(id);
	}

	@Override
	public void save(Event event) {
		// TODO 自動生成されたメソッド・スタブ
		dao.save(event);
	}

	@Override
	public void deleteById(Integer id) {
		// TODO 自動生成されたメソッド・スタブ
		dao.deleteById(id);
	}

	public List<Event> findByUser(User editUser) throws DataNotFoundException {
		return dao.findByUser(editUser);
	}
}
