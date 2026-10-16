package com.seiryo.service;

import java.util.List;

import com.seiryo.entity.Commodity;
import com.seiryo.entity.Commodity_liability_info;
import com.seiryo.entity.MyUserInfo;

public interface CommodityService {
	
	// 查询所有商品
    List<Commodity> findAllCommodity();
    
    //查询单个保险
    Commodity findById(int commodityId);

    // 详情页：联表查完整信息
    Commodity_liability_info findCommodity(int commodityId);
    
    //根据类型查找保险
    List<Commodity> findByType(String commodity_type);

}
