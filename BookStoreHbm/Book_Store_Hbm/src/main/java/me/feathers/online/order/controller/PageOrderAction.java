package me.feathers.online.order.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.feathers.online.entity.Order;
import me.feathers.online.entity.OrderStatus;
import me.feathers.online.entity.PageBean;
import me.feathers.online.entity.User;
import me.feathers.online.order.service.OrderService;
import me.feathers.online.util.BeansFactory;
import me.feathers.online.util.DateUtil;

/**
 * @author Feathers
 * @create 2017-07-03-13:15
 */
@WebServlet(urlPatterns = "/permission/order/page")
public class PageOrderAction extends HttpServlet {

    private static final long serialVersionUID = -2249258212747976407L;

    private OrderService orderService = (OrderService) BeansFactory.getBean("orderService");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取每页条数
        String pageSize = req.getParameter("pageSize");
        // 获取跳转页数
        String pageNow = req.getParameter("pageNow");
        // 获取排序
        String orderBy = req.getParameter("orderBy");
        // 获取状态
        String orderStatus = req.getParameter("orderState");

        String orderNum = req.getParameter("orderNum");

//        System.out.println("---" + pageSize + "----" + pageNow + "----" + orderBy + "----" +
//                orderStatus + "-----");

        // 获取当前用户
        User user = (User) req.getSession().getAttribute("user");

        Integer ps;
        if (pageSize != null) {
            ps = Integer.valueOf(pageSize);
        } else {
            ps = null;
        }

        Integer pn;
        if (pageNow != null) {
            pn = Integer.valueOf(pageNow);
        } else {
            pn = null;
        }

        OrderStatus os;
        if ("已付款".equals(orderStatus)) {
            os = OrderStatus.已付款;
        } else if ("未付款".equals(orderStatus)) {
            os = OrderStatus.未付款;
        } else if ("交易成功".equals(orderStatus)) {
            os = OrderStatus.交易成功;
        } else {
            os = null;
        }

        if (orderBy == null || orderBy.trim().length() == 0) {
            orderBy = null;
        }

        // 获取订单的开始时间和结束时间
        String start = req.getParameter("start");
        String end = req.getParameter("end");
        System.out.println("当前类--PageOrderAction,start" + start + "====" + end);
        Date startDate = null;
        if (start != null) {
           startDate = DateUtil.parse("MM/dd/yyyy", start);
        }
        Date endDate = null;
        if (end != null) {
            endDate = DateUtil.parse("MM/dd/yyyy", end);
        }

        System.out.println("当前类--PageOrderAction,start" + startDate + "--" + endDate);

        PageBean<Order> pageBean = orderService.findByPage(user, pn, ps, os, orderBy, orderNum,
                startDate, endDate);
        req.setAttribute("pageBean", pageBean);
        req.getRequestDispatcher("/jsp/order_list.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
