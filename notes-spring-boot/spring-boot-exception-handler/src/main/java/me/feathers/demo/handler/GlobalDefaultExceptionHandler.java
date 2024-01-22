package me.feathers.demo.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * GlobalDefaultExceptionHandler
 * 全局异常处理器
 * <p>
 *     1. 新建一个ControllerAdvice
 *     2. 添加方法并添加方法注解@ExceptionHandler
 *     3. 方法如果返回View对象，则返回ModelAndView
 *     4. 方法如果返回String或者Json，则需要在方法上添加@ResponeseBody
 *
 * @author Feathers
 * @date 2018-03-30 15:23
 */
@ControllerAdvice
public class GlobalDefaultExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public String defaultExceptionHandler(HttpServletRequest req,HttpServletResponse resp){
        // 返回string
        return "对不起，服务器繁忙，请稍后再试";
    }

/*    @ExceptionHandler(Exception.class)
    public ModelAndView defaultExceptionHandler(HttpServletRequest req, HttpServletResponse resp) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("500");
        return mv;
    }*/

}
