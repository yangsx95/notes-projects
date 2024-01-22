package me.feathers.online.cart.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.feathers.online.book.service.BookService;
import me.feathers.online.cart.service.IShoppingCart;
import me.feathers.online.cart.service.impl.MemoryCart;
import me.feathers.online.entity.Book;
import me.feathers.online.entity.OrderItem;
import me.feathers.online.util.BeansFactory;

/**
 * 添加到购物车中
 *
 * @author Feathers
 * @create 2017-06-30-11:07
 */
@WebServlet(urlPatterns = "/permission/cart/*")
public class CartAction extends HttpServlet {

    private static final long serialVersionUID = -7765513257127806835L;

    /** 请求URL资源常量 */
    private static final String _SHOPPING = "/addCartAjax";// 添加到购物车
    private static final String _TO_SHOPPINGCART = "/showcart";// 查看购物车
    private static final String _MODIFYCART = "/modifyCartAjax";// 修改商品数量
    private static final String _REMOVE_CART = "/removeCartAjax";// 删除商品
    private static final String _CLEAR_CART = "/clearCartAjax";// 清空购物车

    private BookService bookService = (BookService) BeansFactory.getBean("bookService");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("当前类--CartAction,servlet path === " + req.getPathInfo());

        String path = req.getPathInfo();

        // 从作用域中获取购物车sc
        IShoppingCart sc = null;
        if (req.getSession().getAttribute("sc") == null) {
            req.getSession().setAttribute("sc", new MemoryCart());
        }
        sc = (IShoppingCart) req.getSession().getAttribute("sc");

        if (_SHOPPING.equals(path)) {
            // 根据id获取图书信息
            String id = req.getParameter("id");
            if (id == null) {
                resp.getWriter().print("0"); // 没有id，添加购物车失败
                return;
            }

            Long lid = Long.valueOf(id);

            Book book = bookService.findById(lid);
            if (book == null) {
                resp.getWriter().print("1"); // id没有对应的商品，添加购物车失败
                return;
            }

            System.out.println("当前类--CartAction,orderitem === " + sc);

            // 获取count
            String count = req.getParameter("count");
            if (count == null) {
                resp.getWriter().print("2"); // 没有数量，无法添加到购物车
                return;
            }
            Integer lcount = Integer.valueOf(count);

            // 判断购物车中是否有了此商品  如果没有添加一个
            if (!sc.getItems().containsKey(lid)) {
                OrderItem item = new OrderItem();
                item.setItemId(book.getBookId());
                item.setBook_isbn(book.getIsbn());
                item.setBook_author(book.getAuthor());
                item.setBook_name(book.getName());
                item.setPrice(book.getNewPrice());
                item.setCount(lcount);
                item.setImageUrl(book.getImage().getUrl());
                sc.addCart(item);
                // 有了这个商品，则直接更新数量
            } else {
                sc.modifyCount(lid, sc.getItems().get(lid).getCount() + lcount);
            }
            resp.getWriter().print(sc.getCount() + ":" + sc.getTotal());

            System.out.println("------" + sc.getCount() + "========" + sc.getTotal() + "------" +
                    sc.getItems());
        } else if (_TO_SHOPPINGCART.equals(path)) {

        } else if (_REMOVE_CART.equals(path)) {
            System.out.println("当前类--CartAction,进入了");
            // 执行删除操作
            String[] ids = req.getParameterValues("id");
            if (ids == null) {
                resp.getWriter().print("0"); // 删除失败
                return;
            } else {

                for (String id : ids) {
                    sc.delete(Long.valueOf(id));
                }
                resp.getWriter().print(sc.getCount()+":"+sc.getTotal()); // 删除成功
            }
        } else if (_MODIFYCART.equals(req.getPathInfo())) {
            String count = req.getParameter("count");
            String id = req.getParameter("id");
            if (count != null && id != null) {
                sc.modifyCount(Long.valueOf(id), Integer.valueOf(count));
                System.out.println("当前类--CartAction,----------" + sc.getCount() + "---" + sc.getTotal());
                resp.getWriter().print(sc.getCount() + ":" + sc.getTotal());
            }
        }
    }
}
