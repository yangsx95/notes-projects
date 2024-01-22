package com.yangsx95.demo.config;

import com.yangsx95.demo.sqlattack.interceptor.SqlInjectInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author yangsx
 * @version 1.0
 * @date 2019/6/22
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SqlInjectInterceptor());
    }
    
}
