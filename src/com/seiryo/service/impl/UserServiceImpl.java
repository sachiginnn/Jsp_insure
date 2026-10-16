package com.seiryo.service.impl;

import java.util.List;

import com.seiryo.dao.UserDao;
import com.seiryo.dao.impl.UserDaoImpl;
import com.seiryo.entity.MyUserInfo;
import com.seiryo.service.UserService;

/**
 * 
 * @ClassName: UserServiceImpl  
 * @Description: TODO(用户服务层实现类)  
 * @author 10748  
 * @date 2025年10月14日
 */
public class UserServiceImpl implements UserService{
	
	//接口指向实现类
	private UserDao userDao = new UserDaoImpl();

	/**
	 * 新增用户
	 */
	@Override
	public boolean insertUser(MyUserInfo myUserInfo) {
		return userDao.insertUser(myUserInfo);
	}

	/**
	 * 查询所有用户信息
	 */
	@Override
	public List<MyUserInfo> selectAllUserInfo() {
		return userDao.selectAllUserInfo();
	}

	/**
	 * 判断用户邮箱是否重复
	 */
	@Override
	public boolean pdUserEmail(String email) {
		List<MyUserInfo> userList = userDao.selectAllUserInfo();
		if(userList.size() > 0) {
			for(MyUserInfo user:userList) {
				if(user.getUser_email().equals(email)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 用户登录
	 */
	@Override
	public MyUserInfo pdUserLogin(String email, String pass) {
		List<MyUserInfo> userList = userDao.selectAllUserInfo();
		if(userList.size() > 0) {
			for(MyUserInfo user:userList) {
				if(user.getUser_email().equals(email)) {
					if(user.getUser_password().equals(pass)) {
						return user;
					}
				}
			}
		}
		return null;
	}

	@Override
	public boolean updateUserInfo(String phone, String charge, int id) {
		// TODO Auto-generated method stub
		return userDao.updateUserInfo(phone, charge, id);
	}

	@Override
	public boolean updateUser(String pass, int id) {
		// TODO Auto-generated method stub
		return userDao.updateUser(pass, id);
	}

	@Override
	public MyUserInfo findNewUser(int id) {
		// TODO Auto-generated method stub
		return userDao.findNewUser(id);
	}

	@Override
	public boolean reduceMoney(int userId, double money) {
		// TODO Auto-generated method stub
		return userDao.reduceMoney(userId, money);
	}

	@Override
	public MyUserInfo selectUserByPhone(String insuredPhone) {
		// TODO Auto-generated method stub
		return userDao.selectUserByPhone(insuredPhone);
	}
	
}
