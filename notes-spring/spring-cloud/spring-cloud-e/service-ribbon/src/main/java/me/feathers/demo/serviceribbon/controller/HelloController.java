package me.feathers.demo.serviceribbon.controller;

import me.feathers.demo.serviceribbon.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.annotation.Resources;

/**
 * HelloController
 * <p>
 *
 * @author Feathers
 * @date 2018-05-31 11:10
 */
@RestController
public class HelloController {

    @Resource
    private HelloService helloService;

    @Resource
    private LoadBalancerClient loadBalancerClient;

    @GetMapping(value = "/hi")
    public String hi(@RequestParam String name) {
        return helloService.hiService(name);
    }

    @GetMapping("/log-eureka-client-instance")
    public String logEurekaClientInstance() {
        // 使用loadBalancerClient负载均衡的获取服务实例，查看实例信息
        ServiceInstance serviceInstance = loadBalancerClient.choose("EUREKA-CLIENT");
        return "serviceId: " + serviceInstance.getServiceId() + ";\n"
                + "serviceHost:" + serviceInstance.getHost() + ";\n"
                + "serviceUri:" + serviceInstance.getUri() + ";\n";
    }
}