package com.yangsx95.demo.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 请求预处理filter，请求被路由之前调用。
 * 我们可利用这种过滤器实现身份验证、在集群中选择请求的微服务、记录调试信息等。
 *
 * @author yangsx
 * @version 1.0
 * @date 2019/5/9
 */
@Slf4j
//@Component
public class PreRequestLogFilter extends ZuulFilter {

    /**
     * pre请求预处理
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 返回一个int值来指定过滤器的执行顺序，不同的过滤器允许返回相同的数字
     */
    @Override
    public int filterOrder() {
        return 1;
    }

    /**
     * 返回一个boolean值来判断该过滤器是否要执行，true表示执行，false表示不执行
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 过滤器的具体逻辑 
     * 这里打印了访问日志
     */
    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        log.info(String.format("send %s request to %s", request.getMethod(), request.getRequestURL().toString()));
        return null;
    }
}
