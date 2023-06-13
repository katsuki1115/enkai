package com.example.demo.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Event;
import com.example.demo.service.BaseService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	BaseService<Event> eventService;
	
	@GetMapping(value = {"/",""})
	public String list(Event event, Model model) {
		List<Event> events = eventService.findAll();
		model.addAttribute("events", events);
		
		//参加者数
		return "admin/admin_home";
	}
}
