package me.feathers.online.book.controller;

import com.alibaba.fastjson.JSON;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.feathers.online.book.service.BookService;
import me.feathers.online.entity.Book;
import me.feathers.online.entity.Category;
import me.feathers.online.entity.PageBean;
import me.feathers.online.util.BeansFactory;

/**
 * 用来更新首页分页数据的Servlet
 * @author Feathers
 * @create 2017-06-27-21:15
 */
@WebServlet(urlPatterns = "/book/update")
public class UpdateIndexPageAction extends HttpServlet {

    private static final long serialVersionUID = 4085522672448307912L;

    private BookService bookService = (BookService) BeansFactory.getBean("bookService");

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 获取参数
        String cate = req.getParameter("cate");
        String pageNow = req.getParameter("pageNow");

        // 判断获取的类型
        System.out.println("当前类--UpdateIndexPageAction,cate-------" + cate);
        Category c = cate.equals("1") ? Category.精选图书 : (cate.equals("2")? Category.推荐图书 :
                Category.特价图书);
        // 获取跳转的页数
        if (pageNow == null) {
            pageNow = "1";
        }
        PageBean<Book> pbs = bookService.findByPage(null, c, Integer.valueOf(pageNow), 4);
        // 转换为json字符串发送到ajax的回调函数中
        String s = JSON.toJSONString(pbs.getDatas());
        resp.getWriter().print(s);
    }
}
