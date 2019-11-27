package com.ego.dubbo.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.ego.commons.pojo.EasyUIDataGrid;
import com.ego.dubbo.service.TbItemDubboService;
import com.ego.mapper.TbItemMapper;
import com.ego.pojo.TbItem;
import com.ego.pojo.TbItemExample;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

//此类交给dubbo容器管理，不需要加@Service
public class TbItemDubboServiceImpl implements TbItemDubboService{
	
	@Resource
	private TbItemMapper tbItemMapper;
	
	@Override
	public EasyUIDataGrid show(int page, int rows) {
		
		//分页代码
		//设置分页条件
		PageHelper.startPage(page,rows);
		
		//查询全部
		List<TbItem> list = tbItemMapper.selectByExample(new TbItemExample());
		

		
		PageInfo<TbItem> pi = new PageInfo<>(list);
		
		//放入到实体类
		EasyUIDataGrid datagrid = new EasyUIDataGrid();
		datagrid.setRows(pi.getList());
		datagrid.setTotal(pi.getTotal());
		
		return datagrid;
	}

	@Override
	public int updateItemByPrimaryKeySelective(TbItem tbItem) {
		return tbItemMapper.updateByPrimaryKeySelective(tbItem);
	}

}
