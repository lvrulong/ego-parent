package com.ego.manager.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.commons.pojo.EasyUiTree;
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

}
