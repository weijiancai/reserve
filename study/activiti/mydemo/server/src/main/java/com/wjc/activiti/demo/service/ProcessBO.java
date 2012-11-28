package com.wjc.activiti.demo.service;

import com.wjc.activiti.demo.bean.*;
import org.activiti.engine.*;
import org.activiti.engine.impl.ProcessEngineImpl;
import org.activiti.engine.impl.util.IoUtil;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
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

    public static List<ProcessEngineConfigurationBean> getProcessEngineConfiguration(String processEngineName) {
        List<ProcessEngineConfigurationBean> list = new ArrayList<>();
        ProcessEngineImpl processEngine = (ProcessEngineImpl) ProcessEngines.getProcessEngine(processEngineName);
        ProcessEngineConfiguration configuration = processEngine.getProcessEngineConfiguration();
        ProcessEngineConfigurationBean bean = new ProcessEngineConfigurationBean();

        bean.setProcessEngineName(configuration.getProcessEngineName());

        bean.setDatabaseSchemaUpdate(configuration.getDatabaseSchemaUpdate());
        bean.setDatabaseType(configuration.getDatabaseType());

        bean.setDataSource(configuration.getDataSource());
        bean.setDataSourceJndiName(configuration.getDataSourceJndiName());

        bean.setHistory(configuration.getHistory());
        bean.setIdBlockSize(configuration.getIdBlockSize());
        bean.setTransactionsExternallyManaged(configuration.isTransactionsExternallyManaged());
        bean.setJobExecutorActivate(configuration.isJobExecutorActivate());

        bean.setJdbcDriver(configuration.getJdbcDriver());
        bean.setJdbcUrl(configuration.getJdbcUrl());
        bean.setJdbcUsername(configuration.getJdbcUsername());
        bean.setJdbcPassword(configuration.getJdbcPassword());

        bean.setJdbcMaxActiveConnections(configuration.getJdbcMaxActiveConnections());
        bean.setJdbcMaxCheckoutTime(configuration.getJdbcMaxCheckoutTime());
        bean.setJdbcMaxIdleConnections(configuration.getJdbcMaxIdleConnections());
        bean.setJdbcMaxWaitTime(configuration.getJdbcMaxWaitTime());

        bean.setJdbcPingConnectionNotUsedFor(configuration.getJdbcPingConnectionNotUsedFor());
        bean.setJdbcPingEnabled(configuration.isJdbcPingEnabled());
        bean.setJdbcPingQuery(configuration.getJdbcPingQuery());

        bean.setJpaCloseEntityManager(configuration.isJpaCloseEntityManager());
        bean.setJpaHandleTransaction(configuration.isJpaHandleTransaction());
        bean.setJpaEntityManagerFactory(configuration.getJpaEntityManagerFactory());
        bean.setJpaPersistenceUnitName(configuration.getJpaPersistenceUnitName());

        bean.setMailServerDefaultFrom(configuration.getMailServerDefaultFrom());
        bean.setMailServerHost(configuration.getMailServerHost());
        bean.setMailServerPassword(configuration.getMailServerPassword());
        bean.setMailServerPort(configuration.getMailServerPort());
        bean.setMailServerUsername(configuration.getMailServerUsername());
        bean.setMailServerUseTLS(configuration.getMailServerUseTLS());

        list.add(bean);

        return list;
    }

    public static List<ProcessDefineBean> getProcessDefine(String processEngineName, String deploymentId) {
        List<ProcessDefineBean> result = new ArrayList<>();

        ProcessEngine processEngine = ProcessEngines.getProcessEngine(processEngineName);
        RepositoryService repositoryService = processEngine.getRepositoryService();
        List<ProcessDefinition> lists = repositoryService.createProcessDefinitionQuery().deploymentId(deploymentId).list();

        ProcessDefineBean bean;
        for (ProcessDefinition processDefinition : lists) {
            bean = new ProcessDefineBean();
            bean.setId(processDefinition.getId());
            bean.setKey(processDefinition.getKey());
            bean.setName(processDefinition.getName());
            bean.setVersion(processDefinition.getVersion());
            bean.setCategory(processDefinition.getCategory());
            bean.setDescription(processDefinition.getDescription());
            bean.setDeploymentId(processDefinition.getDeploymentId());
            bean.setResourceName(processDefinition.getResourceName());
            bean.setDiagramResourceName(processDefinition.getDiagramResourceName());
            bean.setHasStartFromKey(processDefinition.hasStartFormKey());
            bean.setSuspended(processDefinition.isSuspended());

            result.add(bean);
        }

        return result;
    }

    public static List<DeploymentBean> getDeployment(String processEngineName) {
        List<DeploymentBean> result = new ArrayList<>();

        ProcessEngine processEngine = ProcessEngines.getProcessEngine(processEngineName);
        RepositoryService repositoryService = processEngine.getRepositoryService();
        List<Deployment> lists = repositoryService.createDeploymentQuery().orderByDeploymenTime().desc().list();

        DeploymentBean bean;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (Deployment deployment : lists) {
            bean = new DeploymentBean(deployment.getId(), deployment.getName(), deployment.getCategory(), sdf.format(deployment.getDeploymentTime()));
            result.add(bean);
        }

        return result;
    }

    public static List<ProcessInstanceBean> getProcessInstance(String processEngineName, String processDefineId) {
        List<ProcessInstanceBean> result = new ArrayList<>();

        ProcessEngine processEngine = ProcessEngines.getProcessEngine(processEngineName);
        RuntimeService runtimeService = processEngine.getRuntimeService();
        List<ProcessInstance> lists = runtimeService.createProcessInstanceQuery().processDefinitionId(processDefineId).orderByProcessInstanceId().desc().list();

        ProcessInstanceBean bean;
        for (ProcessInstance instance : lists) {
            bean = new ProcessInstanceBean();
            bean.setId(instance.getId());
            bean.setProcessInstanceId(instance.getProcessInstanceId());
            bean.setEnded(instance.isEnded());
            bean.setBusinessKey(instance.getBusinessKey());
            bean.setProcessDefinitionId(instance.getProcessDefinitionId());
            bean.setSuspended(instance.isSuspended());

            result.add(bean);
        }

        return result;
    }

    public static List<Paris> getProcessInstanceVariables(String processEngineName, String processInstanceId) {
        List<Paris> result = new ArrayList<>();

        ProcessEngine processEngine = ProcessEngines.getProcessEngine(processEngineName);
        RuntimeService runtimeService = processEngine.getRuntimeService();
        Map<String, Object> map = runtimeService.getVariables(processInstanceId);
        if (map != null) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                result.add(new Paris(entry.getKey(), entry.getValue()));
            }
        }

        return result;
    }

    public static void getProcessInstanceTasks(String processEngineName, String processInstanceId) {

    }
}
