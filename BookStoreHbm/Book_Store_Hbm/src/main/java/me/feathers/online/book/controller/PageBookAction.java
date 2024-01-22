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
 * 用来完成图书分页的Servlet
 *
 * @authFeathers
 * @create 2017-06-29-20:28
 */
@WebServlet(urlPatterns = "/book/page")
public class PageBookAction extends HttpServlet {

    private static final long serialVersionUID = -1225308157554912255L;

    private BookService bookService = (BookService) BeansFactory.getBean("bookService");

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 获取每页条数
        String pageSize = req.getParameter("pageSize");
        // 获取跳转页数
        String pageNow = req.getParameter("pageNow");
        // 获取category
        String category = req.getParameter("category");

        String bookName = req.getParameter("bookName");

        System.out.println("当前类--PageBookAction," + category+"========" +
                pageNow +"======" + pageSize + "=========" + bookName);
        if (pageSize == null && pageNow == null) {
            pageSize = "1";
            pageNow = "1";
        }

        Category c = null;
        if (category != null) {
            switch (category) {
                case "0":
                    c = Category.精选图书;
                    break;
                case "1":
                    c = Category.推荐图书;
                    break;
                case "2":
                    c = Category.特价图书;
            }
        }
        PageBean<Book> pageBean = bookService.findByPage(bookName, c, Integer.valueOf(pageNow),
                Integer.valueOf(pageSize));
        req.setAttribute("pageBean", pageBean);
        req.setAttribute("category", c);

        System.out.println("当前类--PageBookAction,pageBean---" + pageBean);

        req.getRequestDispatcher("/jsp/books_list.jsp").forward(req, resp);
    }
}
