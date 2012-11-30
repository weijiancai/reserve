package com.wjc.activiti.demo.bean;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * 流程引擎Bean
 *
 * @author weijiancai
 * @since 0.0.1
 */
@XmlRootElement
public class ProcessEngineBean {
    private String id;
    private String name;
    private String exception;
    private String resourceUrl;
    private List<ProcessDefineBean> processDefineBeanList;

    public ProcessEngineBean() {
    }

    public ProcessEngineBean(String name) {
        this.id = name;
        this.name = name;
    }

    public ProcessEngineBean(String name, String exception, String resourceUrl) {
        this.id = name;
        this.name = name;
        this.exception = exception;
        this.resourceUrl = resourceUrl;
    }

    @XmlAttribute
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @XmlAttribute
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlAttribute
    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    @XmlAttribute
    public String getResourceUrl() {
        return resourceUrl;
    }

    public void setResourceUrl(String resourceUrl) {
        this.resourceUrl = resourceUrl;
    }

    @XmlElement(name = "ProcessDefinition")
    public List<ProcessDefineBean> getProcessDefineBeanList() {
        return processDefineBeanList;
    }

    public void setProcessDefineBeanList(List<ProcessDefineBean> processDefineBeanList) {
        this.processDefineBeanList = processDefineBeanList;
    }
}
