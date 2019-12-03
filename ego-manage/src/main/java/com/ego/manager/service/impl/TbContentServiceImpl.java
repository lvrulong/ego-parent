package com.ego.manager.service.impl;

import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.commons.pojo.EasyUIDataGrid;
import com.ego.commons.pojo.EgoResult;
import com.ego.dubbo.service.TbContentDubboService;
import com.ego.manager.service.TbContentService;
import com.ego.pojo.TbContent;

@Service
public class TbContentServiceImpl implements TbContentService{

	@Reference
	private TbContentDubboService tbContentService;
	
	@Override
	public EasyUIDataGrid selContentByPage(long categoryId, int page, int rows) {
		return tbContentService.selContentByPage(categoryId, page, rows);
	}

	@Override
	public EgoResult save(TbContent tbContent) {
		return tbContentService.insContent(tbContent);
	}
	
	
}
