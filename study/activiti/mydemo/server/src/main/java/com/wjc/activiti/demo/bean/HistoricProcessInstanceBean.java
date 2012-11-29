package com.wjc.activiti.demo.bean;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author weijiancai
 * @since 0.0.1
 */
@XmlRootElement
public class HistoricProcessInstanceBean {
    private String id;
    private String businessKey;
    private String processDefinitionId;
    private String startActivityId;
    private String startUserId;
    private String startTime;
    private String endTime;
    private long durationInMills;
    private String supperProcessInstanceId;
    private String deleteReason;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBusinessKey() {
        return businessKey;
    }

    public void setBusinessKey(String businessKey) {
        this.businessKey = businessKey;
    }

    public String getProcessDefinitionId() {
        return processDefinitionId;
    }

    public void setProcessDefinitionId(String processDefinitionId) {
        this.processDefinitionId = processDefinitionId;
    }

    public String getStartActivityId() {
        return startActivityId;
    }

    public void setStartActivityId(String startActivityId) {
        this.startActivityId = startActivityId;
    }

    public String getStartUserId() {
        return startUserId;
    }

    public void setStartUserId(String startUserId) {
        this.startUserId = startUserId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public long getDurationInMills() {
        return durationInMills;
    }

    public void setDurationInMills(long durationInMills) {
        this.durationInMills = durationInMills;
    }

    public String getSupperProcessInstanceId() {
        return supperProcessInstanceId;
    }

    public void setSupperProcessInstanceId(String supperProcessInstanceId) {
        this.supperProcessInstanceId = supperProcessInstanceId;
    }

    public String getDeleteReason() {
        return deleteReason;
    }

    public void setDeleteReason(String deleteReason) {
        this.deleteReason = deleteReason;
    }
}
