package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.EventUser;

public interface EventUserRepository extends JpaRepository<EventUser, Integer> {
	public List<EventUser> findByEventId(Integer id);
	public void deleteByUserIdAndEventId(Integer UserId, Integer EventId);
	//public void deleteByUserAndEvent(Integer User, Integer Event);
}
