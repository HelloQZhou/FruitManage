package com.Qzhou.fruit.servlets;

import com.Qzhou.fruit.dao.FruitDAO;
import com.Qzhou.fruit.dao.impl.FruitDAOImpl;
import com.Qzhou.fruit.pojo.Fruit;
import com.Qzhou.myssm.myspringmvc.ViewBaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/add.do")
public class AddServlet extends ViewBaseServlet {
    private FruitDAO fruitDAO=new FruitDAOImpl();
    //此时是 add.html 跳转到此 他其中form表单是post 方式, 则需要重写dopost
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String fname = request.getParameter("fname");
        String priceStr = request.getParameter("price");
        int price = Integer.parseInt(priceStr);
        int fcount = Integer.parseInt(request.getParameter("fcount"));
        String remark = request.getParameter("remark");

        Fruit fruit=new Fruit(0,fname,price,fcount,remark);

        fruitDAO.addFruit(fruit);

        response.sendRedirect("index");
    }
}
