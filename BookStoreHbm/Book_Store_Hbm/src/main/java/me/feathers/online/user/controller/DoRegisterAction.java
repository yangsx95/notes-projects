package me.feathers.online.user.controller;

import com.alibaba.fastjson.JSON;

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
 * 执行注册操作
 *
 * @author Feathers
 * @create 2017-06-29-10:41
 */
@WebServlet(urlPatterns = "/user/reg")
public class DoRegisterAction extends HttpServlet {

    private static final long serialVersionUID = 5329746481489265913L;

    private UserService service = (UserService) BeansFactory.getBean("userService");

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取json数据
        String json = req.getParameter("user");
        System.out.println("当前类--DoRegisterAction,json===" + json);
        User user = JSON.parseObject(json, User.class);
        System.out.println("当前类--DoRegisterAction, user====" + user);

        try {
            service.save(user);
        } catch (Exception e) {
            resp.getWriter().print("0");// 注册失败
            return;
        }
        resp.getWriter().print("1"); // 注册成功
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
