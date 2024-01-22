package com.yangsx95.consul.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class ServiceController {

    @Autowired
    private LoadBalancerClient loadBalancer;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 获取名称为producer的服务
     */
    @RequestMapping("/services")
    public Object services() {
        return discoveryClient.getInstances("producer");
    }

    /**
     * 负载均衡(轮询)的方式选择一个名称为producer的服务
     */
    @RequestMapping("/discover")
    public Object discover() {
        return loadBalancer.choose("producer").getUri().toString();
    }

    @GetMapping("/invoke/hello")
    public String invokeHello() {
        String uri = loadBalancer.choose("producer").getUri() + "/hello";
        return restTemplate.getForObject(uri, String.class);
    }
}
