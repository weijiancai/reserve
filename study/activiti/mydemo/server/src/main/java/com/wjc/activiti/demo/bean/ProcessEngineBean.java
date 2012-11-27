package com.wjc.activiti.demo.bean;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 流程引擎Bean
 *
 * @author weijiancai
 * @since 0.0.1
 */
@XmlRootElement
public class ProcessEngineBean {
    private String name;
    private String exception;
    private String resourceUrl;

    public ProcessEngineBean() {
    }

    public ProcessEngineBean(String name) {
        this.name = name;
    }

    public ProcessEngineBean(String name, String exception, String resourceUrl) {
        this.name = name;
        this.exception = exception;
        this.resourceUrl = resourceUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public String getResourceUrl() {
        return resourceUrl;
    }

    public void setResourceUrl(String resourceUrl) {
        this.resourceUrl = resourceUrl;
    }
}
