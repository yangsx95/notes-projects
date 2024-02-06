package io.github.yangsx95.notes.activiti7;

import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.*;
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
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(DEPLOY_KEY, "请假单业务id-23");
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

    /**
     * 将流程挂起、激活
     */
    @Test
    public void suspendAndActive() {
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
        // 创建部署一个请假申请单流程
        RepositoryService repositoryService = engine.getRepositoryService();
        repositoryService.createDeployment()
                .name("请假申请单")
                .addClasspathResource("bpmn/leave.bpmn20.xml")
                .key(DEPLOY_KEY)
                .deploy();

        // 创建流程实例
        ProcessInstance processInstance = engine.getRuntimeService()
                .startProcessInstanceByKey(DEPLOY_KEY, "请检单业务id-100");

        // 判断当前流程实例不是挂起的
        Assertions.assertFalse(processInstance.isSuspended());

        // 将当前这个流程实例挂起，直挂起当前流程实例
        engine.getRuntimeService().suspendProcessInstanceById(processInstance.getProcessInstanceId());

        // 挂起后，流程就是挂起状态的，这个状态需要重新查询
        processInstance = engine.getRuntimeService().createProcessInstanceQuery().processInstanceId(processInstance.getProcessInstanceId()).singleResult();
        Assertions.assertTrue(processInstance.isSuspended());

        // 重新激活流程
        engine.getRuntimeService().activateProcessInstanceById(processInstance.getProcessInstanceId());

        // 这个时候状态又变成了激活
        processInstance = engine.getRuntimeService().createProcessInstanceQuery().processInstanceId(processInstance.getProcessInstanceId()).singleResult();
        Assertions.assertFalse(processInstance.isSuspended());


        // 不仅可以针对流程实例挂起，还可以对指定的流程进行挂起
        // 被挂起的流程不能发起流程实例，以及发起的流程实例也会处于挂起状态
        engine.getRepositoryService().suspendProcessDefinitionByKey(processInstance.getProcessDefinitionKey());

        // 流程状态变为挂起时，不可以发起流程
        Assertions.assertThrowsExactly(ActivitiException.class, () -> {
            engine.getRuntimeService().startProcessInstanceByKey(DEPLOY_KEY);
        });

        // 在流程实例挂起前启动的流程实例，不会被挂起
        processInstance = engine.getRuntimeService().createProcessInstanceQuery().processInstanceId(processInstance.getProcessInstanceId()).singleResult();
        Assertions.assertFalse(processInstance.isSuspended());

    }
}
