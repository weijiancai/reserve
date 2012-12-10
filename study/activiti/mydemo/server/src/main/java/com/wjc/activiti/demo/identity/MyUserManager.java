package com.wjc.activiti.demo.identity;

import org.activiti.engine.ActivitiException;
import org.activiti.engine.identity.User;
import org.activiti.engine.identity.UserQuery;
import org.activiti.engine.impl.persistence.entity.UserManager;

/**
 * @author weijiancai
 * @version 0.0.1
 */
public class MyUserManager extends UserManager {
    @Override
    public User createNewUser(String userId) {
        throw new ActivitiException("不知此创建新用户！");
    }

    @Override
    public UserQuery createNewUserQuery() {
        return new MyUserQuery();
    }
}
