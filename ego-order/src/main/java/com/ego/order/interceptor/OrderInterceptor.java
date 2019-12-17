package com.ego.order.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.ego.commons.pojo.EgoResult;
import com.ego.commons.utils.CookieUtils;
import com.ego.commons.utils.HttpClientUtil;
import com.ego.commons.utils.JsonUtils;

public class OrderInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		String token = CookieUtils.getCookieValue(request, "TT_TOKEN");
		if (token != null && !token.equals("")) {
			String json = HttpClientUtil.doPost("http://localhost:8084/user/token/" + token);
			EgoResult er = JsonUtils.jsonToPojo(json, EgoResult.class);
			if (er.getStatus() == 200) {
				return true;
			}
		}
		String num = request.getParameter("num");
		response.sendRedirect(
				"http://localhost:8084/user/showLogin?interurl=" + request.getRequestURL() + "%3Fnum=" + num);
		return false;
	}

}
