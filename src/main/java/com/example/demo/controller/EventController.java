package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Event;
import com.example.demo.service.EventService;

@Controller
@RequestMapping("/enkai")
public class EventController {
	@Autowired
	EventService eventService;
	
	@GetMapping(value = {"/", ""})
	public String list(Model model) {
		List<Event> events = eventService.findAll();
		model.addAttribute("events", events);
		return "home/home";
	}
	
}
