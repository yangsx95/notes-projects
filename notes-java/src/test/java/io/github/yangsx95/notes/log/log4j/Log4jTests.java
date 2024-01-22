package io.github.yangsx95.notes.log.log4j;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.helpers.LogLog;
import org.junit.Test;

public class Log4jTests {

    @Test
    public void testQuick() {
        // 初始化一个基本配置，从而不需要配置文件
        BasicConfigurator.configure();
        // 创建日志记录器
        Logger logger = Logger.getLogger(Log4jTests.class);

        // 日志输出，共有6种日志级别
        logger.fatal("fatal"); // 严重错误，一般会造成系统崩溃和终止运行
        logger.error("error"); // 错误信息，但不会影响系统运行
        logger.warn("warn");// 警告信息，可能会发生问题
        logger.info("info");// 程序运行信息，数据库的连接、网络、IO操作等
        logger.debug("debug");// 调试信息，一般在开发阶段使用，记录程序的变量、参 数等
        logger.trace("trace");// 追踪信息，记录程序的所有流程信息

        // log4j的默认日志级别为debug

        // 同样也提供通用的log方法
        logger.log(Level.WARN, "warn");
    }

    @Test
    public void testConfig() {
        // 创建日志记录器， 自动寻找类路径下的log4j.properties
        // 配置文件加载顺序，参考 LoggerManager
        Logger logger = Logger.getLogger(Log4jTests.class);

        logger.fatal("fatal");
        logger.error("error");
        logger.warn("warn");
        logger.info("info");
        logger.debug("debug");
        logger.trace("trace");
    }

    /**
     * 开启log4j内置日志记录
     */
    @Test
    public void testLogLog() {
        LogLog.setInternalDebugging(true);
        Logger logger = Logger.getLogger(Log4jTests.class);
        logger.info("info");
    }
}
