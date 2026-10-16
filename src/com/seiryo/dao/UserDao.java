package com.seiryo.dao;

import java.util.List;

import com.seiryo.entity.MyUserInfo;

/**
 * 
 * @ClassName: UserDao  
 * @Description: TODO(用户数据层接口)  
 * @author 10748  
 * @date 2025年10月14日
 */
public interface UserDao {
	
	//插入用户
	boolean insertUser(MyUserInfo myUserInfo);
	
	//查询所有用户信息
	List<MyUserInfo> selectAllUserInfo();
	
	//改密码
	boolean updateUser(String pass,int id);
	 
	//充值，改手机号
	public boolean updateUserInfo(String phone,String charge,int id);
	
	MyUserInfo findNewUser(int id);
	
	//扣款
	boolean reduceMoney(int userId,double money);
	
	//查被保人
	MyUserInfo selectUserByPhone(String insuredPhone);


}
