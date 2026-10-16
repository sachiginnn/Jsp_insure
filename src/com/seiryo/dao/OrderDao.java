package com.seiryo.dao;

import java.util.List;

import com.seiryo.entity.My_order;
import com.seiryo.entity.My_order_info;

public interface OrderDao {
	//创建订单
	boolean createOrder(My_order_info orderInfo,My_order myOrder);

	//遍历全部订单
	List<My_order_info> queryOrder(int userId);

}
