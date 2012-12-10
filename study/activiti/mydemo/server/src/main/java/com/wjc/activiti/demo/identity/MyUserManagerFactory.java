package com.wjc.activiti.demo.identity;

import org.activiti.engine.impl.interceptor.Session;
import org.activiti.engine.impl.interceptor.SessionFactory;
import org.activiti.engine.impl.persistence.entity.UserManager;

/**
 *
 * @author weijiancai
 * @version 0.0.1
 */
public class MyUserManagerFactory implements SessionFactory {
    @Override
    public Class<?> getSessionType() {
        return UserManager.class;
    }

    @Override
    public Session openSession() {
        return new MyUserManager();
    }
}
