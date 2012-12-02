package com.wjc.activiti.demo.action;

import com.wjc.activiti.demo.bean.*;
import com.wjc.activiti.demo.service.OrderBO;
import com.wjc.activiti.demo.service.ProcessBO;
import com.wjc.activiti.demo.util.JaxbUtil;
import org.activiti.engine.task.Task;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author weijiancai
 * @version 0.0.1
 */
public class ProcessAction extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String method = req.getParameter("method");
        String processEngineName, deploymentId, processDefineId, processInstanceId, taskId;

        processEngineName = req.getParameter("processEngineName");
        processDefineId = req.getParameter("processDefineId");
        if (processDefineId != null) {
            processDefineId = URLDecoder.decode(processDefineId, "UTF-8");
        }
        deploymentId = req.getParameter("deploymentId");
        processInstanceId = req.getParameter("processInstanceId");
        taskId = req.getParameter("taskId");

        switch (method) {
            case "getImage":
                res.setContentType("image/png");
                InputStream is = ProcessBO.getProcessImage(processEngineName, processDefineId);
                PrintWriter pw = res.getWriter();
                int i;
                while ((i = is.read()) != -1) {
                    pw.write(i);
                }
                pw.flush();
                pw.close();
                break;
            case "startProcess":
                if (processEngineName != null && processDefineId != null) {
                    ProcessBO.startProcessByProcessDefineId(processEngineName, processDefineId);
                } else {
                    String name = req.getParameter("name");
                    String days = req.getParameter("days");
                    String desc = req.getParameter("desc");

                    // 保存单据
                    Order order = new Order(name, Integer.parseInt(days), desc);
                    OrderBO.saveOrder(order);

                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put("order", order);
                    String processId = ProcessBO.startProcess(ProcessBO.PROCESS_KEY, params);

                    List<Task> tasks = ProcessBO.getTasksByUser(processId, name);
                    for (Task task : tasks) {
                        System.out.println("完成任务： " + task.getName());
                        ProcessBO.getTaskService().complete(task.getId());
                    }
                }

                break;
            case "getProcessDefXml":
                String xml = ProcessBO.getProcessDefXml(processEngineName, processDefineId);
                sendXml(res, xml);
                break;
            case "getProcessEngines":
                try {
                    sendXml(res, JaxbUtil.marshalList(ProcessBO.getProcessEngines(), ProcessEngineBean.class));
                } catch (JAXBException e) {
                    e.printStackTrace();
                }
                break;
            case "getProcessEngineConfiguration":
                try {
                    sendXml(res, JaxbUtil.marshalList(ProcessBO.getProcessEngineConfiguration(processEngineName), ProcessEngineConfigurationBean.class));
                } catch (JAXBException e) {
                    e.printStackTrace();
                }
                break;
            case "getProcessDefine":
                try {
                    sendXml(res, JaxbUtil.marshalList(ProcessBO.getProcessDefine(processEngineName, deploymentId), ProcessDefineBean.class));
                } catch (JAXBException e) {
                    e.printStackTrace();
                }
                break;
            case "getProcessDefineById":
                try {
                    sendXml(res, JaxbUtil.marshalList(ProcessBO.getProcessDefinitionById(processEngineName, processDefineId), ProcessDefineBean.class));
                } catch (JAXBException e) {
                    e.printStackTrace();
                }
                break;
            case "getDeployment":
                try {
                    sendXml(res, JaxbUtil.marshalList(ProcessBO.getDeployment(processEngineName), DeploymentBean.class));
                } catch (JAXBException e) {
                    e.printStackTrace();
                }
                break;
            case "getProcessInstance":
                try {
                    sendXml(res, JaxbUtil.marshalList(ProcessBO.getProcessInstance(processEngineName, processDefineId), ProcessInstanceBean.class));
                } catch (JAXBException e) {
                    e.printStackTrace();
                }
                break;
            case "getProcessInstanceVariables":
                try {
                    sendXml(res, JaxbUtil.marshalList(ProcessBO.getProcessInstanceVariables(processEngineName, processInstanceId), Paris.class, Order.class));
                } catch (JAXBException e) {
                    e.printStackTrace();
                }
                break;
            case "getProcessInstanceTasks":
                try {
                    sendXml(res, JaxbUtil.marshalList(ProcessBO.getProcessInstanceTasks(processEngineName, processInstanceId), TaskBean.class));
                } catch (JAXBException e) {
                    e.printStackTrace();
                }
                break;
            case "getTaskVariables":
                try {
                    sendXml(res, JaxbUtil.marshalList(ProcessBO.getTaskVariables(processEngineName, taskId), Paris.class, Order.class));
                } catch (JAXBException e) {
                    e.printStackTrace();
                }
                break;
            case "getHistoricActivityInstance":
                try {
                    sendXml(res, JaxbUtil.marshalList(ProcessBO.getHistoricActivityInstance(processEngineName, processInstanceId), HistoricActivityInstanceBean.class));
                } catch (JAXBException e) {
                    e.printStackTrace();
                }
                break;
            case "getHistoricProcessInstance":
                try {
                    if (processDefineId != null) {
                        sendXml(res, JaxbUtil.marshalList(ProcessBO.getHistoricProcessInstanceByProcessDefineId(processEngineName, processDefineId), HistoricProcessInstanceBean.class));
                    } else {
                        sendXml(res, JaxbUtil.marshalList(ProcessBO.getHistoricProcessInstance(processEngineName, processInstanceId), HistoricProcessInstanceBean.class));
                    }
                } catch (JAXBException e) {
                    e.printStackTrace();
                }
                break;
            case "getHistoricTaskInstance":
                try {
                    sendXml(res, JaxbUtil.marshalList(ProcessBO.getHistoricTaskInstance(processEngineName, processInstanceId), HistoricTaskInstanceBean.class));
                } catch (JAXBException e) {
                    e.printStackTrace();
                }
                break;
            case "getHistoricDetail":
                try {
                    sendXml(res, JaxbUtil.marshalList(ProcessBO.getHistoricDetail(processEngineName, processInstanceId), HistoricDetailBean.class));
                } catch (JAXBException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    private void sendXml(HttpServletResponse res, String xml) throws IOException {
        res.setContentType("text/xml");
        res.setCharacterEncoding("UTF-8");
        System.out.println(xml);
        System.out.println("=============================================================================");
        res.getWriter().write(xml);
        res.getWriter().flush();
        res.getWriter().close();
    }
}
