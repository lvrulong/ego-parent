package com.ego.manage.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ego.commons.pojo.EasyUiTree;
import com.ego.manager.service.TbContentCategoryService;

@Controller
public class TbContentCategoryController {
	
	@Resource
	private TbContentCategoryService tbContentCategoryService;
	
	@RequestMapping("content/category/list")
	@ResponseBody
	public List<EasyUiTree> showCategory(@RequestParam(defaultValue="0")long id){
		return tbContentCategoryService.showCategory(id);
	}
	
}
