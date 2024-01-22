package me.feathers.demo.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LogApplication {
    private static Logger logger = LoggerFactory.getLogger(LogApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(LogApplication.class, args);
        logger.error("颜色测试-err");
        logger.info("颜色测试-info");
        logger.warn("颜色测试-warn");
        logger.debug("颜色测试-debug");
        logger.trace("颜色测试-trace");
    }
}
