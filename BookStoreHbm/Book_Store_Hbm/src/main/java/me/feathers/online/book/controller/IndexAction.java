package me.feathers.online.book.controller;

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
 * 负责获取index.jsp的页面数据
 *
 * @author Feathers
 * @create 2017-06-27-15:00
 */
@WebServlet(urlPatterns = "/book/index")
public class IndexAction extends HttpServlet {
    private static final long serialVersionUID = 767551770555883654L;

    private BookService bookService = (BookService) BeansFactory.getBean("bookService");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取精选的数据
        PageBean<Book> bean1 = bookService.findByPage(null, Category.精选图书, 1, 4);
        req.setAttribute("bean1", bean1.getDatas());
        req.setAttribute("bean1Count", bean1.getPageCount());


        // 获取推荐图书
        PageBean<Book> bean2 = bookService.findByPage(null, Category.推荐图书, 1, 4);
        req.setAttribute("bean2", bean2.getDatas());
        req.setAttribute("bean2Count", bean2.getPageCount());

        // 获取特价图书
        PageBean<Book> bean3 = bookService.findByPage(null, Category.特价图书, 1, 4);
        req.setAttribute("bean3", bean3.getDatas());
        req.setAttribute("bean3Count", bean3.getPageCount());

        req.getRequestDispatcher("/jsp/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
