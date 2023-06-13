package com.example.demo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.common.DataNotFoundException;
import com.example.demo.entity.Event;
import com.example.demo.entity.User;
import com.example.demo.repository.EventRepository;

@Repository
public class EventDao implements BaseDao<Event>{
	@Autowired
	EventRepository repository;
	
	@Override
	public List<Event> findAll() {
		// TODO 自動生成されたメソッド・スタブ
		return repository.findAll();
	}

	@Override
	public Event findById(Integer id) throws DataNotFoundException {
		// TODO 自動生成されたメソッド・スタブ
		return this.repository.findById(id).orElseThrow(() -> new DataNotFoundException());
	}

	@Override
	public void save(Event event) {
		// TODO 自動生成されたメソッド・スタブ
		this.repository.save(event);
	}

	@Override
	public void deleteById(Integer id) {
		// TODO 自動生成されたメソッド・スタブ
		try {
			Event event = this.findById(id);
			this.repository.deleteById(event.getId());
		} catch (DataNotFoundException e) {
			System.out.println("no data");
		}
	}
	
	public List<Event> findByUser(User editUser) throws DataNotFoundException {
		List<Event> event = this.repository.findByUser(editUser);
		if(event == null) {
			throw new DataNotFoundException();
		}
		return event;
	}
	
	

}
