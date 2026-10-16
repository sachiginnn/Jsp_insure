package com.seiryo.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.seiryo.entity.Commodity;
import com.seiryo.entity.MyUserInfo;
import com.seiryo.entity.My_order;
import com.seiryo.entity.My_order_info;
import com.seiryo.service.CommodityService;
import com.seiryo.service.OrderService;
import com.seiryo.service.UserService;
import com.seiryo.service.impl.CommodityServiceImpl;
import com.seiryo.service.impl.OrderServiceImpl;
import com.seiryo.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	private OrderService orderService = new OrderServiceImpl();
	
	private CommodityService cs = new CommodityServiceImpl();
	
	private UserService us = new UserServiceImpl();

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String opt = req.getParameter("opt");
		 if ("checkInsured".equals(opt)) {
	            String insuredPhone = req.getParameter("insuredPhone");
	            String commodityIdStr = req.getParameter("commodityId");
	            String insuredName = req.getParameter("insuredName");

	            // 根据手机号和姓名查询被保人用户
	            MyUserInfo insuredUser = us.selectUserByPhone(insuredPhone);
	            if (insuredUser == null) {
	                resp.getWriter().print("notReg");
	                return;
	            }
	            // 获取商品，判断年龄区间
	            int commodityId = Integer.parseInt(commodityIdStr);
	            Commodity commodity = cs.findById(commodityId);

	            String userBirth = insuredUser.getUser_birthday();
	            
	            int minAge = commodity.getCommodity_age_start();
	            int maxAge = commodity.getCommodity_age_end();
	            int userAge;
	            try {
	                // 获得年龄
	                java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("yyyyMMdd");
	                java.time.LocalDate birthDate = java.time.LocalDate.parse(userBirth, formatter);
	                java.time.LocalDate now = java.time.LocalDate.now();

	                userAge = now.getYear() - birthDate.getYear();
	                // 判断今年生日是否已经过了
	                if (now.getMonthValue() < birthDate.getMonthValue()
	                        || (now.getMonthValue() == birthDate.getMonthValue() && now.getDayOfMonth() < birthDate.getDayOfMonth())) {
	                    userAge--;
	                }
	            } catch (Exception e) {
	                // 生日解析失败，返回错误标识
	                resp.getWriter().print("error");
	                return;
	            }

	            if (userAge < minAge || userAge > maxAge) {
	                resp.getWriter().print("ageFail");
	            } else {
	                resp.getWriter().print("ok");
	            }
	            return;

				 }if("buy".equals(opt)){
				    
				    MyUserInfo user = (MyUserInfo)req.getSession().getAttribute("user");
				    if(user == null){
				        resp.getWriter().print("nologin");
				        return;
				    }
		
		            double totalFee = Double.parseDouble(req.getParameter("totalFee"));
		            String selectData = req.getParameter("selectData");
		            String startTime = req.getParameter("startTime");
		
		            //=====接收【被保人】从前台传过来的参数=====
		            String insuredName = req.getParameter("insuredName");
		            String insuredCity = req.getParameter("insuredCity");
		            String insuredPhone = req.getParameter("insuredPhone");
		            int commodityId = Integer.parseInt(req.getParameter("commodityId"));
		            
		            if (Double.parseDouble(user.getUser_money()) < totalFee) {
		                resp.getWriter().print("moneyNo");
		                return;
		            }
		
		            String orderId = System.currentTimeMillis() + "";
		            My_order myOrder = new My_order();
		            My_order_info orderInfo = new My_order_info();
		
		            myOrder.setOrder_id(orderId);
		            myOrder.setUser_id(user.getUser_id());
		            myOrder.setCommodity_id(commodityId);
		
		            orderInfo.setOrder_sum_price(totalFee + "");
		            orderInfo.setOrder_commodity_time_start(startTime);
		
		            orderInfo.setOrder_beneficiary_name(insuredName);
		            orderInfo.setOrder_beneficiary_city(insuredCity);
		            orderInfo.setOrder_beneficiary_phone(insuredPhone);
		
		
		            if (selectData != null && !selectData.trim().equals("")) {
		                String[] arr = selectData.split(",");
		                for (int i = 0; i < arr.length; i++) {
		                    String str = arr[i].trim();
		                    if ("".equals(str))
		                        continue;
		                    switch (i) {
		                        case 0:
		                            orderInfo.setOrder_commodity_liability1(str);
		                            break;
		                        case 1:
		                            orderInfo.setOrder_commodity_liability2(str);
		                            break;
		                        case 2:
		                            orderInfo.setOrder_commodity_liability3(str);
		                            break;
		                        case 3:
		                            orderInfo.setOrder_commodity_liability4(str);
		                            break;
		                        case 4:
		                            orderInfo.setOrder_commodity_liability5(str);
		                            break;
		                        case 5:
		                            orderInfo.setOrder_commodity_liability6(str);
		                            break;
		                        case 6:
		                            orderInfo.setOrder_commodity_liability7(str);
		                            break;
		                        case 7:
		                            orderInfo.setOrder_commodity_time_length(str);
		                            break;
		                    }
		                }
		            }
		
		            boolean flag = orderService.createOrder(orderInfo, myOrder);
		            if (flag) {
		                resp.getWriter().print("success");
		            } else {
		                resp.getWriter().print("fail");
		            }
		        } else if ("queryOrder".equals(opt)) {
		            MyUserInfo user = (MyUserInfo) req.getSession().getAttribute("user");
		            if(user == null){
		                resp.sendRedirect("login.jsp");
		                return;
		            }
		            List<My_order_info> orderList = orderService.queryOrder(user.getUser_id());
		            //判空，防止null遍历报错
		            if(orderList == null){
		                orderList = new ArrayList<>();
		            }
		            List<Commodity> comList = new ArrayList<>();
		            for (My_order_info info : orderList) {
		            	
		                Commodity com = cs.findById(info.getCommodity_id());
		                comList.add(com);
		            }
		            req.setAttribute("orderList", orderList);
		            req.setAttribute("comList", comList);
		            req.getRequestDispatcher("my_order.jsp").forward(req, resp);
		            
		        }
		}
}
	
