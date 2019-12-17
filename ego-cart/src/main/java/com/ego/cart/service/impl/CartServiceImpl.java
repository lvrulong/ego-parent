package com.ego.cart.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.cart.service.CartService;
import com.ego.commons.pojo.EgoResult;
import com.ego.commons.pojo.TbItemChild;
import com.ego.commons.utils.CookieUtils;
import com.ego.commons.utils.HttpClientUtil;
import com.ego.commons.utils.JsonUtils;
import com.ego.dubbo.service.TbItemDubboService;
import com.ego.pojo.TbItem;
import com.ego.redis.dao.JedisDao;

@Service
public class CartServiceImpl implements CartService {

	@Resource
	private JedisDao jedisDao;

	@Reference
	private TbItemDubboService tbItemDubboService;

	@Value("${passport.url}")
	private String passportUrl;

	@Value("${cart.key}")
	private String cartKey;

	@Override
	public void addCart(long id, int num, HttpServletRequest request) {
		List<TbItemChild> list = new ArrayList<>();
		// 获取用户信息
		String token = CookieUtils.getCookieValue(request, "TT_TOKEN");
		String jsonUser = HttpClientUtil.doPost(passportUrl + token);
		EgoResult er = JsonUtils.jsonToPojo(jsonUser, EgoResult.class);
		// redis中的key
		String key = cartKey + ((LinkedHashMap) er.getData()).get("username");

		if (jedisDao.exists(key)) {
			String json = jedisDao.get(key);
			if (json != null && !json.equals("")) {
				list = JsonUtils.jsonToList(json, TbItemChild.class);

				for (TbItemChild tbItemChild : list) {
					if ((long) tbItemChild.getId() == id) {
						// 购物车中存在该商品
						tbItemChild.setNum(tbItemChild.getNum() + num);
						jedisDao.set(key, JsonUtils.objectToJson(list));
						return;
					}
				}
				// 重新添加到redis中
				// 购物车中没有改商品
				// if (isExists) {
				// // 购物车中没有这个商品
				// TbItem item = tbItemDubboService.selById(id);
				// TbItemChild child = new TbItemChild();
				// child.setId(item.getId());
				// child.setTitle(item.getTitle());
				// child.setImages(item.getImage() == null || item.getImage().equals("") ? new
				// String[1]
				// : item.getImage().split(","));
				// child.setNum(num);
				// child.setPrice(item.getPrice());
				//
				// // 可以存放多个商品信息.value应该是个集合
				// // 如果商品已经存在修改数量
				// // 如果商品没有存在，把商品对象放入到集合中
				// list.add(child);
				// jedisDao.set(key, JsonUtils.objectToJson(list));
				// }

			}

		}

		// redis中的key对应的value是null或“不存在”
		// 购物车中没有这个商品
		TbItem item = tbItemDubboService.selById(id);
		TbItemChild child = new TbItemChild();
		child.setId(item.getId());
		child.setTitle(item.getTitle());
		child.setImages(
				item.getImage() == null || item.getImage().equals("") ? new String[1] : item.getImage().split(","));
		child.setNum(num);
		child.setPrice(item.getPrice());
		list.add(child);
		jedisDao.set(key, JsonUtils.objectToJson(list));

	}

	@Override
	public List<TbItemChild> showCart(HttpServletRequest request) {
		// 获取用户信息
		String token = CookieUtils.getCookieValue(request, "TT_TOKEN");
		System.out.println(passportUrl);
		String jsonUser = HttpClientUtil.doPost(passportUrl + token);
		EgoResult er = JsonUtils.jsonToPojo(jsonUser, EgoResult.class);
		String key = cartKey + ((LinkedHashMap) er.getData()).get("username");

		String json = jedisDao.get(key);
		return JsonUtils.jsonToList(json, TbItemChild.class);
	}

	@Override
	public EgoResult update(long id, int num, HttpServletRequest request) {
		// 获取用户信息
		String token = CookieUtils.getCookieValue(request, "TT_TOKEN");
		System.out.println(passportUrl);
		String jsonUser = HttpClientUtil.doPost(passportUrl + token);
		EgoResult er = JsonUtils.jsonToPojo(jsonUser, EgoResult.class);
		String key = cartKey + ((LinkedHashMap) er.getData()).get("username");
		String json = jedisDao.get(key);
		List<TbItemChild> list = JsonUtils.jsonToList(json, TbItemChild.class);
		for (TbItemChild child : list) {
			if ((long) child.getId() == id) {
				child.setNum(num);
			}

		}
		String ok = jedisDao.set(key, JsonUtils.objectToJson(list));

		EgoResult egoResult = new EgoResult();
		if (ok.equals("OK")) {
			egoResult.setStatus(200);
		}
		return egoResult;
	}

	@Override
	public EgoResult delete(long id, HttpServletRequest request) {
		// 获取用户信息
		String token = CookieUtils.getCookieValue(request, "TT_TOKEN");
		System.out.println(passportUrl);
		String jsonUser = HttpClientUtil.doPost(passportUrl + token);
		EgoResult er = JsonUtils.jsonToPojo(jsonUser, EgoResult.class);
		String key = cartKey + ((LinkedHashMap) er.getData()).get("username");
		String json = jedisDao.get(key);
		List<TbItemChild> list = JsonUtils.jsonToList(json, TbItemChild.class);
		TbItemChild tbItemChild = null;
		for (TbItemChild child : list) {
			if ((long) child.getId() == id) {
				tbItemChild = child;
			}
		}
		list.remove(tbItemChild);
		String ok = jedisDao.set(key, JsonUtils.objectToJson(list));
		EgoResult egoResult = new EgoResult();
		if (ok.equals("OK")) {
			egoResult.setStatus(200);
		}
		return egoResult;
	}

}
