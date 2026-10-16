package com.seiryo.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.seiryo.dao.OrderDao;
import com.seiryo.dao.impl.OrderDaoImpl;
import com.seiryo.entity.My_order;
import com.seiryo.entity.My_order_info;
import com.seiryo.service.OrderService;

public class OrderServiceImpl implements OrderService{
	
	private OrderDao orderDao = new OrderDaoImpl();
	
	public boolean createOrder(My_order_info orderInfo,My_order myOrder) {
		return orderDao.createOrder(orderInfo, myOrder);
	}
	
	public List<My_order_info> queryOrder(int userId){
		return orderDao.queryOrder(userId);
	}

}
