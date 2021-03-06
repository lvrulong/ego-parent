package com.ego.dubbo.service;

import com.ego.pojo.TbItemDesc;

public interface TbItemDescDubboService {
	/**
	 * 新增
	 * @param itemDesc
	 * @return
	 */
	int insDesc(TbItemDesc itemDesc);
	
	/**
	 * 根据itemid查询
	 * @param itemid
	 * @return
	 */
	TbItemDesc selByItemid(long itemId);
}
