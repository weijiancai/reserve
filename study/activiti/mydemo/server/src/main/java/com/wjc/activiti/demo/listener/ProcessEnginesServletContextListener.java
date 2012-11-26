package com.wjc.activiti.demo.listener; /**
 *
 * @author weijiancai
 * @version 0.0.1
 */

import com.wjc.activiti.demo.service.ProcessBO;
import org.activiti.engine.ProcessEngines;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ProcessEnginesServletContextListener implements ServletContextListener {

    // -------------------------------------------------------
    // ServletContextListener implementation
    // -------------------------------------------------------
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("contextInitialized.........");
        ProcessEngines.init();
//        ProcessBO.deploy("FinancialReportProcess.bpmn20.xml", "FinancialReportProcess.png");
        ProcessBO.deploy("条件分支与合并流程.bpmn20.xml", "条件分支与合并流程.png");
    }

    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("contextDestroyed.........");
        ProcessEngines.destroy();
        ProcessBO.getRepositoryService().deleteDeployment(ProcessBO.deployId, true);
    }
}
