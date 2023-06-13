package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.common.DataNotFoundException;
import com.example.demo.dao.EventUserDao;
import com.example.demo.entity.EventUser;

@Service
public class EventUserService implements BaseService<EventUser> {
	@Autowired
	private EventUserDao dao;

	@Override
	public List<EventUser> findAll() {
		// TODO 自動生成されたメソッド・スタブ
		return dao.findAll();
	}

	@Override
	public EventUser findById(Integer id) throws DataNotFoundException {
		// TODO 自動生成されたメソッド・スタブ
		return dao.findById(id);
	}

	@Override
	public void save(EventUser eventuser) {
		// TODO 自動生成されたメソッド・スタブ
		dao.save(eventuser);
	}

	@Override
	public void deleteById(Integer id) {
		// TODO 自動生成されたメソッド・スタブ
		dao.deleteById(id);
	}

	public List<EventUser> findByEventId(Integer id) throws DataNotFoundException {
		// TODO 自動生成されたメソッド・スタブ
		return dao.findByEventId(id);
	}

	public void deleteByUserIdAndEventId(Integer UserId, Integer EventId) throws DataNotFoundException{
		// TODO 自動生成されたメソッド・スタブ
		dao.deleteByUserIdAndEventId(UserId, EventId);
	}
	
//	public void deleteByUserAndEvent(Integer User, Integer Event) throws DataNotFoundException{
//		// TODO 自動生成されたメソッド・スタブ
//		dao.deleteByUserAndEvent(User, Event);
//	}

}
