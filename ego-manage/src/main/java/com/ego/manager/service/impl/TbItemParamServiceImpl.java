package com.ego.manager.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.commons.pojo.EasyUIDataGrid;
import com.ego.dubbo.service.TbItemCatDubboService;
import com.ego.dubbo.service.TbItemParamDubboService;
import com.ego.manage.pojo.TbItemParamChild;
import com.ego.manager.service.TbItemParamService;
import com.ego.pojo.TbItemParam;

@Service
public class TbItemParamServiceImpl implements TbItemParamService  {
	
	@Reference
	private TbItemParamDubboService tbItemParamDubboService;
	
	@Reference
	private TbItemCatDubboService tbItemCatDubboService;
	
	@Override
	public EasyUIDataGrid showPage(int page, int rows) {
		EasyUIDataGrid dataGrid = tbItemParamDubboService.showPage(page, rows);
		List<TbItemParam> list = (List<TbItemParam>) dataGrid.getRows();
		ArrayList<TbItemParamChild> listChild = new ArrayList<TbItemParamChild>();
		for (TbItemParam param : list) {
			TbItemParamChild child = new TbItemParamChild();
			child.setCreated(param.getCreated());
			child.setUpdated(param.getUpdated());
			child.setId(param.getId());
			child.setItemCatId(param.getItemCatId());
			child.setParamData(param.getParamData());
			child.setItemCatName(tbItemCatDubboService.selById(param.getItemCatId()).getName());
			
			listChild.add(child);
		}
		dataGrid.setRows(listChild);
		return dataGrid;
	}
}
