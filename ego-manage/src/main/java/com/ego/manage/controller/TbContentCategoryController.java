package com.ego.manage.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ego.commons.pojo.EasyUiTree;
import com.ego.commons.pojo.EgoResult;
import com.ego.manager.service.TbContentCategoryService;
import com.ego.pojo.TbContentCategory;

@Controller
public class TbContentCategoryController {
	
	@Resource
	private TbContentCategoryService tbContentCategoryService;
	
	/**
	 * 查询类目
	 * @param id
	 * @return
	 */
	@RequestMapping("content/category/list")
	@ResponseBody
	public List<EasyUiTree> showCategory(@RequestParam(defaultValue="0")long id){
		return tbContentCategoryService.showCategory(id);
	}
	
	/**
	 * 新增内容类目
	 * @param cate
	 * @return
	 */
	@RequestMapping("content/category/create")
	@ResponseBody
	public EgoResult create(TbContentCategory cate) {
		return tbContentCategoryService.create(cate);
	}
	
	/**
	 * 重命名类目
	 * @param cate
	 * @return
	 */
	@RequestMapping("content/category/update")
	@ResponseBody
	public EgoResult update(TbContentCategory cate) {
		return tbContentCategoryService.update(cate);
	}
	
	@RequestMapping("content/category/delete/")
	@ResponseBody
	public EgoResult delete(TbContentCategory cate) {
		return tbContentCategoryService.delete(cate);
	}
}
