package io.github.yangsx95.notes.log.jul;

import org.junit.Test;

import java.io.InputStream;
import java.util.Arrays;
import java.util.logging.*;

public class JULTests {

    @Test
    public void testQuick() {
        // 获取日志记录器
        Logger logger = Logger.getLogger("com.yangsx95.notes.javalog.JULTests");
        // 日志记录输出
        logger.info("Hello JUL！");

        // 通用方法进行日志记录
        logger.log(Level.INFO, "Hello JUL！");

        // 通过占位符方法输出变量值
        logger.log(Level.INFO, "用户信息 {0}, {1}", new Object[]{"张三", 11});
    }

    @Test
    public void testLevel() {
        Logger logger = Logger.getLogger(JULTests.class.getName());

        logger.severe("severe");
        logger.warning("warning");
        logger.info("info");
        logger.config("config");
        logger.fine("fine");
        logger.finer("finer");
        logger.finest("finest");

        // JUL 默认的日志级别为info，比info高的级别将会被输出，低得级别将会隐藏
    }

    /**
     * 日志级别不仅收Logger控制，还受Handler控制。
     * 更改日志记录器的日志级别
     */
    @Test
    public void testModifyLoggerLevel() {
        Logger logger = Logger.getLogger(JULTests.class.getName());
        logger.setLevel(Level.FINE); // 日志记录器级别

        logger.setUseParentHandlers(false);
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setFormatter(new SimpleFormatter());
        consoleHandler.setLevel(Level.ALL); // handler日志级别
        logger.addHandler(consoleHandler);

        logger.severe("severe");
        logger.warning("warning");
        logger.info("info");
        logger.config("config");
        logger.fine("fine");
        logger.finer("finer");
        logger.finest("finest");
    }

    @Test
    public void testParentLogger() {
        Logger logger = Logger.getLogger("com.yangsx95.notes.javalog.jul");
        // logger的父logger
        Logger parentLogger = Logger.getLogger("com.yangsx95.notes.javalog");
        // 根logger
        Logger rootLogger = parentLogger.getParent();

        System.out.println(logger.getParent() == parentLogger);
        System.out.println(parentLogger.getParent() == rootLogger);

        // 根logger的默认配置
        System.out.println("根Logger " + rootLogger.getClass() + " 默认日志级别：" + rootLogger.getLevel()
                + " 默认的handler:" + Arrays.toString(rootLogger.getHandlers()));

        // 子logger
        System.out.println("子Logger：" + parentLogger.getClass().getName()
                + "默认日志级别：" + parentLogger.getLevel()
                + " 默认的handler：" + Arrays.toString(parentLogger.getHandlers()));

    }

    /**
     * 使用自定义的配置文件
     */
    @Test
    public void testConfig() throws Exception {
        // 读取配置文件
        InputStream in = JULTests.class.getClassLoader().getResourceAsStream("logging.properties");
        // 获取logManager
        LogManager logManager = LogManager.getLogManager();
        // 加载配置
        logManager.readConfiguration(in);

        Logger logger = Logger.getLogger("com.yangsx95.notes.javalog.jul");
        logger.severe("severe");
        logger.warning("warning");
        logger.info("info");
        logger.config("config");
        logger.fine("fine");
        logger.finer("finer");
        logger.finest("finest");
    }
}
