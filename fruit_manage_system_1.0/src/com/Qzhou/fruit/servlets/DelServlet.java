package com.Qzhou.fruit.servlets;

import com.Qzhou.fruit.dao.FruitDAO;
import com.Qzhou.fruit.dao.impl.FruitDAOImpl;
import com.Qzhou.myssm.myspringmvc.ViewBaseServlet;
import com.Qzhou.myssm.util.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/del.do")
public class DelServlet extends ViewBaseServlet {
    private FruitDAO fruitDAO=new FruitDAOImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fidStr = request.getParameter("fid");
        if(StringUtil.isNotEmpty(fidStr)){
            int fid = Integer.parseInt(fidStr);
            fruitDAO.delFruit(fid);

            //super.processTemplate("index",response); 问题同上
            //重新发送一个请求
            response.sendRedirect("index");

        }
    }
}
