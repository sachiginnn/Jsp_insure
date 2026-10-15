package com.seiryo.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.seiryo.dao.CommodityDao;
import com.seiryo.entity.Commodity;
import com.seiryo.entity.Commodity_liability_info;
import com.seiryo.utile.JDBCUTILE;

public class CommodityDaoImpl implements CommodityDao{
	
	/**
	 * 遍历所有商品
	 */
	@Override
	public List<Commodity> selectAllCommodity() {
		String sql = "SELECT * FROM commodity";
		ResultSet rst = null;
		Commodity c = null;
		List<Commodity> list = new ArrayList<Commodity>();
		try {
			rst = JDBCUTILE.selectDataBaseTable(sql);
			while(rst.next()) {
				c = new Commodity();
			    
			    c.setCommodity_id(rst.getInt("commodity_id"));
			    c.setCommodity_name(rst.getString("commodity_name"));
			    c.setCommodity_price(rst.getString("commodity_price"));
			    c.setCommodity_introduce(rst.getString("commodity_introduce"));
			    c.setCommodity_img(rst.getString("commodity_img"));
			    c.setCommodity_type(rst.getString("commodity_type"));
			    c.setCommodity_age_start(rst.getInt("commodity_age_start"));
			    c.setCommodity_age_end(rst.getInt("commodity_age_end"));
			    c.setCommodity_time_length(rst.getString("commodity_time_length"));
			    c.setCommodity_applicable_gender(rst.getInt("commodity_applicable_gender"));
			    c.setCommodity_applicable_location(rst.getString("commodity_applicable_location"));
			    c.setCommodity_signing_form(rst.getString("commodity_signing_form"));
			    c.setCommodity_liability_zjs(rst.getString("commodity_liability_zjs"));
			    c.setCommodity_detailed_information1(rst.getString("commodity_detailed_information1"));
			    c.setCommodity_detailed_information2(rst.getString("commodity_detailed_information2"));
			    c.setCommodity_detailed_information3(rst.getString("commodity_detailed_information3"));
                list.add(c);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				JDBCUTILE.dbClose(rst, null, null);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return list;
	}
	
	//商品表与详情表关联
	 @Override
	 public Commodity_liability_info selectCommodityFullInfoById(int commodityId) {
	        ResultSet rst = null;
	        Commodity_liability_info cli = null;
	        Object obj[] = {commodityId};
	        try {
	            //详情页LEFT JOIN两张表，取出主表和7条保障文本
	            String sql = "select c.*," +
	                    "i.commodity_liability_info1," +
	                    "i.commodity_liability_info2," +
	                    "i.commodity_liability_info3," +
	                    "i.commodity_liability_info4," +
	                    "i.commodity_liability_info5," +
	                    "i.commodity_liability_info6," +
	                    "i.commodity_liability_info7 " +
	                    "from commodity c join commodity_liability_info i " +
	                    "on c.commodity_id = i.commodity_liability_info_id where c.commodity_id = ?";

	            rst = JDBCUTILE.selectDataBaseTable(sql,obj);
	            if(rst.next()){
	            	cli = new Commodity_liability_info(
	                        //父类Commodity参数（主表）
	            			rst.getInt("commodity_id"),
            	            rst.getString("commodity_name"),
            	            rst.getString("commodity_price"),
            	            rst.getString("commodity_introduce"),
            	            rst.getString("commodity_img"),
            	            rst.getInt("commodity_age_start"),
            	            rst.getInt("commodity_age_end"),
            	            rst.getString("commodity_time_length"),
            	            rst.getInt("commodity_applicable_gender"),
            	            rst.getString("commodity_applicable_location"),
            	            rst.getString("commodity_signing_form"),
            	            rst.getString("commodity_liability_zjs"),
            	            rst.getString("commodity_detailed_information1"),
            	            rst.getString("commodity_detailed_information2"),
            	            rst.getString("commodity_detailed_information3"),
            	            rst.getString("commodity_type"),
            	            //详情表
            	            rst.getInt("commodity_id"),
            	            rst.getString("commodity_liability_info1"),
            	            rst.getString("commodity_liability_info2"),
            	            rst.getString("commodity_liability_info3"),
            	            rst.getString("commodity_liability_info4"),
            	            rst.getString("commodity_liability_info5"),
            	            rst.getString("commodity_liability_info6"),
            	            rst.getString("commodity_liability_info7")
	                );
	            }
	        }catch (Exception e){
	            e.printStackTrace();
	        }finally {
	            try {
					JDBCUTILE.dbClose(rst,null,null);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
	        return cli;
	    }

	 @Override
	 public List<Commodity> findByType(String commodity_type) {
		 	String sql = "SELECT * FROM commodity WHERE commodity_type=?";
			ResultSet rst = null;
			Commodity c = null;
			List<Commodity> list = new ArrayList<Commodity>();
			Object obj[]= {commodity_type};
			try {
				rst = JDBCUTILE.selectDataBaseTable(sql,obj);
				while(rst.next()) {
					c = new Commodity();
				    
				    c.setCommodity_id(rst.getInt("commodity_id"));
				    c.setCommodity_name(rst.getString("commodity_name"));
				    c.setCommodity_price(rst.getString("commodity_price"));
				    c.setCommodity_introduce(rst.getString("commodity_introduce"));
				    c.setCommodity_img(rst.getString("commodity_img"));
				    c.setCommodity_type(rst.getString("commodity_type"));
				    c.setCommodity_age_start(rst.getInt("commodity_age_start"));
				    c.setCommodity_age_end(rst.getInt("commodity_age_end"));
				    c.setCommodity_time_length(rst.getString("commodity_time_length"));
				    c.setCommodity_applicable_gender(rst.getInt("commodity_applicable_gender"));
				    c.setCommodity_applicable_location(rst.getString("commodity_applicable_location"));
				    c.setCommodity_signing_form(rst.getString("commodity_signing_form"));
				    c.setCommodity_liability_zjs(rst.getString("commodity_liability_zjs"));
				    c.setCommodity_detailed_information1(rst.getString("commodity_detailed_information1"));
				    c.setCommodity_detailed_information2(rst.getString("commodity_detailed_information2"));
				    c.setCommodity_detailed_information3(rst.getString("commodity_detailed_information3"));
	                list.add(c);

				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				try {
					JDBCUTILE.dbClose(rst, null, null);
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			return list;
		}

	 //查询单个保险
	 @Override
	 public Commodity findById(int commodityId) {
		 String sql = "SELECT * FROM commodity WHERE commodity_id=?";
			ResultSet rst = null;
			Commodity c = null;
			Object obj[] = {commodityId};
			
			try {
				rst = JDBCUTILE.selectDataBaseTable(sql,obj);
				if(rst.next()) {
					c = new Commodity();
				    
				    c.setCommodity_id(rst.getInt("commodity_id"));
				    c.setCommodity_name(rst.getString("commodity_name"));
				    c.setCommodity_price(rst.getString("commodity_price"));
				    c.setCommodity_introduce(rst.getString("commodity_introduce"));
				    c.setCommodity_img(rst.getString("commodity_img"));
				    c.setCommodity_type(rst.getString("commodity_type"));
				    c.setCommodity_age_start(rst.getInt("commodity_age_start"));
				    c.setCommodity_age_end(rst.getInt("commodity_age_end"));
				    c.setCommodity_time_length(rst.getString("commodity_time_length"));
				    c.setCommodity_applicable_gender(rst.getInt("commodity_applicable_gender"));
				    c.setCommodity_applicable_location(rst.getString("commodity_applicable_location"));
				    c.setCommodity_signing_form(rst.getString("commodity_signing_form"));
				    c.setCommodity_liability_zjs(rst.getString("commodity_liability_zjs"));
				    c.setCommodity_detailed_information1(rst.getString("commodity_detailed_information1"));
				    c.setCommodity_detailed_information2(rst.getString("commodity_detailed_information2"));
				    c.setCommodity_detailed_information3(rst.getString("commodity_detailed_information3"));
	               
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				try {
					JDBCUTILE.dbClose(rst, null, null);
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			return c;
		}
	

}
