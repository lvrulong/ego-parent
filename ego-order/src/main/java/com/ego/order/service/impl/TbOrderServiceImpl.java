package com.ego.order.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ego.commons.pojo.EgoResult;
import com.ego.commons.pojo.TbItemChild;
import com.ego.commons.utils.CookieUtils;
import com.ego.commons.utils.HttpClientUtil;
import com.ego.commons.utils.JsonUtils;
import com.ego.order.service.TbOrderService;
import com.ego.redis.dao.JedisDao;

@Service
public class TbOrderServiceImpl implements TbOrderService {

	@Resource
	private JedisDao jedisDao;

	@Value("${cart.key}")
	private String cartKey;

	@Value("${passport.url}")
	private String passportUrl;

	@Override
	public List<TbItemChild> showOrderCart(List<Long> ids, HttpServletRequest request) {
		String token = CookieUtils.getCookieValue(request, "TT_TOKEN");
		String result = HttpClientUtil.doPost(passportUrl + token);
		EgoResult er = JsonUtils.jsonToPojo(result, EgoResult.class);
		String key = cartKey + ((LinkedHashMap) er.getData()).get("username");
		String json = jedisDao.get(key);
		List<TbItemChild> list = JsonUtils.jsonToList(json, TbItemChild.class);
		List<TbItemChild> listNew = new ArrayList<>();
		for (TbItemChild child : list) {
			for (Long id : ids) {
				if ((long) child.getId() == (long) id) {
					listNew.add(child);
				}
			}
		}
		return listNew;
	}

}
