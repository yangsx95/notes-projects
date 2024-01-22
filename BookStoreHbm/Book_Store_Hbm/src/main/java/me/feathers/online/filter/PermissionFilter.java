package me.feathers.online.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import me.feathers.online.entity.User;

/**
 * 权限过滤
 *
 * @author Feathers
 * @create 2017-06-27-10:23
 */
@WebFilter(filterName="permissionFilter",urlPatterns="/permission/*")
public class PermissionFilter implements Filter {

    public void destroy() {
        System.out.println("销毁过滤器对象");
    }

    public void doFilter(ServletRequest req, ServletResponse resp,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        HttpSession hs = request.getSession();
        //在session 中获得用户对象
        User user = (User) hs.getAttribute("user");
        //检查用户是否存在，以判断用户是否登录
        if(user != null){
            chain.doFilter(request, response);
        }else {
            //如果用户不存在，转到登录界面
            // TODO 如果用户不存在跳转登陆界面
            // response.sendRedirect(request.getContextPath()+"/user/tologin?info=3");
            ((HttpServletResponse) resp).sendRedirect(((HttpServletRequest) req).getContextPath()
                    +"/book/index");
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}