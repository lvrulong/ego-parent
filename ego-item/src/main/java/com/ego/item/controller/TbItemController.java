package com.ego.item.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ego.item.service.TbItemService;

@Controller
public class TbItemController {
	
	@Resource
	private TbItemService tbItemService;
	
	/**
	 * 显示商品详情
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping("item/{id}.html")
	public String showItemDetails(Model model,@PathVariable long id) {
		
		model.addAttribute("item", tbItemService.show(id));
		return "item";
	}
	
	
}
