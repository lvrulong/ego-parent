package com.ego.manager.service.impl;

import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.commons.pojo.EasyUIDataGrid;
import com.ego.dubbo.service.TbItemDubboService;
import com.ego.manager.service.TbItemService;
import com.ego.pojo.TbItem;

@Service
public class TbItemServiceImpl implements TbItemService {

	//从注册中心获取代理类对象,今此处是dubbo的注解
	@Reference
	private TbItemDubboService tbItemDubboService;
	
	@Override
	public EasyUIDataGrid show(int page, int rows) {
		return tbItemDubboService.show(page, rows);
	}

	
	@Override
	public int update(String ids, byte status) {
		int index = 0 ;
		TbItem item = new TbItem();
		String[] idsStr = ids.split(",");
		for (String id : idsStr) {
			item.setId(Long.parseLong(id));
			item.setStatus(status);
			index += tbItemDubboService.updateItemByPrimaryKeySelective(item);
		}
		if(index==idsStr.length){
			return 1;
		}
		return 0;
	}
}
