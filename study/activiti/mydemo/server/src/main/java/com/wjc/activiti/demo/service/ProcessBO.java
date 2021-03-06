package com.wjc.activiti.demo.service;

import com.wjc.activiti.demo.bean.*;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricDetail;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.activiti.engine.impl.ProcessEngineImpl;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.bpmn.diagram.ProcessDiagramGenerator;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.util.IoUtil;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;

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

    public static InputStream getProcessImage(String processEngineName, String processDefineId) {
        ProcessDefinition processDefinition;
        if (processEngineName != null && processDefineId != null) {
            ProcessEngine processEngine = ProcessEngines.getProcessEngine(processEngineName);
            processDefinition = processEngine.getRepositoryService().getProcessDefinition(processDefineId);
        } else {
            processDefinition = getRepositoryService().createProcessDefinitionQuery().processDefinitionKey(PROCESS_KEY).latestVersion().singleResult();
        }

        String diagramResourceName = processDefinition.getDiagramResourceName();
        return getRepositoryService().getResourceAsStream(processDefinition.getDeploymentId(), diagramResourceName);
    }

    /**
     * 获得流程定义xml文件
     *
     * @return 返回流程定义xml文件内容
     * @param processEngineName
     * @param processDefineId
     */
    public static String getProcessDefXml(String processEngineName, String processDefineId) throws UnsupportedEncodingException {
        ProcessDefinition processDefinition;
        if (processEngineName != null && processDefineId != null) {
            ProcessEngine processEngine = ProcessEngines.getProcessEngine(processEngineName);
            processDefinition = processEngine.getRepositoryService().getProcessDefinition(processDefineId);
        } else {
            processDefinition = getRepositoryService().createProcessDefinitionQuery().processDefinitionKey(PROCESS_KEY).latestVersion().singleResult();
        }
        String xmlResourceName = processDefinition.getResourceName();
        InputStream is = getRepositoryService().getResourceAsStream(processDefinition.getDeploymentId(), xmlResourceName);
        byte[] bytes = IoUtil.readInputStream(is, xmlResourceName);
        return new String(bytes, "UTF-8");
    }

    public static String startProcess(String processEngineName, String processDefineId, Map<String, Object> params) {
        System.out.println("启动流程 > " + processDefineId);
        ProcessEngine processEngine = ProcessEngines.getProcessEngine(processEngineName);
        ProcessInstance processInstance = processEngine.getRuntimeService().startProcessInstanceById(processDefineId, params);
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
            bean.setProcessDefineBeanList(getProcessDefine(processEngine.getName(), null));
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
        List<ProcessDefinition> lists;
        if (deploymentId == null) {
            lists = repositoryService.createProcessDefinitionQuery().orderByProcessDefinitionName().orderByProcessDefinitionVersion().desc().list();
        } else {
            lists = repositoryService.createProcessDefinitionQuery().deploymentId(deploymentId).orderByProcessDefinitionName().orderByProcessDefinitionVersion().desc().list();
        }

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

    public static List<ProcessDefineBean> getProcessDefinitionById(String processEngineName, String processDefinitionId) {
        List<ProcessDefineBean> result = new ArrayList<>();

        ProcessEngine processEngine = ProcessEngines.getProcessEngine(processEngineName);
        RepositoryService repositoryService = processEngine.getRepositoryService();
        List<ProcessDefinition> lists = repositoryService.createProcessDefinitionQuery().processDefinitionId(processDefinitionId).orderByProcessDefinitionName().orderByProcessDefinitionVersion().desc().list();

        ProcessDefineBean bean;
        Deployment deployment;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
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
            deployment = getDeploymentById(processEngineName, bean.getDeploymentId());
            bean.setDeploymentTime(sdf.format(deployment.getDeploymentTime()));

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

    public static Deployment getDeploymentById(String processEngineName, String deploymentId) {
        ProcessEngine processEngine = ProcessEngines.getProcessEngine(processEngineName);
        RepositoryService repositoryService = processEngine.getRepositoryService();

        return repositoryService.createDeploymentQuery().deploymentId(deploymentId).singleResult();
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

    public static List<TaskBean> getProcessInstanceTasks(String processEngineName, String processInstanceId) {
        List<TaskBean> result = new ArrayList<>();

        ProcessEngine processEngine = ProcessEngines.getProcessEngine(processEngineName);
        TaskService taskService = processEngine.getTaskService();
        List<Task> lists = taskService.createTaskQuery().processInstanceId(processInstanceId).orderByTaskCreateTime().desc().list();

        convertToTaskBean(result, lists);

        return result;
    }

    private static void convertToTaskBean(List<TaskBean> result, List<Task> lists) {
        TaskBean bean;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for(Task task : lists) {
            bean = new TaskBean();
            bean.setId(task.getId());
            bean.setName(task.getName());
            bean.setDescription(task.getDescription());
            bean.setPriority(task.getPriority());
            bean.setOwner(task.getOwner());
            bean.setAssignee(task.getAssignee());
            bean.setCreateTime(sdf.format(task.getCreateTime()));
            if (task.getDueDate() != null) {
                bean.setDueDate(sdf.format(task.getDueDate()));
            }
            if (task.getDelegationState() != null) {
                bean.setDelegationState(task.getDelegationState().name());
            }

            bean.setTaskDefinitionKey(task.getTaskDefinitionKey());
            bean.setExecutionId(task.getExecutionId());
            bean.setProcessInstanceId(task.getProcessInstanceId());
            bean.setProcessDefinitionId(task.getProcessDefinitionId());
            bean.setParentTaskId(task.getParentTaskId());

            result.add(bean);
        }
    }

    public static List<Paris> getTaskVariables(String processEngineName, String taskId) {
        List<Paris> result = new ArrayList<>();

        ProcessEngine processEngine = ProcessEngines.getProcessEngine(processEngineName);
        TaskService taskService = processEngine.getTaskService();
        Map<String, Object> map = taskService.getVariables(taskId);
        if (map != null) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                result.add(new Paris(entry.getKey(), entry.getValue()));
            }
        }

        return result;
    }

    public static List<HistoricActivityInstanceBean> getHistoricActivityInstance(String processEngineName, String processInstanceId) {
        List<HistoricActivityInstanceBean> result = new ArrayList<>();

        ProcessEngine processEngine = ProcessEngines.getProcessEngine(processEngineName);
        HistoryService historyService = processEngine.getHistoryService();
        List<HistoricActivityInstance> lists = historyService.createHistoricActivityInstanceQuery().processInstanceId(processInstanceId).list();

        HistoricActivityInstanceBean bean;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (HistoricActivityInstance instance : lists) {
            bean = new HistoricActivityInstanceBean();
            bean.setId(instance.getId());
            bean.setActivityId(instance.getActivityId());
            bean.setActivityName(instance.getActivityName());
            bean.setActivityType(instance.getActivityType());
            bean.setAssignee(instance.getAssignee());
            if (instance.getDurationInMillis() != null) {
                bean.setDurationInMillis(instance.getDurationInMillis());
            }
            if (instance.getStartTime() != null) {
                bean.setStartTime(sdf.format(instance.getStartTime()));
            }
            if (instance.getEndTime() != null) {
                bean.setEndTime(sdf.format(instance.getEndTime()));
            }
            bean.setExecutionId(instance.getExecutionId());
            bean.setProcessDefinitionId(instance.getProcessDefinitionId());
            bean.setProcessInstanceId(instance.getProcessInstanceId());

            result.add(bean);
        }

        return result;
    }

    public static List<HistoricProcessInstanceBean> getHistoricProcessInstance(String processEngineName, String processInstanceId) {
        List<HistoricProcessInstanceBean> result = new ArrayList<>();

        ProcessEngine processEngine = ProcessEngines.getProcessEngine(processEngineName);
        HistoryService historyService = processEngine.getHistoryService();
        List<HistoricProcessInstance> lists = historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstanceId).list();

        for (HistoricProcessInstance instance : lists) {
            result.add(convertToHistoricProcessInstanceBean(instance));
        }

        return result;
    }

    public static List<HistoricProcessInstanceBean> getHistoricProcessInstanceByProcessDefineId(String processEngineName, String processDefineId) {
        List<HistoricProcessInstanceBean> result = new ArrayList<>();

        ProcessEngine processEngine = ProcessEngines.getProcessEngine(processEngineName);
        HistoryService historyService = processEngine.getHistoryService();
        List<HistoricProcessInstance> lists = historyService.createHistoricProcessInstanceQuery().processDefinitionId(processDefineId).list();

        for (HistoricProcessInstance instance : lists) {
            result.add(convertToHistoricProcessInstanceBean(instance));
        }

        return result;
    }

    private static HistoricProcessInstanceBean convertToHistoricProcessInstanceBean(HistoricProcessInstance instance) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        HistoricProcessInstanceBean bean = new HistoricProcessInstanceBean();

        bean.setId(instance.getId());
        bean.setBusinessKey(instance.getBusinessKey());
        bean.setProcessDefinitionId(instance.getProcessDefinitionId());
        bean.setStartActivityId(instance.getStartActivityId());
        bean.setStartUserId(instance.getStartUserId());
        if (instance.getStartTime() != null) {
            bean.setStartTime(sdf.format(instance.getStartTime()));
        }
        if (instance.getEndTime() != null) {
            bean.setEndTime(sdf.format(instance.getEndTime()));
        }
        if (instance.getDurationInMillis() != null) {
            bean.setDurationInMills(instance.getDurationInMillis());
        }
        bean.setSupperProcessInstanceId(instance.getSuperProcessInstanceId());
        bean.setDeleteReason(instance.getDeleteReason());

        return bean;
    }

    public static List<HistoricTaskInstanceBean> getHistoricTaskInstance(String processEngineName, String processInstanceId) {
        List<HistoricTaskInstanceBean> result = new ArrayList<>();

        ProcessEngine processEngine = ProcessEngines.getProcessEngine(processEngineName);
        HistoryService historyService = processEngine.getHistoryService();
        List<HistoricTaskInstance> lists = historyService.createHistoricTaskInstanceQuery().processInstanceId(processInstanceId).list();

        HistoricTaskInstanceBean bean;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (HistoricTaskInstance instance : lists) {
            bean = new HistoricTaskInstanceBean();
            bean.setId(instance.getId());
            bean.setName(instance.getName());
            bean.setOwner(instance.getOwner());
            bean.setDescription(instance.getDescription());
            bean.setAssignee(instance.getAssignee());
            bean.setPriority(instance.getPriority());
            if (instance.getStartTime() != null) {
                bean.setStartTime(sdf.format(instance.getStartTime()));
            }
            if (instance.getEndTime() != null) {
                bean.setEndTime(sdf.format(instance.getEndTime()));
            }
            if (instance.getDueDate() != null) {
                bean.setDueDate(sdf.format(instance.getDueDate()));
            }
            if (instance.getDurationInMillis() != null) {
                bean.setDurationInMills(instance.getDurationInMillis());
            }
            bean.setExecutionId(instance.getExecutionId());
            bean.setProcessDefinitionId(instance.getProcessDefinitionId());
            bean.setProcessInstanceId(instance.getProcessInstanceId());
            bean.setTaskDefinitionKey(instance.getTaskDefinitionKey());
            bean.setParentTaskId(instance.getParentTaskId());
            bean.setDeleteReason(instance.getDeleteReason());

            result.add(bean);
        }

        return result;
    }

    public static List<HistoricDetailBean> getHistoricDetail(String processEngineName, String processInstanceId) {
        List<HistoricDetailBean> result = new ArrayList<>();

        ProcessEngine processEngine = ProcessEngines.getProcessEngine(processEngineName);
        HistoryService historyService = processEngine.getHistoryService();
        List<HistoricDetail> lists = historyService.createHistoricDetailQuery().processInstanceId(processInstanceId).list();

        HistoricDetailBean bean;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (HistoricDetail detail : lists) {
            bean = new HistoricDetailBean();
            bean.setId(detail.getId());
            bean.setExecutionId(detail.getExecutionId());
            bean.setProcessInstanceId(detail.getProcessInstanceId());
            bean.setActivityInstanceId(detail.getActivityInstanceId());
            bean.setTaskId(detail.getTaskId());
            if (detail.getTime() != null) {
                bean.setTime(sdf.format(detail.getTime()));
            }

            result.add(bean);
        }

        return result;
    }

    public static void startProcessByProcessDefineId(String processEngineName, String processDefineId) {
        ProcessEngine processEngine = ProcessEngines.getProcessEngine(processEngineName);
        RuntimeService runtimeService = processEngine.getRuntimeService();
        runtimeService.startProcessInstanceById(processDefineId);
    }

    public static List<ExecutionBean> getProcessInstanceExecution(String processEngineName, String processInstanceId) {
        List<ExecutionBean> result = new ArrayList<>();

        ProcessEngine processEngine = ProcessEngines.getProcessEngine(processEngineName);
        RuntimeService runtimeService = processEngine.getRuntimeService();
        List<Execution> list = runtimeService.createExecutionQuery().processInstanceId(processInstanceId).list();

        ExecutionBean bean;
        for (Execution execution : list) {
            bean = new ExecutionBean();
            bean.setId(execution.getId());
            bean.setProcessInstanceId(execution.getProcessInstanceId());
            bean.setEnded(execution.isEnded());

            result.add(bean);
        }

        return result;
    }

    public static List<GroupBean> getGroups(String processEngineName) {
        List<GroupBean> result = new ArrayList<>();

        ProcessEngine processEngine = ProcessEngines.getProcessEngine(processEngineName);
        List<Group> list = processEngine.getIdentityService().createGroupQuery().list();

        GroupBean bean;
        for (Group group : list) {
            bean = new GroupBean();
            bean.setId(group.getId());
            bean.setName(group.getName());
            bean.setType(group.getType());
            result.add(bean);
        }
        return result;
    }

    public static List<UserBean> getUsers(String processEngineName, String groupId) {
        List<UserBean> result = new ArrayList<>();

        ProcessEngine processEngine = ProcessEngines.getProcessEngine(processEngineName);
        List<User> list;
        if (groupId == null) {
            list = processEngine.getIdentityService().createUserQuery().list();
        } else {
            list = processEngine.getIdentityService().createUserQuery().memberOfGroup(groupId).list();
        }

        UserBean bean;
        for (User user : list) {
            bean = new UserBean();
            bean.setId(user.getId());
            bean.setFirstName(user.getFirstName());
            bean.setLastName(user.getLastName());
            bean.setPassword(user.getPassword());
            bean.setEmail(user.getEmail());
            result.add(bean);
        }

        return result;
    }

    public static InputStream getProcessActiveImage(String processEngineName, String processInstanceId) {
        ProcessEngine processEngine = ProcessEngines.getProcessEngine(processEngineName);
        RepositoryService repositoryService = processEngine.getRepositoryService();
        ProcessInstance processInstance = processEngine.getRuntimeService().createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
        ProcessDefinitionEntity processDefinition = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService).getDeployedProcessDefinition(processInstance.getProcessDefinitionId());

        return ProcessDiagramGenerator.generateDiagram(processDefinition, "png", processEngine.getRuntimeService().getActiveActivityIds(processInstanceId));
    }

    public static List<TaskBean> getGroupTasks(String processEngineName, String processInstanceId, String groupId) {
        List<TaskBean> result = new ArrayList<>();

        ProcessEngine processEngine = ProcessEngines.getProcessEngine(processEngineName);
        TaskService taskService = processEngine.getTaskService();
        TaskQuery query = taskService.createTaskQuery();
        if (processInstanceId != null) {
            query.processInstanceId(processInstanceId);
        }
        query.taskCandidateGroup(groupId);
        List<Task> lists = query.orderByTaskCreateTime().desc().list();

        convertToTaskBean(result, lists);

        return result;
    }

    public static List<TaskBean> getUserTasks(String processEngineName, String processInstanceId, String userId) {
        List<TaskBean> result = new ArrayList<>();

        ProcessEngine processEngine = ProcessEngines.getProcessEngine(processEngineName);
        TaskService taskService = processEngine.getTaskService();
        TaskQuery query = taskService.createTaskQuery();
        if (processInstanceId != null) {
            query.processInstanceId(processInstanceId);
        }
        query.taskAssignee(userId);
        List<Task> lists = query.orderByTaskCreateTime().desc().list();

        query = taskService.createTaskQuery();
        if (processInstanceId != null) {
            query.processInstanceId(processInstanceId);
        }
        query.taskCandidateUser(userId);
        lists.addAll(query.orderByTaskCreateTime().desc().list());

        convertToTaskBean(result, lists);

        return result;
    }

    public static void assignUserToTask(String processEngineName, String processDefineId, String userId, String taskDefineKey) {
        ProcessEngine processEngine = ProcessEngines.getProcessEngine(processEngineName);
        TaskService taskService = processEngine.getTaskService();
        List<Task> list = taskService.createTaskQuery().processDefinitionId(processDefineId).taskDefinitionKey(taskDefineKey).list();
        for (Task task : list) {
            taskService.setAssignee(task.getId(), userId);
        }
    }

    public static void addCandidateUserToTask(String processEngineName, String processDefineId, String userId, String taskDefineKey) {
        ProcessEngine processEngine = ProcessEngines.getProcessEngine(processEngineName);
        TaskService taskService = processEngine.getTaskService();
        List<Task> list = taskService.createTaskQuery().processDefinitionId(processDefineId).taskDefinitionKey(taskDefineKey).list();
        for (Task task : list) {
            taskService.addCandidateUser(task.getId(), userId);
        }
    }

    public static void addCandidateGroupToTask(String processEngineName, String processDefineId, String groupId, String taskDefineKey) {
        ProcessEngine processEngine = ProcessEngines.getProcessEngine(processEngineName);
        TaskService taskService = processEngine.getTaskService();
        List<Task> list = taskService.createTaskQuery().processDefinitionId(processDefineId).taskDefinitionKey(taskDefineKey).list();
        for (Task task : list) {
            taskService.addCandidateGroup(task.getId(), groupId);
        }
    }

    public static void deployProcess(String processEngineName, String fileName, String processDefineXml) {
        ProcessEngine processEngine = ProcessEngines.getProcessEngine(processEngineName);
        processEngine.getRepositoryService().createDeployment().addString(fileName, processDefineXml).deploy();
    }
}
