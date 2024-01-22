package me.feathers.online.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * 监听ServletContext，获取全局上下文路径，保存在application域中
 * @author Feathers
 *
 */
@WebListener
public class CreatePathListener implements ServletContextListener{

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        context.setAttribute("path", context.getContextPath());
//        System.out.println("当前类--CreatePathListener,"+context.getContextPath());
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        
    }
}
