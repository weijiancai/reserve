package com.wjc.activiti.demo.bean;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 流程定义Bean
 *
 * @author weijiancai
 * @since 0.0.1
 */
@XmlRootElement
public class ProcessDefineBean {
    private String id;
    private String key;
    private String name;
    private int version;
    private String category;
    private String description;
    private String deploymentId;
    private String resourceName;
    private String diagramResourceName;
    private boolean hasStartFromKey;
    private boolean isSuspended;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDeploymentId() {
        return deploymentId;
    }

    public void setDeploymentId(String deploymentId) {
        this.deploymentId = deploymentId;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getDiagramResourceName() {
        return diagramResourceName;
    }

    public void setDiagramResourceName(String diagramResourceName) {
        this.diagramResourceName = diagramResourceName;
    }

    public boolean isHasStartFromKey() {
        return hasStartFromKey;
    }

    public void setHasStartFromKey(boolean hasStartFromKey) {
        this.hasStartFromKey = hasStartFromKey;
    }

    public boolean isSuspended() {
        return isSuspended;
    }

    public void setSuspended(boolean suspended) {
        isSuspended = suspended;
    }
}
