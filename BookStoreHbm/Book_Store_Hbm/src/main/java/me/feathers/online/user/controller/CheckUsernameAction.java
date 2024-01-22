package me.feathers.online.user.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.feathers.online.entity.User;
import me.feathers.online.user.service.UserService;
import me.feathers.online.util.BeansFactory;

/**
 * 判断用户名是否存在的Servlet
 *
 * @author Feathers
 * @create 2017-06-28-21:28
 */
@WebServlet(urlPatterns = "/user/checkName")
public class CheckUsernameAction extends HttpServlet {
    private static final long serialVersionUID = -8316854635632589779L;

    private UserService service = (UserService) BeansFactory.getBean("userService");

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");
        User user = null;
        if (username != null) {
            user = service.findByUserName(username);
        }
        if (user == null) {
            resp.getWriter().print("1"); // 用户名可用
        } else {
            resp.getWriter().print("0"); // 用户名已经存在
        }
    }
}
