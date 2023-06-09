package com.example.demo.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.common.FlashData;
import com.example.demo.entity.Event;
import com.example.demo.service.BaseService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/admin/events")
public class AdminEventController {
	@Autowired
	BaseService<Event> eventService;

	@GetMapping(value = "/mylist")
	public String mylist(Model model) {
		//全権取得	
		List<Event> events = eventService.findAll();
		model.addAttribute("events", events);
		return "admin/events/mylist";
	}

	/*
	 * 新規作成画面表示
	 */
	@GetMapping(value = "/create")
	public String form(Event event, Model model) {
		model.addAttribute("event", event);
		return "admin/events/create";
	}

	/*
	 * 新規登録
	 */
	@PostMapping(value = "/create")
	public String register(@Valid Event event, BindingResult result, Model model, RedirectAttributes ra) {
		FlashData flash;
		try {
			if (result.hasErrors()) {
				model.addAttribute("event", event);
				return "admin/events/create";
			}
			
			//新規登録
			eventService.save(event);
			flash = new FlashData().success("新規作成しました");
		} catch (Exception e) {
			flash = new FlashData().danger("処理中にエラーが発生しました");
		}
		ra.addFlashAttribute("flash", flash);
		return "redirect:/admin/events/mylist";
	}
	
	/*
	 * 表示
	 */
	@GetMapping(value = "/view/{id}")
	public String view(@PathVariable Integer id, Model model) {
		try {
			Event event = eventService.findById(id);
			model.addAttribute("event", event);
		} catch (Exception e) {
		}
		return "admin/events/view";
	}
	
	/*
	 * 編集画面表示
	 */
	@GetMapping(value = "/edit/{id}")
	public String edit(@PathVariable Integer id, Model model, RedirectAttributes ra) {
		try {
			//存在確認
			Event event = eventService.findById(id);
			model.addAttribute("event", event);
		} catch (Exception e) {
			FlashData flash = new FlashData().danger("該当データがありません");
			ra.addFlashAttribute("flash", flash);
			return "redirect:/admin/events";	
		}
		return "admin/events/edit";
	}
	
	/*
	 * 	更新
	 */
	@PostMapping(value = "/edit/{id}")
	public String update(@PathVariable Integer id, @Valid Event event, BindingResult result, Model model, RedirectAttributes ra) {
		FlashData flash;
		try {
			if(result.hasErrors()) {
				return "admin/events/edit";
			}
			eventService.findById(id);
			//新規登録
			eventService.save(event);
			flash = new FlashData().success("更新しました");
		} catch (Exception e) {
			flash  = new FlashData().danger("該当データがありません");
		}
		ra.addFlashAttribute("flash", flash);
		return "redirect:/admin/events/mylist";
	}
	
	/*
	 * 削除
	 */
	@GetMapping(value = "/delete/{id}")
	public String delete(@PathVariable Integer id,@Valid Event event, BindingResult result, Model model, RedirectAttributes ra) {
		FlashData flash;
		try {
			//削除
			eventService.deleteById(id);
			flash = new FlashData().success("削除しました");
		} catch (Exception e) {
			flash  = new FlashData().danger("削除できませんでした");
		}
		ra.addFlashAttribute("flash", flash);
		return "redirect:/admin/events/mylist";
	}
}
