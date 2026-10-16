package com.seiryo.service;

import java.util.List;

import com.seiryo.entity.MyUserInfo;
import com.seiryo.entity.My_order;
import com.seiryo.entity.My_order_info;

public interface OrderService {

	//生成订单
	boolean createOrder(My_order_info orderInfo,My_order myOrder);

	//遍历订单
	List<My_order_info> queryOrder(int userId);

}
