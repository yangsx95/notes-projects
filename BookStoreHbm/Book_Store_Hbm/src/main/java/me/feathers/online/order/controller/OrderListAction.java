package me.feathers.online.order.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.feathers.online.entity.Order;
import me.feathers.online.entity.PageBean;
import me.feathers.online.entity.User;
import me.feathers.online.order.service.OrderService;
import me.feathers.online.util.BeansFactory;

/**
 * Order列表
 *
 * @author Feathers
 * @create 2017-07-02-22:22
 */
@WebServlet(urlPatterns = "/permission/orderList")
public class OrderListAction extends HttpServlet {

    private static final long serialVersionUID = 1692922578876274284L;

    private OrderService orderService = (OrderService) BeansFactory.getBean("orderService");
    private final Integer DEFAULT_PAGE_SIZE = 1; // 默认每页条数
    private final Integer DEFAULT_PAGE_COUNT = 1; // 默认页数

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 获取当前用户
        User user = (User) req.getAttribute("user");

        // 查询所有订单
        PageBean<Order> pageBean = orderService.findByPage(user, DEFAULT_PAGE_COUNT, DEFAULT_PAGE_SIZE, null,
                null, null, null, null);
        req.setAttribute("pageBean", pageBean);

//        System.out.println("当前类--OrderListAction,pageBea==============" + pageBean + "====="
//                + pageBean.getDatas());

        req.getRequestDispatcher("/jsp/order_list.jsp").forward(req, resp);
    }
}
