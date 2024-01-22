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
 * 编码过滤器
 *
 * @author Feathers
 * @create 2017-06-27-10:20
 */
@WebFilter(filterName = "encoding", urlPatterns = "/*")
public class EncodeFilter implements Filter {
    private String encoding;
    private String contentType;

    public void destroy() {
        System.out.println("销毁过滤器对象");
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        chain.doFilter(request, response);
    }

    public void init(FilterConfig config) throws ServletException {
        System.out.println("创建过滤器对象");

        encoding = config.getInitParameter("encoding");
        contentType = config.getInitParameter("contentType");

    }

}