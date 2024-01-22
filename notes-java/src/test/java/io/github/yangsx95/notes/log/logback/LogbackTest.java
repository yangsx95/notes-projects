package io.github.yangsx95.notes.log.logback;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogbackTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogbackTest.class);

    @Test
    public void test() {
        LOGGER.error("error");
        LOGGER.warn("warn");
        LOGGER.info("info");
        LOGGER.debug("debug");
        LOGGER.trace("trace");
    }

}
