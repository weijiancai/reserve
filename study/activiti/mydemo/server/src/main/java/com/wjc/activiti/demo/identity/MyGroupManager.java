package com.wjc.activiti.demo.identity;

import org.activiti.engine.identity.GroupQuery;
import org.activiti.engine.impl.persistence.entity.GroupManager;

/**
 * @author weijiancai
 * @version 0.0.1
 */
public class MyGroupManager extends GroupManager {
    @Override
    public GroupQuery createNewGroupQuery() {
        return new MyGroupQuery();
    }
}
