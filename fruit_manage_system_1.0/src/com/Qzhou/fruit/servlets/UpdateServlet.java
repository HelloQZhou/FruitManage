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
        //1�����ñ���
        request.setCharacterEncoding("utf-8");

        //2����ȡ����
        String fname = request.getParameter("fname");
        String priceStr = request.getParameter("price");
        int price = Integer.parseInt(priceStr);

        String fcountStr = request.getParameter("fcount");
        Integer fcount = Integer.parseInt(fcountStr);
        
        String remark = request.getParameter("remark");

        String fidStr = request.getParameter("fid");
        Integer fid = Integer.parseInt(fidStr);

        //3��ִ�и���

        fruitDAO.updateFruit(new Fruit(fid,fname,price,fcount,remark));

        //4����Դ��ת ��ʱ�൱�ڷ������ڲ�ת��
        //super.processTemplate("index",request,response);

        //�˴���Ҫ�ض��� Ŀ�������¸�IndexServlet ������ ���»�ȡfuritList Ȼ�󸲸ǵ�session�� ����index.htmlҳ����
        //��ʾ��session�е����ݲ������µ�  ��ʱΪ�ض���
        response.sendRedirect("index");


    }
}
