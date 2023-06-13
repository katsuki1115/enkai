package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Event;
import com.example.demo.entity.User;

public interface EventRepository extends JpaRepository<Event, Integer>{
	public List<Event> findByUser(User editUser);
}
