package me.feathers.demo.serviceribbon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * HelloService
 * <p>
 *
 * @author Feathers
 * @date 2018-05-31 11:08
 */
@Service
public class HelloService {

    @Resource
    private RestTemplate restTemplate;

    public String hiService(String name) {
        return restTemplate.getForObject("http://EUREKA-CLIENT/hi?name=" + name, String.class);
    }

}