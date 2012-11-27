package com.wjc.activiti.demo.service;

import com.wjc.activiti.demo.bean.ProcessEngineBean;
import org.activiti.engine.*;
import org.activiti.engine.impl.util.IoUtil;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
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

    /**
     * 获得流程定义xml文件
     *
     * @return 返回流程定义xml文件内容
     */
    public static String getProcessDefXml() throws UnsupportedEncodingException {
        ProcessDefinition processDefinition = getRepositoryService().createProcessDefinitionQuery().processDefinitionKey(PROCESS_KEY).latestVersion().singleResult();
        String xmlResourceName = processDefinition.getResourceName();
        InputStream is = getRepositoryService().getResourceAsStream(processDefinition.getDeploymentId(), xmlResourceName);
        byte[] bytes = IoUtil.readInputStream(is, xmlResourceName);
        return new String(bytes, "UTF-8");
    }

    public static String startProcess(String key, Map<String, Object> params) {
        System.out.println("启动流程 > " + key);
        ProcessInstance processInstance = getRuntimeService().startProcessInstanceByKey(key, params);
        return processInstance.getId();
    }

    public static List<Task> getTasksByUser(String processId, String user) {
        return getTaskService().createTaskQuery().processInstanceId(processId).taskAssignee(user).list();
    }

    /**
     * 查询流程引擎Bean信息
     *
     * @return 返回流程引擎Bean列表
     */
    public static List<ProcessEngineBean> getProcessEngines() {
        List<ProcessEngineBean> processEngineBeans = new ArrayList<>();
        ProcessEngineBean bean;

        for (ProcessEngine processEngine : ProcessEngines.getProcessEngines().values()) {
            bean = new ProcessEngineBean(processEngine.getName());
            ProcessEngineInfo info = ProcessEngines.getProcessEngineInfo(bean.getName());
            if (null != info) {
                bean.setException(info.getException());
                bean.setResourceUrl(info.getResourceUrl());
            }
            processEngineBeans.add(bean);
        }

        return processEngineBeans;
    }
}
