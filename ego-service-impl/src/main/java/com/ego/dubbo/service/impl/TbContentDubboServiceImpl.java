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
	public int insContent(TbContent tbContent) {
		return tbContentMapper.insertSelective(tbContent);
	}

	@Override
	public List<TbContent> selByCount(int count, boolean isSort) {
		TbContentExample example = new TbContentExample();
		if(isSort) {
			example.setOrderByClause("updated desc");
		}
		if(count!=0) {
			PageHelper.startPage(1, count);
			List<TbContent> list = tbContentMapper.selectByExample(example);
			PageInfo<TbContent> pi = new PageInfo<>(list);
			return pi.getList();
		}else {
			return tbContentMapper.selectByExampleWithBLOBs(example);
		}
	}

}
