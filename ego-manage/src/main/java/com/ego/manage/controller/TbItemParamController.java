package com.ego.manage.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ego.commons.pojo.EasyUIDataGrid;
import com.ego.manager.service.TbItemParamService;

@Controller
public class TbItemParamController {
	
	@Resource
	private TbItemParamService tbItemParamService;
	
	@RequestMapping("item/param/list")
	@ResponseBody
	public EasyUIDataGrid showPage(int page, int rows) {
		return tbItemParamService.showPage(page, rows);
	}
}
