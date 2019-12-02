package com.ego.dubbo.service;

import java.util.List;

import com.ego.pojo.TbContentCategory;

public interface TbContentCategoryDubboService {

	/**
	 * 根据父id查询所有子类目
	 */
	List<TbContentCategory> selByPid(long pid);

	/**
	 * 新增
	 * 
	 * @return
	 */
	int insTbContenCategory(TbContentCategory cate);

	/**
	 * 根据id修改isParent
	 * 
	 * @return
	 */

	int updIsParentById(TbContentCategory cate);
}
