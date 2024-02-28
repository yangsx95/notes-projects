package io.github.yangsx95.notes.activiti7;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.task.Task;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * @author yangshunxiang
 * @since 2024/2/28
 */
public class CandidateTest {

    @Test
    public void candidateUsers() {
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
        // 创建部署一个请假申请单流程
        RepositoryService repositoryService = engine.getRepositoryService();

        repositoryService.createDeployment()
                .name("请假申请-candidateusers")
                .addClasspathResource("bpmn/leave-candidateusers.bpmn20.xml")
                .deploy();

        RuntimeService runtimeService = engine.getRuntimeService();

        String processInstanceId = runtimeService
                .startProcessInstanceByKey("leave-candidateusers", "测测请假单id109")
                .getId();

        // 查询流程的所有任务
        Task task = engine.getTaskService().createTaskQuery()
                .processInstanceId(processInstanceId)
                .singleResult();
        engine.getTaskService().complete(task.getId());

        task = engine.getTaskService().createTaskQuery()
                .processInstanceId(processInstanceId)
                .singleResult();
        engine.getTaskService().complete(task.getId());

        task = engine.getTaskService().createTaskQuery()
                .processInstanceId(processInstanceId)
                .singleResult();
        // 拥有候选人的任务，如果没有设置负责人本身是没有负责人的
        Assertions.assertNull(task.getAssignee());

        // 查询zhangsan作为候选人或者责任人的任务
        List<Task> taskList = engine.getTaskService().createTaskQuery()
                .taskCandidateOrAssigned("zhangsan")
                .list();
        System.out.println(taskList);

        // 如果候选人拾取了这个任务，这个任务才会有负责人
        engine.getTaskService().claim(task.getId(), "zhangsan");

        task = engine.getTaskService().createTaskQuery()
                .processInstanceId(processInstanceId)
                .singleResult();
        // zahngsan拾取这个任务之后，任务的负责人就变为了zhangsan
        Assertions.assertEquals(task.getAssignee(), "zhangsan");

        // 归还任务
        task = engine.getTaskService().createTaskQuery()
                .taskId(task.getId())
                .taskAssignee("zhangsan")
                .singleResult();
        // zhangsan确实有这个任务，有这个任务才可归还
        Assertions.assertNotNull(task);

        // 归还任务直接将Assignee设置为空即可
        engine.getTaskService().setAssignee(task.getId(), null);

        // 或者直接对任务进行任务交接
        // 任务负责人将任务交给其他人来处理
        engine.getTaskService().setAssignee(task.getId(), "lisi");
    }

}
