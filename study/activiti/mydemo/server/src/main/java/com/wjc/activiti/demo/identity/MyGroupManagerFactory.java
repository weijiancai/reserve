package com.wjc.activiti.demo.identity;

import org.activiti.engine.impl.interceptor.Session;
import org.activiti.engine.impl.interceptor.SessionFactory;
import org.activiti.engine.impl.persistence.entity.GroupManager;

/**
 * @author weijiancai
 * @version 0.0.1
 */
public class MyGroupManagerFactory implements SessionFactory {
    @Override
    public Class<?> getSessionType() {
        return GroupManager.class;
    }

    @Override
    public Session openSession() {
        return new MyGroupManager();
    }
}
