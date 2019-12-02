package com.ego.manager.service;

import java.util.List;

import com.ego.commons.pojo.EasyUiTree;
import com.ego.commons.pojo.EgoResult;
import com.ego.pojo.TbContentCategory;

public interface TbContentCategoryService {
	/**
	 * 返回所有的内容类目，转换为符合easyui的格式
	 * @return
	 */
	List<EasyUiTree> showCategory(long id);
	
	/**
	 *  新增
	 * @param cate
	 * @return
	 */
	EgoResult create(TbContentCategory cate);
}
