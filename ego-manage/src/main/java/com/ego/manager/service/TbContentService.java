package com.ego.manager.service;

import com.ego.commons.pojo.EasyUIDataGrid;
import com.ego.commons.pojo.EgoResult;
import com.ego.pojo.TbContent;

public interface TbContentService {
	
	/**
	 * 
	 * @param categoryId
	 * @param page
	 * @param rows
	 * @return
	 */
	EasyUIDataGrid selContentByPage(long categoryId, int page, int rows);
	
	/**
	 * 新增
	 * @param tbContent
	 * @return
	 */
	int save(TbContent tbContent);
}
