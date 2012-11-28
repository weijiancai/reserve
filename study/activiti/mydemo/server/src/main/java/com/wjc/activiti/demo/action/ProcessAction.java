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
        String processEngineName, deploymentId, processDefineId, processInstanceId;

        switch (method) {
            case "getImage":
                res.setContentType("image/png");
                InputStream is = ProcessBO.getProcessImage();
                PrintWriter pw = res.getWriter();
                int i;
                while ((i = is.read()) != -1) {
                    pw.write(i);
                }
                pw.flush();
                pw.close();
                break;
            case "startProcess":
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
                break;
            case "getProcessDefXml":
                String xml = ProcessBO.getProcessDefXml();
                sendXml(res, xml);
                break;
            case "getProcessEngines":
//                Map<String, Object> map = new HashMap<>();
//                map.put("lists", ProcessBO.getProcessEngines());
//                sendXml(res, XML.toString(new JSONObject(map)));
                try {
                    sendXml(res, JaxbUtil.marshalList(ProcessBO.getProcessEngines(), ProcessEngineBean.class));
                } catch (JAXBException e) {
                    e.printStackTrace();
                }
                break;
            case "getProcessEngineConfiguration":
                processEngineName = req.getParameter("processEngineName");
                try {
                    sendXml(res, JaxbUtil.marshalList(ProcessBO.getProcessEngineConfiguration(processEngineName), ProcessEngineConfigurationBean.class));
                } catch (JAXBException e) {
                    e.printStackTrace();
                }
                break;
            case "getProcessDefine":
                processEngineName = req.getParameter("processEngineName");
                deploymentId = req.getParameter("deploymentId");
                try {
                    sendXml(res, JaxbUtil.marshalList(ProcessBO.getProcessDefine(processEngineName, deploymentId), ProcessDefineBean.class));
                } catch (JAXBException e) {
                    e.printStackTrace();
                }
                break;
            case "getDeployment":
                processEngineName = req.getParameter("processEngineName");
                try {
                    sendXml(res, JaxbUtil.marshalList(ProcessBO.getDeployment(processEngineName), DeploymentBean.class));
                } catch (JAXBException e) {
                    e.printStackTrace();
                }
                break;
            case "getProcessInstance":
                processEngineName = req.getParameter("processEngineName");
                processDefineId = URLDecoder.decode(req.getParameter("processDefineId"), "UTF-8");
                try {
                    sendXml(res, JaxbUtil.marshalList(ProcessBO.getProcessInstance(processEngineName, processDefineId), ProcessInstanceBean.class));
                } catch (JAXBException e) {
                    e.printStackTrace();
                }
                break;
            case "getProcessInstanceVariables":
                processEngineName = req.getParameter("processEngineName");
                processInstanceId = req.getParameter("processInstanceId");
                try {
                    sendXml(res, JaxbUtil.marshalList(ProcessBO.getProcessInstanceVariables(processEngineName, processInstanceId), Paris.class, Order.class));
                } catch (JAXBException e) {
                    e.printStackTrace();
                }
                break;
            case "getProcessInstanceTasks":
                processEngineName = req.getParameter("processEngineName");
                processInstanceId = req.getParameter("processInstanceId");
                try {
                    sendXml(res, JaxbUtil.marshalList(ProcessBO.getProcessInstanceTasks(processEngineName, processInstanceId), Paris.class, Order.class));
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
