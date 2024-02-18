package io.github.yangsx95.notes.activiti7;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.task.Task;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;

/**
 * 任务处理
 *
 * @author yangshunxiang
 * @since 2024/2/8
 */
public class UserTaskTest {

    @Test
    public void assigneeUel() {
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
        // 创建部署一个请假申请单流程
        RepositoryService repositoryService = engine.getRepositoryService();

        repositoryService.createDeployment()
                .name("请假申请-assignee-uel")
                .addClasspathResource("bpmn/leave.assignee-uel.bpmn20.xml")
                .deploy();

        RuntimeService runtimeService = engine.getRuntimeService();

        String processInstanceId = runtimeService
                // 第三个参数就是uel的取值
                // 这些值都会存储到关联表ACT_RU_VARIABLE中
                .startProcessInstanceByKey("leave-uel", "测测请假单id101", new HashMap<String, Object>() {{
                    put("creator", "张三");
                    put("approvator1", "李四");
                    put("approvator2", "wangwu");
                }})
                .getId();

        // 查询流程的所有任务
        List<Task> taskList = engine.getTaskService().createTaskQuery()
                .processInstanceId(processInstanceId)
                .list();
        Assertions.assertEquals(taskList.size(), 1);

        Assertions.assertEquals(taskList.get(0).getAssignee(), "张三");
    }

    @Test
    public void assigneeTaskListener() {
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
        // 创建部署一个请假申请单流程
        RepositoryService repositoryService = engine.getRepositoryService();

        repositoryService.createDeployment()
                .name("请假申请-assignee-tasklistener")
                .addClasspathResource("bpmn/leave.assignee-tasklistener.bpmn20.xml")
                .deploy();

        RuntimeService runtimeService = engine.getRuntimeService();

        String processInstanceId = runtimeService
                .startProcessInstanceByKey("leave-tasklistener", "测测请假单id102")
                .getId();

        // 查询流程的所有任务
        List<Task> taskList = engine.getTaskService().createTaskQuery()
                .processInstanceId(processInstanceId)
                .list();
        Assertions.assertEquals(taskList.size(), 1);

        Assertions.assertEquals(taskList.get(0).getAssignee(), "张三-listener");


        // 查询指定任务负责人的待办任务
        List<Task> zsTaskList = engine.getTaskService().createTaskQuery()
                .processDefinitionKey("leave-tasklistener") // 查询指定的流程定义
                .processInstanceBusinessKey("测测请假单id102") // 查询指定的业务
                .taskAssignee("张三-listener")
                .list();

        // 办理任务
        engine.getTaskService().complete(zsTaskList.get(0).getId());
    }
}
