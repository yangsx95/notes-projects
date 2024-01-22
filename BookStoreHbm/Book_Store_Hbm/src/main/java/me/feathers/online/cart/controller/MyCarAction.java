package me.feathers.online.cart.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.feathers.online.book.service.BookService;
import me.feathers.online.cart.service.IShoppingCart;
import me.feathers.online.entity.Book;
import me.feathers.online.entity.OrderItem;
import me.feathers.online.util.BeansFactory;

/**
 * 我的购物车
 *
 * @author Feathers
 * @create 2017-06-30-10:15
 */
@WebServlet(urlPatterns = "/permission/mycar")
public class MyCarAction extends HttpServlet {

    private static final long serialVersionUID = -1186624130699796679L;

    private BookService bookService = (BookService) BeansFactory.getBean("bookService");

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("当前类--MyCarAction,--------进入到了我的购物车");

        // 获取图书的具体信息,即获取book对象
        List<Book> bookList = new ArrayList<>();
        IShoppingCart sc = (IShoppingCart) req.getSession().getAttribute("sc");
        Map<Long, OrderItem> items = sc.getItems();
        Set<Long> set = items.keySet();
        Iterator<Long> iterator = set.iterator();
        while (iterator.hasNext()) {
            Long next = iterator.next();
            OrderItem item = items.get(next);
            Book b = bookService.findById(item.getItemId());
            bookList.add(b);
        }
        // 将获取到的bookList放入到request作用域中
        req.setAttribute("bookList", bookList);
        System.out.println("当前类--MyCarAction,booklist" + bookList);
        req.getRequestDispatcher("/jsp/cart.jsp").forward(req, resp);
    }
}
