package me.feathers.online.cart.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.feathers.online.cart.service.IShoppingCart;
import me.feathers.online.entity.Address;
import me.feathers.online.entity.OrderItem;
import me.feathers.online.entity.User;
import me.feathers.online.user.service.AddressService;
import me.feathers.online.util.BeansFactory;

/**
 * 我的订单Action
 *
 * @author Feathers
 * @create 2017-07-01-10:24
 */
@WebServlet(urlPatterns = "/permission/confirmOrder")
public class ConfirmOrderAction extends HttpServlet {

    private static final long serialVersionUID = 1787289998645379716L;

    private AddressService service = (AddressService) BeansFactory.getBean("addressService");

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 获取地址数据
        User user = (User) req.getSession().getAttribute("user");
        List<Address> addresses = service.findByUserId(user.getUserId());
        // 将地址数据放入到request作用域中
        req.setAttribute("addresses", addresses);
//        resp.getWriter().print(addresses.toString());

        // 获取商品
        Map<Long, OrderItem> newItems = new HashMap<>();
        IShoppingCart cart = (IShoppingCart) req.getSession().getAttribute("sc");
        Map<Long, OrderItem> items = cart.getItems();

        String[] ids = req.getParameterValues("id");
        if (ids.length == 1 && ids[0].equals("all")) {
            // 选择所有商品
            newItems = items;
        } else { // 选择指定的商品
            for (int i = 0; i < ids.length; i++) {
                Long id = Long.valueOf(ids[i]);
                newItems.put(id, items.get(id));
            }
        }
        req.getSession().setAttribute("newItems", newItems);

        req.getRequestDispatcher("/jsp/confirm_order.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
