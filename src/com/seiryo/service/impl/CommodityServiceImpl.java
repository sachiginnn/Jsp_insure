package com.seiryo.service.impl;

import java.util.List;

import com.seiryo.dao.CommodityDao;
import com.seiryo.dao.impl.CommodityDaoImpl;
import com.seiryo.entity.Commodity;
import com.seiryo.entity.Commodity_liability_info;
import com.seiryo.entity.MyUserInfo;
import com.seiryo.service.CommodityService;

public class CommodityServiceImpl implements CommodityService{

	 private CommodityDao com = new CommodityDaoImpl();

	@Override
	public List<Commodity> findAllCommodity() {
		// TODO Auto-generated method stub
		return com.selectAllCommodity();
	}

	@Override
	public Commodity_liability_info findCommodity(int commodityId) {
		// TODO Auto-generated method stub
		return com.selectCommodityFullInfoById(commodityId);
	}

	@Override
	public List<Commodity> findByType(String commodity_type) {
		// TODO Auto-generated method stub
		return com.findByType(commodity_type);
	}

	@Override
	public Commodity findById(int commodityId) {
		// TODO Auto-generated method stub
		return com.findById(commodityId);
	}
	
	

}
