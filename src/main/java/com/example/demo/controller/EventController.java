package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Event;
import com.example.demo.entity.EventUser;
import com.example.demo.service.EventService;
import com.example.demo.service.EventUserService;
import com.example.demo.service.UserService;

@Controller
@RequestMapping({"/", ""})
public class EventController {
	@Autowired
	EventService eventService;

	@Autowired
	EventUserService eventUserService;
	
	@Autowired
	UserService userService;

	@GetMapping(value = {"/", ""})
	public String list(Model model) {
		List<Event> events = eventService.findAll();
		model.addAttribute("events", events);
		return "home/home";
	}
	
	@GetMapping(value = "/events/view/{id}")
	public String view(@PathVariable Integer id, Model model) {
		try {
			//イベント詳細
			Event event = eventService.findById(id);
			model.addAttribute("event", event);
			
			//イベント参加者
			int eventId = event.getId();
			List<EventUser> eventuserAll = eventUserService.findByEventId(eventId);
			
			//参加者数
			int size = eventuserAll.size();
			model.addAttribute("size", size);			
		} catch (Exception e) {
		}
		return "home/detail";
	}
}