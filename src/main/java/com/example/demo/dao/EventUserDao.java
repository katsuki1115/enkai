package com.example.demo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.common.DataNotFoundException;
import com.example.demo.entity.EventUser;
import com.example.demo.repository.EventUserRepository;

@Repository
public class EventUserDao implements BaseDao<EventUser> {
	@Autowired
	EventUserRepository repository;
	
	@Override
	public List<EventUser> findAll() {
		// TODO 自動生成されたメソッド・スタブ
		return repository.findAll();
	}

	@Override
	public EventUser findById(Integer id) throws DataNotFoundException {
		// TODO 自動生成されたメソッド・スタブ
		return this.repository.findById(id).orElseThrow(() -> new DataNotFoundException());
	}

	@Override
	public void save(EventUser eventuser) {
		// TODO 自動生成されたメソッド・スタブ
		this.repository.save(eventuser);
	}

	@Override
	public void deleteById(Integer id) {
		// TODO 自動生成されたメソッド・スタブ
		try {
			EventUser eventuser = this.findById(id);
			this.repository.deleteById(eventuser.getId());
		} catch (DataNotFoundException e) {
			System.out.println("no data");
		}
	}
	
	public EventUser findByEventId(Integer id) throws DataNotFoundException {
		EventUser eventuser = this.repository.findByEventId(id);
		if(eventuser == null) {
			throw new DataNotFoundException();
		}
		return eventuser;
	}

}
