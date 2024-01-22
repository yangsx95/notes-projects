package me.feathers.online.user.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.feathers.online.entity.Address;
import me.feathers.online.entity.User;
import me.feathers.online.user.service.AddressService;
import me.feathers.online.util.BeansFactory;

/**
 * 添加收货地址Action
 *
 * @author Feathers
 * @create 2017-07-01-14:33
 */
@WebServlet(urlPatterns = "/permission/addAddress")
public class AddAddressAction extends HttpServlet {

    private static final long serialVersionUID = -7675172103123762527L;

    // /Book_Store_Hbm/permission/confirmOrder?area=北京市北京市东城区
    // &emailCode=adfas&detail=adf&name=fasfa&tel=18361507620&isDefault=true

    private AddressService addressService = (AddressService) BeansFactory.getBean("addressService");


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取所需的参数
//        resp.getWriter().print(req.getRequestURL());
        String area = req.getParameter("area");
        String emailCode = req.getParameter("emailCode");
        String detail = req.getParameter("detail");
        String name = req.getParameter("name");
        String tel = req.getParameter("tel");
        String isDefault = req.getParameter("isDefault");

        System.out.println("当前类--AddAddressAction,parmary=====" + area
            + "==" + emailCode
                + "==" + detail
                + "==" + name
                + "==" + tel
                + "==" + isDefault
        );

        // 使用参数创建一个Address对象，并持久化
        Address address = new Address();
        address.setArea(area);
        address.setEmailCode(emailCode);
        address.setDetailAddress(detail);
        address.setReceiver(name);
        address.setTel(tel);
        address.setUser((User) req.getSession().getAttribute("user"));

        if (isDefault.equals("true")) {
            address.setIsDefault("1");
        } else {
            address.setIsDefault("0");
        }
        addressService.save(address);

        // 添加地址成功，返回状态码1
        resp.getWriter().print("1");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      doPost(req, resp);
    }
}
