package io.github.yangsx95.notes.activiti7;

import org.activiti.engine.*;
import org.activiti.engine.task.Task;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;

/**
 * 流程变量的使用
 *
 * @author yangshunxiang
 * @since 2024/2/18
 */
public class VarTest {
    /**
     * 大于三天的走总经理审批（赵六）   小于等于三天的走人事审批（王五）
     * 在任务发起时设置流程变量
     * 在任务complete时设置流程变量
     */
    @Test
    public void varGlobalTest() {
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
        // 创建部署一个请假申请单流程
        RepositoryService repositoryService = engine.getRepositoryService();

        repositoryService.createDeployment()
                .name("请假申请-assignee-大于三天判断")
                .addClasspathResource("bpmn/leave-var.bpmn20.xml")
                .deploy();

        RuntimeService runtimeService = engine.getRuntimeService();

        int days = 10;

        String processInstanceId = runtimeService
                // 第三个参数就是uel的取值
                // 这些值都会存储到关联表ACT_RU_VARIABLE中
                .startProcessInstanceByKey("leave-var", "测测请假单id109"
//                        , new HashMap<String, Object>() {{
//                            put("days", days);
//                        }}
                )
                .getId();

        // 查询流程的所有任务
        TaskService taskService = engine.getTaskService();
        Task task = taskService.createTaskQuery()
                .processInstanceId(processInstanceId)
                .singleResult();
        Assertions.assertEquals(task.getAssignee(), "zhangsan");
        taskService.complete(task.getId());


        task = taskService.createTaskQuery()
                .processInstanceId(processInstanceId)
                .singleResult();
        Assertions.assertEquals(task.getAssignee(), "lisi");

//        taskService.complete(task.getId());

        // lisi 审核时，设置流程变量，给下一步使用ø
//        taskService.complete(task.getId(), new HashMap<String,Object>(){{
//            put("days", days);
//        }});

        // 上面的写法也可以改为
        taskService.setVariables(task.getId(), new HashMap<String, Object>() {{
            put("days", days);
        }});
        taskService.complete(task.getId());

        task = taskService.createTaskQuery()
                .processInstanceId(processInstanceId)
                .singleResult();
        if (days <= 3) {
            Assertions.assertEquals(task.getAssignee(), "wangwu");
        } else {
            Assertions.assertEquals(task.getAssignee(), "zhaoliu");
        }
    }

    /**
     * 使用local变量，这个变量在当前任务有效，但是到了下个任务，就是无效的了
     * 1. 在任务办理之前设置
     */
    @Test
    public void varLocalTest() {
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
        // 创建部署一个请假申请单流程
        RepositoryService repositoryService = engine.getRepositoryService();

        repositoryService.createDeployment()
                .name("请假申请-assignee-大于三天判断")
                .addClasspathResource("bpmn/leave-var.bpmn20.xml")
                .deploy();

        RuntimeService runtimeService = engine.getRuntimeService();


        String processInstanceId = runtimeService
                // 第三个参数就是uel的取值
                // 这些值都会存储到关联表ACT_RU_VARIABLE中
                .startProcessInstanceByKey("leave-var", "测测请假单id109")
                .getId();

        // 查询流程的所有任务
        TaskService taskService = engine.getTaskService();
        Task task = taskService.createTaskQuery()
                .processInstanceId(processInstanceId)
                .singleResult();
        Assertions.assertEquals(task.getAssignee(), "zhangsan");
        taskService.complete(task.getId());


        task = taskService.createTaskQuery()
                .processInstanceId(processInstanceId)
                .singleResult();
        Assertions.assertEquals(task.getAssignee(), "lisi");
        int days = 10;
        taskService.setVariables(task.getId(), new HashMap<String, Object>() {{
            put("days", days);
        }});
        taskService.complete(task.getId());

        task = taskService.createTaskQuery()
                .processInstanceId(processInstanceId)
                .singleResult();


        if (days <= 3) {
            Assertions.assertEquals(task.getAssignee(), "wangwu");
        } else {
            Assertions.assertEquals(task.getAssignee(), "zhaoliu");
        }
    }
}
