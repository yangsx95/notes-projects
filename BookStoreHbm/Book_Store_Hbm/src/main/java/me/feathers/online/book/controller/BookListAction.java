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
 * 图书列表Servlet
 *
 * @author Feathers
 * @create 2017-06-29-14:35
 */
@WebServlet(urlPatterns = "/book/list")
public class BookListAction extends HttpServlet {

    private static final long serialVersionUID = -1974327912027325386L;

    private BookService bookService = (BookService) BeansFactory.getBean("bookService");
    private final Integer DEFAULT_PAGE_SIZE = 1; // 默认每页条数
    private final Integer DEFAULT_PAGE_COUNT = 1; // 默认页数

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 获取参数，判断类型
        // 精选图书，0
        // 推荐图书，1
        // 特价图书，2
        String category = req.getParameter("category");
        Category c = null;
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
        req.setAttribute("category", c);

        // 第一次使用默认的页数和数据
        PageBean<Book> page = bookService.findByPage(null, c, DEFAULT_PAGE_COUNT, DEFAULT_PAGE_SIZE);
        // 将数据放入到reqest作用于中，让前台页面获取
        req.setAttribute("pageBean", page);
        req.setAttribute("category", c);

        // 转发到前台
        req.getRequestDispatcher("/jsp/books_list.jsp").forward(req, resp);
    }
}
