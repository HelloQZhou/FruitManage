package com.Qzhou.fruit.servlets;

import com.Qzhou.fruit.dao.FruitDAO;
import com.Qzhou.fruit.dao.impl.FruitDAOImpl;
import com.Qzhou.fruit.pojo.Fruit;
import com.Qzhou.myssm.myspringmvc.ViewBaseServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/update.do")
public class UpdateServlet extends ViewBaseServlet {

    private FruitDAO fruitDAO=new FruitDAOImpl();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws  IOException {
        //1、设置编码
        request.setCharacterEncoding("utf-8");

        //2、获取参数
        String fname = request.getParameter("fname");
        String priceStr = request.getParameter("price");
        int price = Integer.parseInt(priceStr);

        String fcountStr = request.getParameter("fcount");
        Integer fcount = Integer.parseInt(fcountStr);
        
        String remark = request.getParameter("remark");

        String fidStr = request.getParameter("fid");
        Integer fid = Integer.parseInt(fidStr);

        //3、执行更新

        fruitDAO.updateFruit(new Fruit(fid,fname,price,fcount,remark));

        //4、资源跳转 此时相当于服务器内部转发
        //super.processTemplate("index",request,response);

        //此处需要重定向 目的是重新给IndexServlet 发请求 重新获取furitList 然后覆盖到session中 这样index.html页面上
        //显示的session中的数据才是最新的  此时为重定向
        response.sendRedirect("index");


    }
}
