package io.github.yangsx95.notes.log.jcl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

public class JCLTest {

    @Test
    public void testQuick() {
        Log log = LogFactory.getLog(JCLTest.class);
        log.fatal("fatal");
        log.error("error");
        log.warn("warn");
        log.info("info");
        log.debug("debug");
    }
}
