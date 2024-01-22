package com.yangsx95.demo.config.security;

import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class ActuatorSecurity extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.requestMatcher(EndpointRequest.toAnyEndpoint()).authorizeRequests() // 匹配所有Endpoint请求
                .anyRequest()
                .hasRole("ENDPOINT_ADMIN") // 登录用户需要有ENDPOINT_ADMIN角色
                .and()
                .httpBasic();
    }

}