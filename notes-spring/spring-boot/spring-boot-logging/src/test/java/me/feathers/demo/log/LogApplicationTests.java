package me.feathers.demo.log;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LogApplicationTests {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Test
	public void contextLoads() {
	}

	@Test
	public void testColor() {
		logger.error("颜色测试-err");
		logger.info("颜色测试-info");
		logger.warn("颜色测试-warn");
		logger.debug("颜色测试-debug");
		logger.trace("颜色测试-trace");
	}

}
