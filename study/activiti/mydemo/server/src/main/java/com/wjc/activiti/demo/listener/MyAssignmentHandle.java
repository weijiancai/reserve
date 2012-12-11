package com.wjc.activiti.demo.listener;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

/**
 * @author weijiancai
 * @version 0.0.1
 */
public class MyAssignmentHandle implements TaskListener {
    @Override
    public void notify(DelegateTask delegateTask) {
        // 执行自定义的身份查找

        // 接下来，例如调用以下方法：
        delegateTask.setAssignee("gonzo");
        delegateTask.addCandidateUser("fozzie");
        delegateTask.addCandidateGroup("management");
    }
}
