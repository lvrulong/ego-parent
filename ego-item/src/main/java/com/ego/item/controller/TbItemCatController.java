package com.ego.item.controller;

import javax.annotation.Resource;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ego.item.pojo.PortalMenu;
import com.ego.item.service.TbItemCatService;

@Controller
public class TbItemCatController {

	@Resource
	private TbItemCatService tbItemCatService;
	
	/**
	 * 返回jsonp数据格式,返回所有菜单
	 * jsonp性能较低，只适用于局部使用，并且使用了递归也影响性能
	 * @param callback
	 * @return
	 */
	@RequestMapping("/rest/itemcat/all")
	@ResponseBody
	public MappingJacksonValue showMenu(String callback){
		PortalMenu portalMenu = tbItemCatService.showCatMenu();
		MappingJacksonValue mjv = new MappingJacksonValue(portalMenu);
		mjv.setJsonpFunction(callback);
		return mjv;
	}
	
}
