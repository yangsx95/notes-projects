package io.github.yangsx95.notes.activiti7;

import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * @author yangshunxiang
 * @since 2024/1/17
 */
@Slf4j
public class SampleTest {

    public static String DEPLOY_KEY = "leave";

    /**
     * 创建、部署一个流程
     */
    @Test
    void sample() {
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
        // 创建部署一个请假申请单流程
        RepositoryService repositoryService = engine.getRepositoryService();
        Deployment deployment = repositoryService.createDeployment()
                .name("请假申请单")
                .addClasspathResource("bpmn/leave.bpmn20.xml")
                .key(DEPLOY_KEY)
                .deploy();
        Assertions.assertNotNull(deployment);

        // 查询创建的流程
        deployment = repositoryService.createDeploymentQuery()
                .deploymentId(deployment.getId())
                // 只查询一个结果，如果有两个，会报错，类似mybatis的selectOne方法
                .singleResult();
        Assertions.assertNotNull(deployment);
        System.out.println("部署信息： " + deployment);

        // 使用部署的流程，发起一个流程实例
        RuntimeService runtimeService = engine.getRuntimeService();
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(DEPLOY_KEY);
        Assertions.assertNotNull(processInstance);

        // 查询发起的流程实例
        processInstance = runtimeService.createProcessInstanceQuery()
                .deploymentId(deployment.getId()) // 根据部署id查询他相关的实例
                .singleResult();
        Assertions.assertNotNull(processInstance);
        System.out.println("流程实例信息：" + processInstance);

        // 查询该流程实例要完成的所有任务
        List<Task> taskList = engine.getTaskService().createTaskQuery()
                .processInstanceId(processInstance.getId())
                .list();
        Assertions.assertFalse(taskList.isEmpty());

        // 最开始系应该有一个任务，zhangsan 创建请假单
        Assertions.assertEquals(taskList.get(0).getName(), "创建请假单");

        // 查询zhangsan下的任务
        Task zhangsanTask = engine.getTaskService().createTaskQuery()
                .taskCandidateOrAssigned("zhangsan")
                .processInstanceId(processInstance.getId())
                .singleResult();
        Assertions.assertNotNull(zhangsanTask);
        Assertions.assertEquals(zhangsanTask.getName(), "创建请假单");

        // zhangsan完成任务，下一个就是lisi，部门经理审核
        engine.getTaskService().complete(zhangsanTask.getId());

        Task lisiTask = engine.getTaskService().createTaskQuery()
                .taskCandidateOrAssigned("lisi")
                .processInstanceId(processInstance.getId())
                .singleResult();
        engine.getTaskService().complete(lisiTask.getId());
        Assertions.assertNotNull(lisiTask);
        Assertions.assertEquals(lisiTask.getName(), "部门经理审核");

        Task wangwuTask = engine.getTaskService().createTaskQuery()
                .taskCandidateOrAssigned("wangwu")
                .processInstanceId(processInstance.getId())
                .singleResult();
        engine.getTaskService().complete(wangwuTask.getId());
        Assertions.assertNotNull(wangwuTask);
        Assertions.assertEquals(wangwuTask.getName(), "人事复核");

        // wangwu复核之后，该流程应该就结束了，进入到了历史表中
        HistoricProcessInstance historicProcessInstance = engine.getHistoryService().createHistoricProcessInstanceQuery().processInstanceId(processInstance.getId()).singleResult();
        Assertions.assertNotNull(historicProcessInstance);
        Assertions.assertEquals(historicProcessInstance.getProcessDefinitionName(), "请假单");
        Assertions.assertEquals(historicProcessInstance.getProcessDefinitionKey(), "leave");

        // 删除流程以及关联的流程实例，参数2会级联删除关联的其他数据
        repositoryService.deleteDeployment(deployment.getId(), true);
    }

}
