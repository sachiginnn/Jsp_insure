package com.seiryo.service;

import java.util.List;

import com.seiryo.entity.MyUserInfo;

/**
 * 
 * @ClassName: UserService  
 * @Description: TODO(用户服务层接口)  
 * @author 10748  
 * @date 2025年10月14日
 */
public interface UserService {
	
	//插入用户
	boolean insertUser(MyUserInfo myUserInfo);
	
	//查询所有用户信息
	List<MyUserInfo> selectAllUserInfo();
	
	//判断用户邮箱是否重复
	boolean pdUserEmail(String email);
	
	//用户登录
	MyUserInfo pdUserLogin(String email,String pass);
	
	//充值，改手机号
	boolean updateUserInfo(String phone,String charge,int id);
	
	//改密码
	boolean updateUser(String pass,int id);
	
	MyUserInfo findNewUser(int id);
	
	//扣款
	boolean reduceMoney(int userId, double money);

	//查被保人
	MyUserInfo selectUserByPhone(String insuredPhone);
	
}
