package com.yangsx95.demo;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * spring-cloud-starter-netflix-eureka-client 已经包含 spring-cloud-starter-netfilx-ribbon ，无需添加额外的依赖
 * <p>
 * EnableCircuitBreaker 启用断路器
 * <p>
 * EnableHystrixDashboard 启用断路器可视化监控面板，可以通过http://localhost:8082/hystrix 访问，
 * > 访问后，输入stream地址，进行可视化的监控
 *
 * @author yangsx
 * @version 1.0
 * @date 2019/5/9
 */
@SpringBootApplication
@EnableCircuitBreaker
@EnableHystrixDashboard
public class ConsumerMovieApplication {

    /**
     * 引入RestTemplate，是Spring对Rest接口的请求工具类
     * <p>
     * 只要在RestTemplate上加入 @LoadBalanced，Ribbon就可以与RestTemplate整合了
     */
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    /**
     * 解决 hystrix.stream 404的问题
     * 如果访问一直ping，需要先请求一个服务
     */
    @Bean
    @SuppressWarnings("unchecked")
    public ServletRegistrationBean getServlet() {
        HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(streamServlet);
        registrationBean.setLoadOnStartup(1);
        registrationBean.addUrlMappings("/actuator/hystrix.stream");
        registrationBean.setName("HystrixMetricsStreamServlet");
        return registrationBean;
    }

    public static void main(String[] args) {
        SpringApplication.run(ConsumerMovieApplication.class, args);
    }
}