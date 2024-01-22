package com.yangsx95.demo.sqlattack.interceptor;

import com.yangsx95.demo.sqlattack.SqlInjectCheck;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Enumeration;

/**
 * @author Cheri
 * @title: SqlInjectInterceptor
 * @projectName demo
 * @description: TODO
 * @date 2019/6/21 22:54
 */
public class SqlInjectInterceptor implements HandlerInterceptor {

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception arg3)
            throws Exception {

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView arg3)
            throws Exception {

    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("============[preHandle]=>");
        if (handler instanceof HandlerMethod) {
            final HandlerMethod handlerMethod = (HandlerMethod) handler;
            final Class<?> clazz = handlerMethod.getBeanType();
            final Method method = handlerMethod.getMethod();
            if (clazz.isAnnotationPresent(SqlInjectCheck.class) || method.isAnnotationPresent(SqlInjectCheck.class)) {

                if(request.getRequestURI().contains("Che    ckAcc")){
                    Enumeration<String> names = request.getParameterNames();
                    while(names.hasMoreElements()){
                        String name = names.nextElement();
                        String[] values = request.getParameterValues(name);
                        for(String value: values){
                            if(judgeXSS(value.toLowerCase())){
                                response.setContentType("text/html;charset=UTF-8");
                                response.getWriter().print("参数含有非法攻击字符,已禁止继续访问！");
                                return false;
                            }
                        }
                    }
                }

                return true;
            }

        }

        return true;
    }

    /**
     * 判断参数是否含有非法攻击字符串
     * @param value
     * @return
     */
    public boolean judgeXSS(String value){
        if(value == null || "".equals(value)){
            return false;
        }
        String xssStr = "and|or|select|update|delete|drop|truncate|%20|=|-|--|;|'|%|#|+|,|//|/| |\\|!=|(|)";
        String[] xssArr = xssStr.split("\\|");
        for(int i=0;i<xssArr.length;i++){
            if(value.contains(xssArr[i])){
                return true;
            }
        }
        return false;

    }

}