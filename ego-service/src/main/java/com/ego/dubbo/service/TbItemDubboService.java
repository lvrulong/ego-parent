package com.ego.dubbo.service;

import com.ego.commons.pojo.EasyUIDataGrid;
import com.ego.pojo.TbItem;

public interface TbItemDubboService {
	
	/**
	 * 商品分页查询
	 * @param page
	 * @param rows
	 * @return
	 */
	EasyUIDataGrid  show(int page, int rows);
	
	
	int updateItemByPrimaryKeySelective(TbItem tbItem);
	
}
