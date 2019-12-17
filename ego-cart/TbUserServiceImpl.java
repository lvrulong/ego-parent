package com.ego.passport.service.impl;

import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.commons.pojo.EgoResult;
import com.ego.commons.utils.CookieUtils;
import com.ego.commons.utils.JsonUtils;
import com.ego.dubbo.service.TbUserDubboService;
import com.ego.passport.service.TbUserService;
import com.ego.pojo.TbUser;
import com.ego.redis.dao.JedisDao;

@Service
public class TbUserServiceImpl implements TbUserService {

	@Reference
	private TbUserDubboService tbUserDubboService;

	@Resource
	private JedisDao jedisDao;

	@Override
	public EgoResult login(TbUser user, HttpServletRequest request, HttpServletResponse response) {
		EgoResult er = new EgoResult();
		TbUser selectUser = tbUserDubboService.selByTbUser(user);
		if (selectUser != null) {
			er.setStatus(200);
			// 当用户登录成功后把用户信息放入到redis中
			String key = UUID.randomUUID().toString();
			jedisDao.set(key, JsonUtils.objectToJson(selectUser));
			jedisDao.expire(key, 60 * 60 * 24 * 7);
			// 产生cookie
			CookieUtils.setCookie(request, response, "TT_TOKEN", key, 60 * 60 * 24 * 7);
		} else {
			er.setStatus(500);
			er.setMsg("用户名和密码不正确");
		}
		return er;
	}

	@Override
	public EgoResult getUserInfoByToken(String token) {
		EgoResult er = new EgoResult();
		String json = jedisDao.get(token);
		if (json != null && !json.equals("")) {
			TbUser tbUser = JsonUtils.jsonToPojo(json, TbUser.class);
			//可以把密码清空
			tbUser.setPassword(null);
			er.setStatus(200);
			er.setMsg("OK");
			er.setData(tbUser);
			
		}else {
			er.setMsg("获取用户失败");
		}
		return er;
	}

	@Override
	public EgoResult logout(String token, HttpServletRequest request, HttpServletResponse response) {
		Long index = jedisDao.del(token);
		CookieUtils.deleteCookie(request, response, "TT_TOKEN");
		EgoResult er = new EgoResult();
		er.setStatus(200);
		er.setMsg("OK");
		return er;
	}

}
