package io.github.yangsx95.notes.activiti7;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.ProcessEngines;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author yangshunxiang
 * @since 2024/1/16
 */
public class InitTest {

    @Test
    void initDbWithDefaultConf() {
        // 使用 getDefaultProcessEngine 方法，读取默认配置（activiti.cfg.xml）
        // 并在创建processEngine时，就会生成数据库表结构
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        Assertions.assertNotNull(processEngine);
    }

    @Test
    void initDbWithTargetConf() {
        // 使用自定义配置文件的方式创建流程引擎
        // 可以指定配置文件名称，以及bean的名字
        ProcessEngineConfiguration configuration = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml", "processEngineConfiguration");
        ProcessEngine processEngine = configuration.buildProcessEngine();
        Assertions.assertNotNull(processEngine);
    }

}
