package com.wjc.activiti.demo.identity;

import org.activiti.engine.identity.User;
import org.activiti.engine.identity.UserQuery;
import org.activiti.engine.impl.persistence.entity.UserEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author weijiancai
 * @version 0.0.1
 */
public class MyUserQuery implements UserQuery {
    private static List<User> userList = new ArrayList<>();
    private static Map<String, User> userMap = new HashMap<>();

    static {
        User user = new UserEntity();
        user.setId("kermit");
        user.setEmail("kermit@localhost");
        user.setFirstName("Kermit");
        user.setLastName("the Frog");
        user.setPassword("kermit");
        userList.add(user);
        userMap.put(user.getId(), user);

        user = new UserEntity();
        user.setId("gonzo");
        user.setEmail("gonzo@localhost");
        user.setFirstName("Gonzo");
        user.setLastName("the Great");
        user.setPassword("gonzo");
        userList.add(user);
        userMap.put(user.getId(), user);

        user = new UserEntity();
        user.setId("fozzie");
        user.setEmail("fozzie@localhost");
        user.setFirstName("Fozzie");
        user.setLastName("Bear");
        user.setPassword("fozzie");
        userList.add(user);
        userMap.put(user.getId(), user);
    }

    private List<User> currentUserList;

    public MyUserQuery() {
        currentUserList = new ArrayList<>(userList);
    }

    @Override
    public UserQuery userId(String id) {
        List<User> list = new ArrayList<>();
        for (User user : currentUserList) {
            if (user.getId().equals(id)) {
                list.add(user);
            }
        }
        currentUserList = list;
        return this;
    }

    @Override
    public UserQuery userFirstName(String firstName) {
        List<User> list = new ArrayList<>();
        for (User user : currentUserList) {
            if (user.getFirstName().equals(firstName)) {
                list.add(user);
            }
        }
        currentUserList = list;
        return this;
    }

    @Override
    public UserQuery userFirstNameLike(String firstNameLike) {
        List<User> list = new ArrayList<>();
        for (User user : currentUserList) {
            if (user.getFirstName().contains(firstNameLike)) {
                list.add(user);
            }
        }
        currentUserList = list;
        return this;
    }

    @Override
    public UserQuery userLastName(String lastName) {
        List<User> list = new ArrayList<>();
        for (User user : currentUserList) {
            if (user.getLastName().equals(lastName)) {
                list.add(user);
            }
        }
        currentUserList = list;
        return this;
    }

    @Override
    public UserQuery userLastNameLike(String lastNameLike) {
        List<User> list = new ArrayList<>();
        for (User user : currentUserList) {
            if (user.getLastName().contains(lastNameLike)) {
                list.add(user);
            }
        }
        currentUserList = list;
        return this;
    }

    @Override
    public UserQuery userEmail(String email) {
        List<User> list = new ArrayList<>();
        for (User user : currentUserList) {
            if (user.getEmail().equals(email)) {
                list.add(user);
            }
        }
        currentUserList = list;
        return this;
    }

    @Override
    public UserQuery userEmailLike(String emailLike) {
        List<User> list = new ArrayList<>();
        for (User user : currentUserList) {
            if (user.getEmail().contains(emailLike)) {
                list.add(user);
            }
        }
        currentUserList = list;
        return this;
    }

    @Override
    public UserQuery memberOfGroup(String groupId) {
        List<User> list = new ArrayList<>();
        for (String userId : MemberShip.getMembers(groupId)) {
            User user = userMap.get(userId);
            if (user != null) {
                list.add(user);
            }
        }
        currentUserList = list;
        return this;
    }

    @Override
    public UserQuery potentialStarter(String procDefId) {
        return this;
    }

    @Override
    public UserQuery orderByUserId() {
        return this;
    }

    @Override
    public UserQuery orderByUserFirstName() {
        return this;
    }

    @Override
    public UserQuery orderByUserLastName() {
        return this;
    }

    @Override
    public UserQuery orderByUserEmail() {
        return this;
    }

    @Override
    public UserQuery asc() {
        return this;
    }

    @Override
    public UserQuery desc() {
        return this;
    }

    @Override
    public long count() {
        return userList.size();
    }

    @Override
    public User singleResult() {
        if (currentUserList.size() > 0) {
            return currentUserList.get(0);
        }
        return null;
    }

    @Override
    public List<User> list() {
        return currentUserList;
    }

    @Override
    public List<User> listPage(int firstResult, int maxResults) {
        return currentUserList.subList(firstResult < 0 ? 0 : firstResult, maxResults > currentUserList.size() - 1 ? currentUserList.size() : maxResults);
    }
}
