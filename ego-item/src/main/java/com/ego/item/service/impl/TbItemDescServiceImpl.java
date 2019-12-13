package com.ego.item.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.commons.utils.JsonUtils;
import com.ego.dubbo.service.TbItemDescDubboService;
import com.ego.item.service.TbItemDescService;
import com.ego.redis.dao.JedisDao;

@Service
public class TbItemDescServiceImpl implements TbItemDescService {

	@Reference
	private TbItemDescDubboService tbItemDescDubboService;

	@Resource
	private JedisDao jedisDao;

	@Value("redis.desc.key")
	private String descKey;

	@Override
	public String showDesc(long itemId) {
		String key = descKey + itemId;
		if (jedisDao.exists(key)) {
			String json = jedisDao.get(key);
			if (json != null && !json.equals("")) {
				return json;
			}
		}
		String result = tbItemDescDubboService.selByItemid(itemId).getItemDesc();
		jedisDao.set(key, result);
		return result;
	}

}
