package io.github.yangsx95.notes.log.logback;

import ch.qos.logback.core.PropertyDefinerBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.UUID;

/**
 * 自定义属性，获取当前服务器ip地址
 */
public class IPLogDefiner extends PropertyDefinerBase {

    private static final Logger LOG = LoggerFactory.getLogger(IPLogDefiner.class);

    private String getUniqName() {
        String localIp = null;
        try {
            localIp = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            LOG.error("fail to get ip...", e);
        }
        String uniqName = UUID.randomUUID().toString().replace("-", "");
        if (localIp != null) {
            uniqName = localIp + "-" + uniqName;
        }
        return uniqName;
    }


    @Override
    public String getPropertyValue() {
        return getUniqName();
    }

}
