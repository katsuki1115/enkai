package com.example.demo.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.common.FlashData;
import com.example.demo.entity.Chat;
import com.example.demo.entity.Event;
import com.example.demo.entity.EventUser;
import com.example.demo.entity.User;
import com.example.demo.service.BaseService;
import com.example.demo.service.ChatService;
import com.example.demo.service.EventService;
import com.example.demo.service.EventUserService;
import com.example.demo.service.UserService;

@Controller
@RequestMapping("/admin/chats")
public class AdminChatsController {
	@Autowired
	EventService eventService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	EventUserService eventUserService;
	
	@Autowired
	ChatService chatService;

	@Autowired
	BaseService<EventUser> eventuserService;
	
	@GetMapping(value = "/talk/{eventId}")
	public String talk(@PathVariable Integer eventId, Model model) {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		User editUser;
		try {
			editUser = userService.findByEmail(email);//ログインユーザ
			//イベントを送る
			Event event = eventService.findById(eventId);
			model.addAttribute("event", event);
			
			//空のchatオブジェクトにイベント情報をのせて送る
			Chat chat = new Chat();
			chat.setUser(editUser);
			chat.setEvent(event);
			model.addAttribute("chat", chat);
			
			List<Chat> chats = chatService.findAll();
			model.addAttribute("chats", chats);
		} catch (Exception e) {
		}
		return "admin/chats/talk";
	}
	
	@PostMapping(value = "/create/{eventId}")
	public String register(@PathVariable Integer eventId, Chat chat, BindingResult result, Model model, RedirectAttributes ra) {
		FlashData flash;
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		User editUser;
		try {
			if (result.hasErrors()) {
				model.addAttribute("chat", chat);
				return "admin/chats/create";
			}
			editUser = userService.findByEmail(email);//ログインユーザ
			Event event = eventService.findById(eventId);
			
			chat.setEvent(event);
			chat.setUser(editUser);
			//新規登録
			chatService.save(chat);
			flash = new FlashData().success("新規作成しました");
		} catch (Exception e) {
			flash = new FlashData().danger("処理中にエラーが発生しました");
		}
		ra.addFlashAttribute("flash", flash);
		return "redirect:/admin/chats/talk/" + eventId;
	}
}
