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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

//Servlet从3.0版本开始支持注解方式的注册
@WebServlet("/index")
public class IndexServlet extends ViewBaseServlet {
    @Override
    public void doGet(HttpServletRequest request , HttpServletResponse response)throws IOException, ServletException {
        //doPost需要设置编码
        request.setCharacterEncoding("utf-8");
        String oper = request.getParameter("oper");
        //设置当前页默认值为1
        Integer pageNo=1;
        HttpSession session = request.getSession();
        String keyword=null;
        //如果oper!=null 说明是通过form表单的查询按钮点击过来的
        if(StringUtil.isNotEmpty(oper)&&"search".equals(oper)){
            //oper 不为null 且值为search 说明是点击表单查询发送过来的请求
            //此时 pageNO应该还原为1 keyword 应该从请求参数中获取
            keyword=request.getParameter("keyword");
            pageNo=1;
            //如果keyword为null，需要设置为空字符串“” 否则会拼接成 %null%
            if(StringUtil.isEmpty(keyword)) {
                keyword = "";
            }
            //将keyword 保存（覆盖）到session 里去
            session.setAttribute("keyword",keyword);
            }else{
                //说明此处不是点击表单查询发送过来的请求（比如是点击下面的上一页或者下一页或 直接在地址栏输入网址）
                //此时keyword 应该从session作用域获取
                String pageNoStr = request.getParameter("pageNo");
                if(StringUtil.isNotEmpty(pageNoStr)){
                    //如果从请求中获取到pageNo 则进行枪强转 获取不到则为1
                    pageNo=Integer.parseInt(pageNoStr);
                }
                //如果不是点击的查询按钮，那么查询是基于session中保存的现有的keyword进行查询
                Object keywordObj=session.getAttribute("keyword");
                if(keywordObj!=null){
                    keyword=(String) keywordObj;
                }else{
                    keyword="";
                }
            }

        //重新更新当前页的值
        session.setAttribute("pageNo",pageNo);

        FruitDAO fruitDAO = new FruitDAOImpl();
        //显示第几 页的五条信息
        List<Fruit> fruitList = fruitDAO.getFruitList(keyword,pageNo);
        //保存到session作用域
        //HttpSession session = request.getSession() ;
        session.setAttribute("fruitList",fruitList);

        /*
        *   总记录条数          总页数
        *       1               1
        *       5               1
        *       10              2
        *       16              4
        *   fruitCount    (fruitCount+5-1)/5
        * */
        //总记录条数
        int fruitCount = fruitDAO.getFruitCount(keyword);
        //总页数
        int pageCount =(fruitCount+4)/5;

        session.setAttribute("pageCount",pageCount);

        //此处的视图名称是 index
        //那么thymeleaf会将这个 逻辑视图名称 对应到 物理视图 名称上去
        //逻辑视图名称 ：   index
        //物理视图名称 ：   view-prefix + 逻辑视图名称 + view-suffix
        //所以真实的视图名称是：      /       index       .html
        super.processTemplate("index",request,response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
