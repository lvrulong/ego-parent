package com.ego.manager.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.commons.pojo.EasyUIDataGrid;
import com.ego.commons.utils.JsonUtils;
import com.ego.dubbo.service.TbContentDubboService;
import com.ego.manager.service.TbContentService;
import com.ego.pojo.TbContent;
import com.ego.redis.dao.JedisDao;

@Service
public class TbContentServiceImpl implements TbContentService{

	@Reference
	private TbContentDubboService tbContentService;
	
	@Resource
	private JedisDao jedisDao;
	
	@Value("${redis.bigpic.key}")
	private String key;
	
	@Override
	public EasyUIDataGrid selContentByPage(long categoryId, int page, int rows) {
		return tbContentService.selContentByPage(categoryId, page, rows);
	}

	@Override
	public int save(TbContent tbContent) {
		Date date = new Date();
		tbContent.setCreated(date);
		tbContent.setUpdated(date);
		
		int index = tbContentService.insContent(tbContent);
		
		//判断redis中是否有缓存数据
		if(jedisDao.exists(key)) {
			String value = jedisDao.get(key);
			if(value != null && !value.equals("")) {
				List<HashMap> list = JsonUtils.jsonToList(value, HashMap.class);
				

				HashMap<String, Object> map = new HashMap<>();
				map.put("srcB", tbContent.getPic2());
				map.put("height", 240);
				map.put("alt", "对不起，加载图片失败");
				map.put("width", 670);
				map.put("src", tbContent.getPic());
				map.put("widthB", 550);
				map.put("href", tbContent.getUrl());
				map.put("heightB", 240);
				
				if(list.size()==6) {
					list.remove(5);
				}
				
				list.add(0, map);
				jedisDao.set(key, JsonUtils.objectToJson(list));

			}
		}
		return index;
	}
	
	
}
