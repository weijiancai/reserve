package com.wjc.activiti.demo.bean;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author weijiancai
 * @since 0.0.1
 */
@XmlRootElement
public class HistoricDetailBean {
    private String id;
    private String executionId;
    private String processInstanceId;
    private String taskId;
    private String activityInstanceId;
    private String time;

    @XmlAttribute
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @XmlAttribute
    public String getExecutionId() {
        return executionId;
    }

    public void setExecutionId(String executionId) {
        this.executionId = executionId;
    }

    @XmlAttribute
    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    @XmlAttribute
    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    @XmlAttribute
    public String getActivityInstanceId() {
        return activityInstanceId;
    }

    public void setActivityInstanceId(String activityInstanceId) {
        this.activityInstanceId = activityInstanceId;
    }

    @XmlAttribute
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
