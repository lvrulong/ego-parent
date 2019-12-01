package com.ego.item.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.dubbo.service.TbItemCatDubboService;
import com.ego.item.pojo.PortalMenu;
import com.ego.item.pojo.PortalMenuNode;
import com.ego.item.service.TbItemCatService;
import com.ego.pojo.TbItemCat;

@Service
public class TbItemCatServiceImpl implements TbItemCatService{

	@Reference
	private TbItemCatDubboService ibItemCatDubboService;
	
	@Override
	public PortalMenu showCatMenu() {
		PortalMenu portalMenu = new PortalMenu();
		//查询所有的一级菜单
		List<TbItemCat> list = ibItemCatDubboService.show(0);
		List<Object> data = selAllMenu(list);
		portalMenu.setData(data);
		return portalMenu;
	}
	
	/**
	 * 最终返回结果所有查询到的结果
	 */
	public List<Object> selAllMenu(List<TbItemCat> list){
		List<Object> listNode = new ArrayList<>();
		for (TbItemCat tbItemCat : list) {
				if(tbItemCat.getIsParent()){
				PortalMenuNode pmd = new PortalMenuNode();
				pmd.setU("/products/"+ tbItemCat.getId() +".html");
				pmd.setN("<a href='/products/"+ tbItemCat.getId() +".html'>"+tbItemCat.getName()+"</a>");
				pmd.setI(selAllMenu(ibItemCatDubboService.show(tbItemCat.getId())));
				listNode.add(pmd);
			}else{
				listNode.add("/products/"+tbItemCat.getId()+".html|"+tbItemCat.getName());
			}
		}
		return listNode;
	}

}
