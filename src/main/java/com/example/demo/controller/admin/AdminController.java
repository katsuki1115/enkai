package com.example.demo.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Event;
import com.example.demo.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	UserService userService;
	
	@GetMapping(value = {"/",""})
	public String admin(Event event, Model model) {
		model.addAttribute("event", event);
		return "admin/admin_home";
	}
	
	@GetMapping(value = "/users/logout")
	public String logout() {
		return "redirect:enkai";
	}
}
