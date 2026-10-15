package com.seiryo.dao;

import java.util.List;

import com.seiryo.entity.Commodity;
import com.seiryo.entity.Commodity_liability_info;


public interface CommodityDao {

	/**
	 * 遍历所有商品
	 */
	List<Commodity> selectAllCommodity();

	Commodity_liability_info selectCommodityFullInfoById(int commodityId);
	
	List<Commodity> findByType(String commodity_type);

	//查询单件商品
	Commodity findById(int commodityId);
	
	

}
