package com.ego.dubbo.service;

import java.util.List;

import com.ego.commons.pojo.EasyUIDataGrid;
import com.ego.pojo.TbItem;
import com.ego.pojo.TbItemDesc;
import com.ego.pojo.TbItemParamItem;

public interface TbItemDubboService {
	
	/**
	 * 商品分页查询
	 * @param page
	 * @param rows
	 * @return
	 */
	EasyUIDataGrid  show(int page, int rows);
	
	/**
	 * 根据id修改状态
	 * @param tbItem
	 * @return
	 */
	int updateItemByPrimaryKeySelective(TbItem tbItem);
	
	/**
	 * 商品新增
	 * @param tbItem
	 * @return
	 */
	int insTbItem(TbItem tbItem);
	
	/**
	 * 添加商品包含商品描述
	 * @param tbItem
	 * @param desc
	 * @return
	 */
	int insTbItemDesc(TbItem tbItem, TbItemDesc desc, TbItemParamItem paramItem) throws Exception;
	
	/**
	 * 通过状态查询全部可用数据
	 * @return
	 */
	List<TbItem> selAllByStatus(byte status);
}
