package com.wjc.activiti.demo.service;

import org.activiti.engine.*;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * @author weijiancai
 * @version 0.0.1
 */
public class ProcessBO {
    public static final String PROCESS_KEY = "条件分支与合并流程";
    public static String deployId = "";

    public static ProcessEngine getProcessEngine() {
        return ProcessEngines.getDefaultProcessEngine();
    }

    public static RepositoryService getRepositoryService() {
        return getProcessEngine().getRepositoryService();
    }

    public static RuntimeService getRuntimeService() {
        return getProcessEngine().getRuntimeService();
    }

    public static TaskService getTaskService() {
        return getProcessEngine().getTaskService();
    }

    public static void deploy(String... resources) {
        RepositoryService repositoryService = getRepositoryService();
        DeploymentBuilder deploymentBuilder = repositoryService.createDeployment();
        for (String resource : resources) {
            deploymentBuilder.addClasspathResource(resource);
        }
        Deployment deployment = deploymentBuilder.deploy();
        deployId = deployment.getId();
    }

    public static InputStream getProcessImage() {
        ProcessDefinition processDefinition = getRepositoryService().createProcessDefinitionQuery().processDefinitionKey(PROCESS_KEY).latestVersion().singleResult();
        String diagramResourceName = processDefinition.getDiagramResourceName();
        return getRepositoryService().getResourceAsStream(processDefinition.getDeploymentId(), diagramResourceName);
    }

    public static String startProcess(String key, Map<String, Object> params) {
        System.out.println("启动流程 > " + key);
        ProcessInstance processInstance = getRuntimeService().startProcessInstanceByKey(key, params);
        return processInstance.getId();
    }

    public static List<Task> getTasksByUser(String processId, String user) {
        return getTaskService().createTaskQuery().processInstanceId(processId).taskAssignee(user).list();
    }
}
