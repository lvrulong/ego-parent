package com.ego.dubbo.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.ego.commons.pojo.EasyUIDataGrid;
import com.ego.commons.pojo.EgoResult;
import com.ego.dubbo.service.TbContentDubboService;
import com.ego.mapper.TbContentMapper;
import com.ego.pojo.TbContent;
import com.ego.pojo.TbContentExample;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

public class TbContentDubboServiceImpl implements TbContentDubboService{
	
	@Resource
	private TbContentMapper tbContentMapper;
	
	@Override
	public EasyUIDataGrid selContentByPage(long categoryId, int page, int rows) {
		EasyUIDataGrid dataGrid = new EasyUIDataGrid();
		PageHelper.startPage(page, rows);
		TbContentExample example = new TbContentExample();
		if(categoryId!=0) {
			example.createCriteria().andCategoryIdEqualTo(categoryId);
		}
		
		List<TbContent> list = tbContentMapper.selectByExampleWithBLOBs(example);
		
		PageInfo<TbContent> pi = new PageInfo<>(list);
		dataGrid.setRows(pi.getList());
		dataGrid.setTotal(pi.getTotal());
		
		return dataGrid;
	}

	@Override
	public EgoResult insContent(TbContent tbContent) {
		EgoResult er = new EgoResult();
		Date date = new Date();
		tbContent.setCreated(date);
		tbContent.setUpdated(date);
		int index = tbContentMapper.insertSelective(tbContent);
		if(index > 0) {
			er.setStatus(200);
		}
		return er;
	}

}
