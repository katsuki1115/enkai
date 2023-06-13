package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Event;
import com.example.demo.entity.EventUser;
import com.example.demo.entity.User;

import jakarta.transaction.Transactional;

public interface EventUserRepository extends JpaRepository<EventUser, Integer> {
	public List<EventUser> findByEventId(Integer id);
	
	@Transactional
	public void deleteByUserAndEvent(User user, Event event);
}
