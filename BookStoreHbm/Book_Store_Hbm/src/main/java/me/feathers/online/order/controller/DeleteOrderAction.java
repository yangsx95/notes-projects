package me.feathers.online.order.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.feathers.online.entity.Order;
import me.feathers.online.entity.PageBean;
import me.feathers.online.order.service.OrderService;
import me.feathers.online.util.BeansFactory;

/**
 * 删除订单Servlet
 *
 * @author Feathers
 * @create 2017-07-03-14:16
 */
@WebServlet(urlPatterns = "/permission/deleteOrder")
public class DeleteOrderAction extends HttpServlet {

    private static final long serialVersionUID = 6287408256101160038L;

    private OrderService orderService = (OrderService) BeansFactory.getBean("orderService");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String orderId = req.getParameter("orderId");



        if (orderId != null && "all".equals(orderId)){
            // 清除所有的订单
            PageBean<Order> byPage =
                    orderService.findByPage(null, null, null, null, null, null, null, null);
            for(int i = 0; i < byPage.getDatas().size(); i++) {
                Order order = byPage.getDatas().get(i);
                orderService.delete(order);
            }

        }

        if (orderId != null && !"all".equals(orderId)){
            String[] s = orderId.split(":");
            for (int i = 0; i < s.length; i++) {
                Long id = Long.valueOf(s[i]);
                orderService.delete(orderService.findById(id));
            }
        }
        req.getRequestDispatcher("/permission/orderList").forward(req, resp);
    }
}
