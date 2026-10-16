package com.seiryo.servlet;

import java.io.IOException;

import com.seiryo.entity.MyUserInfo;
import com.seiryo.service.UserService;
import com.seiryo.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	private UserService userService = new UserServiceImpl();
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//a获取判断
		String opt = req.getParameter("opt");
		//b通过判断进行设置调用方法
		if("user_regist".equals(opt)) {
			userRegister(req,resp);
		}else if("user_login".equals(opt)) {
			userLogin(req,resp);
		}
		else if("update".equals(opt)) {
			update(req,resp);
		}
	}
	
	/**
	 * user_login
	 * @param req 请求
	 * @param resp 响应
	 * @throws ServletException 异常
	 * @throws IOException 异常
	 */
	private void userLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		
		//a获取前台传递的内容
		String user_email = req.getParameter("userEmail");
		String user_pass = req.getParameter("userPass");
		//a通过账号密码判断登录
		MyUserInfo user = userService.pdUserLogin(user_email, user_pass);
		if(user != null) {
			HttpSession session = req.getSession();
			session.setAttribute("user", user);
			resp.getWriter().println(1);
		}else {
			resp.getWriter().println(0);
		}
	}
	
	/**
	 * user_regist
	 * @param req 请求
	 * @param resp 响应
	 * @throws ServletException 异常
	 * @throws IOException 异常
	 */
	private void userRegister(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		
		//a获取传递的数据
		String userName = req.getParameter("userName");
		String userBirthday = req.getParameter("userBirthday");
		String userSex = req.getParameter("userSex");
		String userPhone = req.getParameter("userPhone");
		String userEmail = req.getParameter("userEmail");
		String userPass = req.getParameter("userPass");
		if("1".equals(userSex)) {
			userSex = "男";
		}else {
			userSex = "女";
		}
		//a设置初始化参数
		int a = 0;
		//a调用判断账号是否重复的方法
		if(!userService.pdUserEmail(userEmail)) {
			//a创建新的对象进行保存用户信息
			MyUserInfo myUserInfo = new MyUserInfo(null,userEmail,userPass, null, userPhone, "0", userName, userBirthday,userSex, 1);
			//a插入用户信息
			if(userService.insertUser(myUserInfo)) {
				a = 1;
			}
		}
		resp.getWriter().println(a);
	}
	
	private void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    HttpSession session = req.getSession();
	    MyUserInfo user=(MyUserInfo)session.getAttribute("user");

	    if(user == null){
	        resp.sendRedirect("login.jsp");
	        return;
	    }

	    int userId=user.getUser_id();

	    String userPhone = req.getParameter("userPhone");
	    String chargeStr = req.getParameter("charge");
	    String userPass = req.getParameter("userPass");

	    //充值：空则传0
	    String charge = "0";
	    if(chargeStr!=null && !chargeStr.trim().equals("")){
	        charge = chargeStr.trim();
	    }

	    //手机号null/空，复用session里面旧手机号
	    if(userPhone == null || userPhone.trim().equals("")){
	        userPhone = user.getUser_phone();
	    }

	    boolean flag1 = true;
	    //执行修改手机号和充值
	    flag1 = userService.updateUserInfo(userPhone,charge,userId);

	    boolean flag2 = true;
	    //密码不为空才修改
	    if(userPass!=null && !userPass.trim().equals("")){
	        flag2 = userService.updateUser(userPass.trim(),userId);
	    }

	    //两个操作都成功返回1，否则返回0
	    if(flag1 && flag2){

	    	//查询数据库最新数据
	        MyUserInfo newUser = userService.findNewUser(userId);
	        //覆盖session
	        req.getSession().setAttribute("user",newUser);

	        resp.getWriter().print(1);
	    }else{
	        resp.getWriter().print(0);
	    }
	}
}
