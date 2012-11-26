package com.wjc.activiti.demo.action;

import com.wjc.activiti.demo.bean.Order;
import com.wjc.activiti.demo.service.OrderBO;
import com.wjc.activiti.demo.service.ProcessBO;
import org.activiti.engine.task.Task;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
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
                res.setContentType("text/xml");
                String xml = ProcessBO.getProcessDefXml();
                System.out.println(xml);
                res.getWriter().write(xml);
                res.getWriter().flush();
                res.getWriter().close();
                break;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
