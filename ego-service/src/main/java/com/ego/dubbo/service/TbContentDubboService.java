package com.ego.dubbo.service;

import com.ego.commons.pojo.EasyUIDataGrid;
import com.ego.commons.pojo.EgoResult;
import com.ego.pojo.TbContent;

public interface TbContentDubboService {
	
	/**
	 * 分页查询
	 * @param categoryId
	 * @param page
	 * @param rows
	 * @return
	 */
	EasyUIDataGrid selContentByPage(long categoryId, int page, int rows);
	
	/**
	 * 内容新增
	 * @param tbContent
	 * @return
	 */
	EgoResult insContent(TbContent tbContent);
}