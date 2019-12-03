package com.ego.manager.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.commons.pojo.EasyUiTree;
import com.ego.commons.pojo.EgoResult;
import com.ego.commons.utils.IDUtils;
import com.ego.dubbo.service.TbContentCategoryDubboService;
import com.ego.manager.service.TbContentCategoryService;
import com.ego.pojo.TbContentCategory;

@Service
public class TbContentCategoryServiceImpl implements TbContentCategoryService{
	
	@Reference
	private TbContentCategoryDubboService tbContentCategoryDubboService;
	
	@Override
	public List<EasyUiTree> showCategory(long id) {
		List<EasyUiTree> listTree = new ArrayList<>();  
		List<TbContentCategory> list = tbContentCategoryDubboService.selByPid(id);
		for (TbContentCategory category : list) {
			EasyUiTree tree = new EasyUiTree();
			tree.setId(category.getId());
			tree.setText(category.getName());
			tree.setState(category.getIsParent() ? "closed" : "open");
			listTree.add(tree);
		}
		return listTree;
	}

	@Override
	public EgoResult create(TbContentCategory cate) {
		EgoResult er = new EgoResult();
		//判断当前节点名称是否已经存在
		List<TbContentCategory> children = tbContentCategoryDubboService.selByPid(cate.getParentId());
		for (TbContentCategory child : children) {
			if(child.getName().equals(cate.getName())) {
				er.setData("改分类名称已存在");
				return er;
			}
		}
		
		Date date = new Date();
		cate.setCreated(date);
		cate.setUpdated(date);
		cate.setStatus(1);
		cate.setSortOrder(1);
		cate.setIsParent(false);
		long id = IDUtils.genItemId();
		cate.setId(id);
		int index = tbContentCategoryDubboService.insTbContenCategory(cate);
		if(index >0) {
			TbContentCategory parent = new TbContentCategory();
			parent.setId(cate.getParentId());
			parent.setIsParent(true);
			tbContentCategoryDubboService.updIsParentById(parent);
		}
		er.setStatus(200);
		Map<String,Long> map = new HashMap<>();
		map.put("id", id);
		er.setData(map);
		return er;
	}

	@Override
	public EgoResult update(TbContentCategory cate) {
		EgoResult er = new EgoResult();
		//查询当前节点信息
		TbContentCategory cateSelect = tbContentCategoryDubboService.selById(cate.getId());
		//判断当前节点名称是否已经存在
		List<TbContentCategory> children = tbContentCategoryDubboService.selByPid(cateSelect.getParentId());
		for (TbContentCategory child : children) {
			if(child.getName().equals(cate.getName())) {
				er.setData("改分类名称已存在");
				return er;
			}
		}
		
		Date date = new Date();
		cate.setUpdated(date);
		int index = tbContentCategoryDubboService.updIsParentById(cate);
		if(index>0) {
			er.setStatus(200);
		}
		return er;
	}

	@Override
	public EgoResult delete(TbContentCategory cate) {
		EgoResult er = new EgoResult();
		cate.setStatus(2);
		int index = tbContentCategoryDubboService.updIsParentById(cate);
		if(index>0) {
			//查询当前节点信息
			TbContentCategory curr = tbContentCategoryDubboService.selById(cate.getId());
			List<TbContentCategory> children = tbContentCategoryDubboService.selByPid(curr.getParentId());
			if(children == null || children.size()==0) {
				TbContentCategory parent = tbContentCategoryDubboService.selById(curr.getParentId());
				parent.setIsParent(false);
				tbContentCategoryDubboService.updIsParentById(parent);
			}
			er.setStatus(200);
		}
		return er;
	}

}
