package com.example.demo.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.common.FlashData;
import com.example.demo.entity.Event;
import com.example.demo.entity.EventUser;
import com.example.demo.entity.User;
import com.example.demo.service.BaseService;
import com.example.demo.service.EventUserService;
import com.example.demo.service.UserService;

@Controller
@RequestMapping("/admin/eventusers")
public class EventUsersController {
	@Autowired
	BaseService<EventUser> eventuserService;

	@Autowired
	BaseService<Event> eventService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	EventUserService eventUserService;

	@GetMapping(value = "/create/{eventId}")
	public String register(@PathVariable Integer eventId, EventUser eventuser, Model model, BindingResult result, RedirectAttributes ra) {
		FlashData flash;
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		User editUser;
		try {
			if (result.hasErrors()) {
				return "admin/eventusers/create";
			}
			editUser = userService.findByEmail(email);
			Event event = eventService.findById(eventId);
			eventuser.setEvent(event);
			eventuser.setUser(editUser);
			//新規登録
			eventuserService.save(eventuser);
			flash = new FlashData().success("更新しました");
		} catch (Exception e) {
			flash = new FlashData().danger("該当データがありません");
		}
		ra.addFlashAttribute("flash", flash);
		return "redirect:/admin/events/view/" + eventId;
	}
	
	@GetMapping(value = "/delete/{id}")
	public String delete(@PathVariable Integer id, RedirectAttributes ra) {
		FlashData flash;
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		User editUser;
		try {
			editUser = userService.findByEmail(email);
			Event event = eventService.findById(id);
			
			//削除
			eventUserService.deleteByUserAndEvent(editUser, event);
			//eventUserService.deleteByUserAndEvent(editId, eventId);
			flash = new FlashData().success("削除しました");
		} catch (Exception e) {
			flash  = new FlashData().danger("削除できませんでした");
		}
		ra.addFlashAttribute("flash", flash);
		return "redirect:/admin/events/view/" + id;
	}
	
}
