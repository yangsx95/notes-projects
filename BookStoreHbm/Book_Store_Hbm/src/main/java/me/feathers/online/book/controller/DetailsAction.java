package me.feathers.online.book.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.feathers.online.book.service.BookService;
import me.feathers.online.cart.service.IShoppingCart;
import me.feathers.online.entity.Book;
import me.feathers.online.util.BeansFactory;

/**
 * 购物车页面数据获取后台servlet
 *
 * @author Feathers
 * @create 2017-06-28-15:12
 */
@WebServlet(urlPatterns = "/book/details")
public class DetailsAction extends HttpServlet {

    private static final long serialVersionUID = -8765205210080640404L;

    private BookService bookService = (BookService) BeansFactory.getBean("bookService");

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String bookId = req.getParameter("id");
        System.out.println("当前类--DetailsAction,bookId" + bookId);
        // 如果没有bookId 跳转到首页
        if (bookId == null) {
            resp.sendRedirect(req.getContextPath()+"/book/index");
            return;
        }

        // 如果有bookId参数，根据bookId获取book
        Book book = bookService.findById(Long.valueOf(bookId));
        // 将book放入到request作用域中
        req.setAttribute("book", book);
        System.out.println("当前类--DetailsAction," + book);
        // 转发到前台jsp页面
        req.getRequestDispatcher("/jsp/details.jsp").forward(req, resp);
    }
}
