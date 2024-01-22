package me.feathers.online.user.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.feathers.online.cart.service.IShoppingCart;
import me.feathers.online.cart.service.impl.MemoryCart;
import me.feathers.online.entity.User;
import me.feathers.online.user.service.UserService;
import me.feathers.online.util.BeansFactory;

/**
 * 做登陆操作的Servlet，对用户名和密码进行判断
 *
 * @author Feathers
 * @create 2017-06-28-10:10
 */
@WebServlet(urlPatterns = "/user/doLogin")
public class DoLoginAction extends HttpServlet {

    private static final long serialVersionUID = 3451153755218912773L;

    private UserService userService = (UserService) BeansFactory.getBean("userService");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 获取用户名和密码
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String remember = req.getParameter("remember");

        System.out.println("当前类--DoLoginAction,======" + username +"========" + password +
                "=======" + remember);

        if (username != null && password != null) {
            User user = userService.findByUserName(username);
            // 当查找用户为null时，代表该用户不存在     返回状态码0  用户名不存在
            if (user == null) {
                resp.getWriter().println("0");
            } else {
                // 当输入框中的密码和用户密码不一致     返回状态码1   密码错误
                if (!user.getPassword().equals(password)) {
                    resp.getWriter().println("1");
                } else {  // 当密码正确时，返回状态吗2
                    resp.getWriter().println("2");
                    // 将user对象放入到session域中, 执行页面的跳转
                    req.getSession().setAttribute("user", user);
                    // 将购物车对象放入到session域中
                    IShoppingCart cart = new MemoryCart();
                    req.getSession().setAttribute("sc", cart);

                    if (remember.equals("true")) {
                        // 设置cookie 用来保存用户名和密码
                        Cookie un = new Cookie("username", username);
                        Cookie pwd = new Cookie("password", password);
                        un.setMaxAge(60 * 60);
                        pwd.setMaxAge(60 * 60);
                        un.setPath("/");
                        pwd.setPath("/");
                        resp.addCookie(un);
                        resp.addCookie(pwd);
                    }
                }
            }
        }
    }
}
