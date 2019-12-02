package com.ego.dubbo.service;

import java.util.List;

import com.ego.pojo.TbContentCategory;

public interface TbContentCategoryDubboService {
	
	/**
	 * 根据父id查询所有子类目
	 */
	List<TbContentCategory> selByPid(long pid);
}
