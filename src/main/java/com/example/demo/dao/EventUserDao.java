package com.example.demo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.common.DataNotFoundException;
import com.example.demo.entity.Event;
import com.example.demo.entity.EventUser;
import com.example.demo.entity.User;
import com.example.demo.repository.EventUserRepository;

@Repository
public class EventUserDao implements BaseDao<EventUser> {
	@Autowired
	EventUserRepository repository;

	@Override
	public List<EventUser> findAll() {
		return repository.findAll();
	}

	@Override
	public EventUser findById(Integer id) throws DataNotFoundException {
		return this.repository.findById(id).orElseThrow(() -> new DataNotFoundException());
	}

	@Override
	public void save(EventUser eventuser) {
		this.repository.save(eventuser);
	}

	@Override
	public void deleteById(Integer id) {
		try {
			EventUser eventuser = this.findById(id);
			this.repository.deleteById(eventuser.getId());
		} catch (DataNotFoundException e) {
			System.out.println("no data");
		}
	}

	public List<EventUser> findByEventId(Integer id) throws DataNotFoundException {
		List<EventUser> eventuser = this.repository.findByEventId(id);
		if (eventuser == null) {
			throw new DataNotFoundException();
		}
		return eventuser;
	}

	public void deleteByUserAndEvent(User user, Event event) throws DataNotFoundException {
		this.repository.deleteByUserAndEvent(user, event);
	}

	public EventUser findByUser(User editUser) throws DataNotFoundException{
		EventUser eventuser = this.repository.findByUser(editUser);
		if(eventuser == null) {
			throw new DataNotFoundException();
		}
		return eventuser;
	}
}
