package com.Qzhou.fruit.servlets;

import com.Qzhou.fruit.dao.FruitDAO;
import com.Qzhou.fruit.dao.impl.FruitDAOImpl;
import com.Qzhou.fruit.pojo.Fruit;
import com.Qzhou.myssm.myspringmvc.ViewBaseServlet;
import com.Qzhou.myssm.util.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/edit.do")
public class EditServlet extends ViewBaseServlet {

    private FruitDAO fruitDAO=new FruitDAOImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fidStr = req.getParameter("fid");
        if(StringUtil.isNotEmpty(fidStr)){
            int fid=Integer.parseInt(fidStr);
            Fruit fruit=fruitDAO.getFruitByFid(fid);
            req.setAttribute("fruit",fruit);
            super.processTemplate("edit",req,resp);
        }
    }
}
