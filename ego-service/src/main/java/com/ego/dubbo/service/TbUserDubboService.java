package com.ego.dubbo.service;

import com.ego.pojo.TbUser;

public interface TbUserDubboService {
	
	/**
	 * 通过用户名和密码获取User信息
	 * @param user
	 * @return
	 */
	TbUser selByTbUser(TbUser user);
}
