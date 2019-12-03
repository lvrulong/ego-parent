package com.ego.manage.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ego.commons.pojo.EasyUIDataGrid;
import com.ego.commons.pojo.EgoResult;
import com.ego.manager.service.TbContentService;
import com.ego.pojo.TbContent;

@Controller
public class TbContentController {

	@Resource
	private TbContentService tbContentService;

	@RequestMapping("content/query/list")
	@ResponseBody
	public EasyUIDataGrid showConten(long categoryId,int page,int rows) {
		return tbContentService.selContentByPage(categoryId, page, rows);
	}
	
	@RequestMapping("content/save")
	@ResponseBody
	public EgoResult save(TbContent tbContent) {
		return tbContentService.save(tbContent);
	}
}
