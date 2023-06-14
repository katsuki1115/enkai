package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.common.DataNotFoundException;
import com.example.demo.dao.EventUserDao;
import com.example.demo.entity.Event;
import com.example.demo.entity.EventUser;
import com.example.demo.entity.User;

@Service
public class EventUserService implements BaseService<EventUser> {
	@Autowired
	private EventUserDao dao;

	@Override
	public List<EventUser> findAll() {
		return dao.findAll();
	}

	@Override
	public EventUser findById(Integer id) throws DataNotFoundException {
		return dao.findById(id);
	}

	@Override
	public void save(EventUser eventuser) {
		dao.save(eventuser);
	}

	@Override
	public void deleteById(Integer id) {
		dao.deleteById(id);
	}

	public List<EventUser> findByEventId(Integer id) throws DataNotFoundException {
		return dao.findByEventId(id);
	}

	public void deleteByUserAndEvent(User user, Event event) throws DataNotFoundException{
		dao.deleteByUserAndEvent(user, event);
	}
	
	public EventUser findByUser(User editUser) throws DataNotFoundException {
		return dao.findByUser(editUser);
	}

}
