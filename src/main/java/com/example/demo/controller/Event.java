package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.service.EventService;

@Controller
@RequestMapping("/enkai")
public class Event {
	@Autowired
	EventService eventservice;
	
	@GetMapping(value = {"/", ""})
	public String list(Event event, Model model) {
		model.addAttribute("event", event);
		return "home/home";
	}
}
