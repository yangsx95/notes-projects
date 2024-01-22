package me.feathers.online.order.controller;

import net.sf.ehcache.search.expression.Or;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.feathers.online.cart.service.IShoppingCart;
import me.feathers.online.entity.Order;
import me.feathers.online.entity.OrderItem;
import me.feathers.online.entity.OrderStatus;
import me.feathers.online.entity.User;
import me.feathers.online.order.service.OrderService;
import me.feathers.online.user.service.AddressService;
import me.feathers.online.util.BeansFactory;

/**
 * 提交订单
 *
 * @author Feathers
 * @create 2017-07-02-14:59
 */
@WebServlet(urlPatterns = "/permission/addOrder")
public class AddOrderAction extends HttpServlet {

    private static final long serialVersionUID = 8994413805438975933L;

    private AddressService addressService = (AddressService) BeansFactory.getBean("addressService");
    private OrderService orderService = (OrderService) BeansFactory.getBean("orderService");

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 获取地址id
        String addressId = req.getParameter("addressId");
        Long addrId = 1L;
        if (addressId == null) {
            addrId = Long.valueOf(addressId);
        }
        // 获取user
        User user = (User) req.getSession().getAttribute("user");

        // 保存订单信息到订单表!
        Order order = new Order();
        // 取出订单信息
        Map<Long, OrderItem> orderItem = (Map<Long, OrderItem>) req.getSession().getAttribute("newItems");
        System.out.println("当前类--AddOrderAction,------" + orderItem.size());

        Date now = new Date();
        order.setCreateDate(now);
        order.setAddress(addressService.findById(addrId));
        order.setOrderStatus(OrderStatus.未付款);
        order.setUser(user);
        order.setOrderNum(String.valueOf(now.getTime()));
        List<OrderItem> v = new ArrayList<>(orderItem.values());
        for (int i = 0; i < v.size(); i++) {
            v.get(i).setOrder(order);
            v.get(i).setItemId(null);
        }
        Set<OrderItem> orderItems = new HashSet<>(v);
        order.setItems(orderItems);

        System.out.println("当前类--AddOrderAction,order---------------" + order);

        // 持久化订单
        orderService.save(order);

        // 清空orderItem
        orderItem.clear();
        // 清除购物车
        IShoppingCart cart = (IShoppingCart) req.getSession().getAttribute("sc");
        Map<Long, OrderItem> items = cart.getItems();
        for (int i = 0; i < orderItem.size(); i++) {
            items.remove(orderItem.get(i).getItemId());
        }

        resp.sendRedirect(req.getContextPath() + "/permission/orderList");
    }
}
