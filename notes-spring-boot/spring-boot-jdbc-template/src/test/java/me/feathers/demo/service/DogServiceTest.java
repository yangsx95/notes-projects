package me.feathers.demo.service;

import me.feathers.demo.SpringBootJdbcTemplateApplicationTests;
import me.feathers.demo.bean.Dog;
import org.junit.Test;

import static org.junit.Assert.*;
import javax.annotation.Resource;

/**
 * DogServiceTest
 * <p>
 *
 * @author Feathers
 * @date 2018-04-01 20:15
 */
public class DogServiceTest extends SpringBootJdbcTemplateApplicationTests {

    @Resource
    private DogService dogService;

    @Test
    public void get() {
        Dog dog = dogService.get(1);
        assertNotNull(dog);
    }
}