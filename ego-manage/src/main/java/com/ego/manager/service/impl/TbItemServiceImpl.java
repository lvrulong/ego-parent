package com.ego.manager.service.impl;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.commons.pojo.EasyUIDataGrid;
import com.ego.commons.utils.IDUtils;
import com.ego.dubbo.service.TbItemDescDubboService;
import com.ego.dubbo.service.TbItemDubboService;
import com.ego.manager.service.TbItemService;
import com.ego.pojo.TbItem;
import com.ego.pojo.TbItemDesc;

@Service
public class TbItemServiceImpl implements TbItemService {

	// 从注册中心获取代理类对象,仅此处是dubbo的注解
	@Reference
	private TbItemDubboService tbItemDubboService;

	@Reference
	private TbItemDescDubboService tbItemDescDubboService;

	@Override
	public EasyUIDataGrid show(int page, int rows) {
		return tbItemDubboService.show(page, rows);
	}

	@Override
	public int update(String ids, byte status) {
		int index = 0;
		TbItem item = new TbItem();
		String[] idsStr = ids.split(",");
		for (String id : idsStr) {
			item.setId(Long.parseLong(id));
			item.setStatus(status);
			index += tbItemDubboService.updateItemByPrimaryKeySelective(item);
		}
		if (index == idsStr.length) {
			return 1;
		}
		return 0;
	}


	@Override
	public int save(TbItem tbItem, String desc) throws Exception {
		//不考虑事务回滚
//		long id = IDUtils.genItemId();
//		tbItem.setId(id);
//		Date date = new Date();
//		tbItem.setCreated(date);
//		tbItem.setUpdated(date);
//		tbItem.setStatus((byte) 1);
//		int index = tbItemDubboService.insTbItem(tbItem);
//		if (index > 0) {
//			TbItemDesc tbItemDesc = new TbItemDesc();
//			tbItemDesc.setItemId(id);
//			tbItemDesc.setItemDesc(desc);
//			tbItemDesc.setCreated(date);
//			tbItemDesc.setUpdated(date);
//			index += tbItemDescDubboService.insDesc(tbItemDesc);
//		}
//		if(index==2) {
//			return 1;
//		}
//		return 0;
		
		//调用dubbo中考虑事务回滚功能方法
		long id = IDUtils.genItemId();
		tbItem.setId(id);
		Date date = new Date();
		tbItem.setCreated(date);
		tbItem.setUpdated(date);
		tbItem.setStatus((byte) 1);
		
		TbItemDesc tbItemDesc = new TbItemDesc();
		tbItemDesc.setItemId(id);
		tbItemDesc.setItemDesc(desc);
		tbItemDesc.setCreated(date);
		tbItemDesc.setUpdated(date);
		int index = 0 ;

		index = tbItemDubboService.insTbItemDesc(tbItem, tbItemDesc);
		
		return index;
		
	}
}
