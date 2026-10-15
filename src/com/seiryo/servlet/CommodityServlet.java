package com.seiryo.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import java.util.Random;

import com.seiryo.entity.Commodity;
import com.seiryo.entity.Commodity_liability_info;
import com.seiryo.entity.MyUserInfo;
import com.seiryo.service.CommodityService;
import com.seiryo.service.UserService;
import com.seiryo.service.impl.CommodityServiceImpl;
import com.seiryo.service.impl.UserServiceImpl;
import com.seiryo.utile.JSONUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;

@WebServlet("/CommodityServlet")
public class CommodityServlet extends HttpServlet{

    private static final long serialVersionUID = 1L;
    private CommodityService commodityService = new CommodityServiceImpl();
    private UserService userService = new UserServiceImpl();

    // ==========【公共方法】==========
    private List<Commodity> getAll(HttpServletRequest req){
        List<Commodity> list = commodityService.findAllCommodity();
        HttpSession session = req.getSession();
        Object User = session.getAttribute("user");
        if (User == null) {
            Random random = new Random();
            List<Commodity> newList = new ArrayList<>();
            while(list.size() > 0){
                int index = random.nextInt(list.size());
                newList.add(list.remove(index));
            }
            list = newList;
        }
        return list;
    }


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        String op = req.getParameter("op");

       
         if ("findByType".equals(op)) {
            String indexStr = req.getParameter("idx");
            List<Commodity> list = null;
            if("0".equals(indexStr)){
                list = getAll(req); 
            }else if("1".equals(indexStr)){
                list = commodityService.findByType("健康保険");
            }else if("2".equals(indexStr)){
                list = commodityService.findByType("意外保険");
            }else if("3".equals(indexStr)){
                list = commodityService.findByType("企業保険");
            }else if("4".equals(indexStr)){
                list = commodityService.findByType("旅行保険");
            }else if("5".equals(indexStr)){
                list = commodityService.findByType("家計保険");
            }else{
                list = getAll(req);
            }

            String json = null;
            try {
                json = JSONUtil.toJSONString(list);
                resp.getWriter().write(json);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else if ("commodityQueryById".equals(op)){
            //获取传过来的保险id
            String commodity_id = req.getParameter("commodity_id");
            int commodityId = Integer.parseInt(commodity_id);
            //调用业务层，通过id查询单个保险对象
            Commodity com = commodityService.findById(commodityId);
            //把查到的单条保险对象存到request域，给page_info.jsp页面取数据
            req.setAttribute("commodityInfo",com);
            //转发跳转到详情页面 page_info.jsp
            req.getRequestDispatcher("/page_info.jsp").forward(req,resp);
        }else if("findById".equals(op)) {
        	//获取传过来的保险id
            String commodity_id = req.getParameter("commodity_id");
            int commodityId = Integer.parseInt(commodity_id);
            //调用业务层，通过id查询单个保险对象
            Commodity_liability_info com = commodityService.findCommodity(commodityId);
          //把查到的单条保险对象存到request域
            req.setAttribute("commodityInfo",com);
            req.getRequestDispatcher("/shop.jsp").forward(req,resp);
            
        }
    }
}