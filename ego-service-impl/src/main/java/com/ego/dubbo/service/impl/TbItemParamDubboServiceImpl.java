package com.ego.dubbo.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.ego.commons.pojo.EasyUIDataGrid;
import com.ego.dubbo.service.TbItemParamDubboService;
import com.ego.mapper.TbItemParamMapper;
import com.ego.pojo.TbItemParam;
import com.ego.pojo.TbItemParamExample;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

public class TbItemParamDubboServiceImpl implements TbItemParamDubboService {
	
	@Resource
	private TbItemParamMapper tbItemParamMapper;
	
	@Override
	public EasyUIDataGrid showPage(int page, int rows) {
		//先设置分页条件
		PageHelper.startPage(page,rows);
		
		//设置查询的SQL语句
		//XXXXExample() 设置了什么，相当于在sql中where从句中添加条件
		//如果表中有一个或者一个一上的列是text类型，生成的方法xxxxxxWithBLOBs
		//若果使用xxxxxxWithBLOBs() 查询结果中包含带有text类的列，如果没有使用WithBLOBs()方法中，带有text类型，则取出该列为null
		List<TbItemParam> list = tbItemParamMapper.selectByExampleWithBLOBs(new TbItemParamExample());
		
		//根据程序员自己编写的SQL语句结合分月插件产生最终结果，封装到PageInfo
		PageInfo<TbItemParam> pi = new PageInfo<>(list);
		
		//设置方法返回结果
		EasyUIDataGrid dataGrid = new EasyUIDataGrid();
		dataGrid.setRows(pi.getList());
		dataGrid.setTotal(pi.getTotal());
		
		return dataGrid;
	}

}
