package io.github.yangsx95.notes.activiti7;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

/**
 * @author yangshunxiang
 * @since 2024/2/9
 */
public class MyTaskListener implements TaskListener {

    @Override
    public void notify(DelegateTask delegateTask) {
        if (delegateTask.getName().equals("创建请假单")) {
            delegateTask.setAssignee("张三-listener");
        } else if (delegateTask.getName().equals("部门经理审核")) {
            delegateTask.setAssignee("李四-listener");
        } else if (delegateTask.getName().equals("人事复核")) {
            delegateTask.setAssignee("王五-listener");
        }
    }
}