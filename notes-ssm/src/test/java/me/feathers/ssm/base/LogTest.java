package me.feathers.ssm.base;

import me.feathers.ssm.AbsBaseTest;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * LogTest
 * <p>
 *
 * @author Feathers
 * @date 2018-05-27 13:48
 */
public class LogTest extends AbsBaseTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    public void err() {
        logger.error("测试错误日志");
    }

    @Test
    public void info() {
        logger.info("测试信息日志");
    }
}
