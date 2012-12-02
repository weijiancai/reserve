package com.wjc.activiti.demo.bean;

import javax.xml.bind.annotation.XmlAttribute;
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

    @XmlAttribute
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @XmlAttribute
    public String getBusinessKey() {
        return businessKey;
    }

    public void setBusinessKey(String businessKey) {
        this.businessKey = businessKey;
    }

    @XmlAttribute
    public String getProcessDefinitionId() {
        return processDefinitionId;
    }

    public void setProcessDefinitionId(String processDefinitionId) {
        this.processDefinitionId = processDefinitionId;
    }

    @XmlAttribute
    public String getStartActivityId() {
        return startActivityId;
    }

    public void setStartActivityId(String startActivityId) {
        this.startActivityId = startActivityId;
    }

    @XmlAttribute
    public String getStartUserId() {
        return startUserId;
    }

    public void setStartUserId(String startUserId) {
        this.startUserId = startUserId;
    }

    @XmlAttribute
    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    @XmlAttribute
    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    @XmlAttribute
    public long getDurationInMills() {
        return durationInMills;
    }

    public void setDurationInMills(long durationInMills) {
        this.durationInMills = durationInMills;
    }

    @XmlAttribute
    public String getSupperProcessInstanceId() {
        return supperProcessInstanceId;
    }

    public void setSupperProcessInstanceId(String supperProcessInstanceId) {
        this.supperProcessInstanceId = supperProcessInstanceId;
    }

    @XmlAttribute
    public String getDeleteReason() {
        return deleteReason;
    }

    public void setDeleteReason(String deleteReason) {
        this.deleteReason = deleteReason;
    }
}
