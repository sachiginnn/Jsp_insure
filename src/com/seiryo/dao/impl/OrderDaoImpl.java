package com.seiryo.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.seiryo.dao.OrderDao;
import com.seiryo.entity.MyUserInfo;
import com.seiryo.entity.My_order;
import com.seiryo.entity.My_order_info;
import com.seiryo.utile.JDBCUTILE;

public class OrderDaoImpl implements OrderDao{

	//创建订单
	public boolean createOrder(My_order_info orderInfo,My_order myOrder) {
		boolean flag;
		String sql1 = "INSERT INTO my_order(order_id,user_id,commodity_id) VALUES(?,?,?)";
		 try {
	            //第一步插入主表
	            flag = JDBCUTILE.upDataBaseTable(sql1,
	                    myOrder.getOrder_id(),
	                    myOrder.getUser_id(),
	                    myOrder.getCommodity_id());

	            //主表插入成功，再插入详情表
	            if(flag){
	                String sql2="insert into my_order_info(" +
	                        "order_id,order_commodity_liability1,order_commodity_liability2,order_commodity_liability3," +
	                        "order_commodity_liability4,order_commodity_liability5,order_commodity_liability6,order_commodity_liability7," +
	                        "order_beneficiary_name,order_beneficiary_city,order_beneficiary_phone," +
	                        "order_commodity_time_start,order_commodity_time_length,order_sum_price) " +
	                        "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	                flag = JDBCUTILE.upDataBaseTable(sql2,
	                        orderInfo.getOrder_info_id(),
	                        orderInfo.getOrder_commodity_liability1(),
	                        orderInfo.getOrder_commodity_liability2(),
	                        orderInfo.getOrder_commodity_liability3(),
	                        orderInfo.getOrder_commodity_liability4(),
	                        orderInfo.getOrder_commodity_liability5(),
	                        orderInfo.getOrder_commodity_liability6(),
	                        orderInfo.getOrder_commodity_liability7(),
	                        orderInfo.getOrder_beneficiary_name(),
	                        orderInfo.getOrder_beneficiary_city(),
	                        orderInfo.getOrder_beneficiary_phone(),
	                        orderInfo.getOrder_commodity_time_start(),
	                        orderInfo.getOrder_commodity_time_end(),
	                        orderInfo.getOrder_commodity_time_length(),
	                        orderInfo.getOrder_sum_price());
	            }
	            
	        }catch (Exception e){
	            e.printStackTrace();
	            flag=false;
	        }
	        return flag;
	    }
	
	//遍历订单
	@Override
	public List<My_order_info> queryOrder(int userId) {
	    List<My_order_info> list=new ArrayList<My_order_info>();
	    String sql="SELECT oi.*,o.commodity_id,o.user_id FROM my_order o JOIN my_order_info oi ON o.order_id=oi.order_id WHERE o.user_id=?";
	    try{
	        ResultSet rs = JDBCUTILE.selectDataBaseTable(sql,userId);
	        while(rs.next()){
	            My_order_info info=new My_order_info(
	                    rs.getString("order_id"),
	                    rs.getInt("user_id"),
	                    rs.getInt("commodity_id"),
	                    rs.getInt("order_info_id"),
	                    rs.getString("order_commodity_liability1"),
	                    rs.getString("order_commodity_liability2"),
	                    rs.getString("order_commodity_liability3"),
	                    rs.getString("order_commodity_liability4"),
	                    rs.getString("order_commodity_liability5"),
	                    rs.getString("order_commodity_liability6"),
	                    rs.getString("order_commodity_liability7"),
	                    rs.getString("order_beneficiary_name"),
	                    rs.getString("order_beneficiary_city"),
	                    rs.getString("order_beneficiary_phone"),
	                    rs.getString("order_commodity_time_start"),
	                    rs.getString("order_commodity_time_end"),
	                    rs.getString("order_commodity_time_length"),
	                    rs.getString("order_sum_price")
	            );
	            list.add(info);
	        }
	    }catch (Exception e){
	        e.printStackTrace();
	    }
	    return list;
	}
	
}
