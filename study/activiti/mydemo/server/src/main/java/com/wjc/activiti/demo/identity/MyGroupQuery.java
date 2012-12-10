package com.wjc.activiti.demo.identity;

import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.GroupQuery;
import org.activiti.engine.impl.persistence.entity.GroupEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author weijiancai
 * @version 0.0.1
 */
public class MyGroupQuery implements GroupQuery {
    private static List<Group> groupList = new ArrayList<>();
    private static Map<String, Group> groupMap = new HashMap<>();

    static {
        Group group = new GroupEntity();
        group.setId("admin");
        group.setName("System administrator");
        group.setType("security-role");
        groupList.add(group);
        groupMap.put(group.getId(), group);

        group = new GroupEntity();
        group.setId("user");
        group.setName("User");
        group.setType("security-role");
        groupList.add(group);
        groupMap.put(group.getId(), group);

        group = new GroupEntity();
        group.setId("manager");
        group.setName("Manager");
        group.setType("security-role");
        groupList.add(group);
        groupMap.put(group.getId(), group);

        group = new GroupEntity();
        group.setId("management");
        group.setName("Management");
        group.setType("assignment");
        groupList.add(group);
        groupMap.put(group.getId(), group);

        group = new GroupEntity();
        group.setId("accountancy");
        group.setName("Accountancy");
        group.setType("assignment");
        groupList.add(group);
        groupMap.put(group.getId(), group);

        group = new GroupEntity();
        group.setId("engineering");
        group.setName("Engineering");
        group.setType("assignment");
        groupList.add(group);
        groupMap.put(group.getId(), group);

        group = new GroupEntity();
        group.setId("sales");
        group.setName("Sales");
        group.setType("assignment");
        groupList.add(group);
        groupMap.put(group.getId(), group);

        group = new GroupEntity();
        group.setId("marketing");
        group.setName("Marketing");
        group.setType("assignment");
        groupList.add(group);
        groupMap.put(group.getId(), group);
    }

    private List<Group> currentGroupList;

    public MyGroupQuery() {
        currentGroupList = new ArrayList<>(groupList);
    }

    @Override
    public GroupQuery groupId(String groupId) {
        List<Group> list = new ArrayList<>();
        for (Group group : currentGroupList) {
            if (group.getId().equals(groupId)) {
                list.add(group);
            }
        }
        currentGroupList = list;
        return this;
    }

    @Override
    public GroupQuery groupName(String groupName) {
        List<Group> list = new ArrayList<>();
        for (Group group : currentGroupList) {
            if (group.getName().equals(groupName)) {
                list.add(group);
            }
        }
        currentGroupList = list;
        return this;
    }

    @Override
    public GroupQuery groupNameLike(String groupNameLike) {
        List<Group> list = new ArrayList<>();
        for (Group group : currentGroupList) {
            if (group.getName().contains(groupNameLike)) {
                list.add(group);
            }
        }
        currentGroupList = list;
        return this;
    }

    @Override
    public GroupQuery groupType(String groupType) {
        List<Group> list = new ArrayList<>();
        for (Group group : currentGroupList) {
            if (group.getType().equals(groupType)) {
                list.add(group);
            }
        }
        currentGroupList = list;
        return this;
    }

    @Override
    public GroupQuery groupMember(String groupMemberUserId) {
        return this;
    }

    @Override
    public GroupQuery potentialStarter(String procDefId) {
        return this;
    }

    @Override
    public GroupQuery orderByGroupId() {
        return this;
    }

    @Override
    public GroupQuery orderByGroupName() {
        return this;
    }

    @Override
    public GroupQuery orderByGroupType() {
        return this;
    }

    @Override
    public GroupQuery asc() {
        return this;
    }

    @Override
    public GroupQuery desc() {
        return this;
    }

    @Override
    public long count() {
        return groupList.size();
    }

    @Override
    public Group singleResult() {
        if (currentGroupList.size() > 0) {
            return currentGroupList.get(0);
        }
        return null;
    }

    @Override
    public List<Group> list() {
        return groupList;
    }

    @Override
    public List<Group> listPage(int firstResult, int maxResults) {
        return groupList.subList(firstResult, maxResults);
    }
}
