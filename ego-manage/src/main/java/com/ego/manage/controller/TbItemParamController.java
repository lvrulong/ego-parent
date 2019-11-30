package com.ego.manage.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ego.commons.pojo.EasyUIDataGrid;
import com.ego.commons.pojo.EgoResult;
import com.ego.manager.service.TbItemParamService;
import com.ego.pojo.TbItemParam;

@Controller
public class TbItemParamController {
	
	@Resource
	private TbItemParamService tbItemParamService;
	
	/**
	 * 规格参数-分页显示
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("item/param/list")
	@ResponseBody
	public EasyUIDataGrid showPage(int page, int rows) {
		return tbItemParamService.showPage(page, rows);
	}
	
	/**
	 * 规格参数-批量删除
	 * @param ids
	 * @return
	 */
	@RequestMapping("item/param/delete")
	@ResponseBody
	public EgoResult delByIds(String ids) {
		EgoResult er = new EgoResult();
		try {
			int index = tbItemParamService.delByIds(ids);
			if(index==1) {
				er.setStatus(200);
			}
		} catch (Exception e) {
			e.printStackTrace();
			er.setData(e.getMessage());
		}
		return er;
	}
	
	/**
	 * 点击商品类目按钮显示添加分组按钮
	 * 判断类目是否已经添加模板
	 * @param catId
	 * @return
	 */
	@RequestMapping("item/param/query/itemcatid/{catId}")
	@ResponseBody
	public EgoResult show(@PathVariable long catId) {
		return tbItemParamService.showParam(catId);
	}
	
	
	/**
	 * 新增商品类目
	 * @param paramData
	 * @param catId
	 * @return
	 */
	@RequestMapping("item/param/save/{catId}")
	@ResponseBody
	public EgoResult save(TbItemParam param, @PathVariable long catId) {
		param.setItemCatId(catId);
		return tbItemParamService.save(param);
	}
}
