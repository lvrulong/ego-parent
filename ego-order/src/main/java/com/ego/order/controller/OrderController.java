package com.ego.order.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ego.order.service.TbOrderService;


@Controller
public class OrderController {
	@Resource
	private TbOrderService tbOrderService;
	
	/**
	 * 显示订单确认页面
	 * @param model
	 * @param ids
	 * @param request
	 * @return
	 */
	@RequestMapping("order/order-cart.html")
	public String showCartOrder(Model model,@RequestParam("id")List<Long> ids,HttpServletRequest request) {
		model.addAttribute("cartList", tbOrderService.showOrderCart(ids, request));
		return "order-cart";
	}
}
