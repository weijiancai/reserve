package com.wjc.activiti.demo.bean;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 部署bean
 *
 * @author weijiancai
 * @since 0.0.1
 */
@XmlRootElement
public class DeploymentBean {
    private String id;
    private String name;
    private String category;
    private String deploymentTime;

    public DeploymentBean() {
    }

    public DeploymentBean(String id, String name, String category, String deploymentTime) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.deploymentTime = deploymentTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDeploymentTime() {
        return deploymentTime;
    }

    public void setDeploymentTime(String deploymentTime) {
        this.deploymentTime = deploymentTime;
    }
}
