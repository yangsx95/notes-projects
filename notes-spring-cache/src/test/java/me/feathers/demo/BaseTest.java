package me.feathers.demo;


import junit.framework.TestCase;
import me.feathers.demo.config.CacheConfig;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CacheConfig.class)
public class BaseTest extends TestCase {
}
