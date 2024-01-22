package com.yangsx95.demo.filter;

import com.google.common.util.concurrent.RateLimiter;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

/**
 * 使用Guava进行限流
 *
 * @author yangsx
 * @version 1.0
 * @date 2019/5/9
 */
@Component
@Slf4j
public class RateLimitZuulFilter extends ZuulFilter {

    /**
     * 5代表每秒允许5个请求
     */
    private final RateLimiter rateLimiter = RateLimiter.create(5.0);

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }

    @Override
    public boolean shouldFilter() {
        // 限流开关，可以做一个动态控制
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        try {
            log.info("请求发送时间-----");
            RequestContext currentContext = RequestContext.getCurrentContext();
            HttpServletResponse response = currentContext.getResponse();
            // 等待1s tryAcquire(1, TimeUnit.SECONDS) ，不等待 tryAcquire()
            if (!rateLimiter.tryAcquire()) {
                HttpStatus httpStatus = HttpStatus.TOO_MANY_REQUESTS;

                response.setContentType(MediaType.TEXT_PLAIN_VALUE);
                response.setStatus(httpStatus.value());
                response.getWriter().append(httpStatus.getReasonPhrase());
                // 请求不进行路由
                currentContext.setSendZuulResponse(false);
                log.error("请求被限制 {}", rateLimiter.getRate());
                return null;
                /*throw new ZuulException(
                        httpStatus.getReasonPhrase(),
                        httpStatus.value(),
                        httpStatus.getReasonPhrase()
                );*/
            }
            log.warn("请求未被限制");
        } catch (Exception e) {
            ReflectionUtils.rethrowRuntimeException(e);
        }
        return null;
    }
}
