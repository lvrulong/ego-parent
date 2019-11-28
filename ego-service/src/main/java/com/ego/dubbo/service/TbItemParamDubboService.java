package com.ego.dubbo.service;

import com.ego.commons.pojo.EasyUIDataGrid;

public interface TbItemParamDubboService {

	/**
	 * 分页查询数据
	 * @param page
	 * @param rows
	 * @return 包含：当前页显示数据和总条数
	 */
	EasyUIDataGrid showPage(int page,int rows);
}
