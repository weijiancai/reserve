package com.wjc.activiti.demo.identity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author weijiancai
 * @version 0.0.1
 */
public class MemberShip {
    private static Map<String, List<String>> userGroupMap = new HashMap<>();

    static {
        List<String> list = new ArrayList<>();
        list.add("kermit");
        userGroupMap.put("admin", list);

        list = new ArrayList<>();
        list.add("kermit");
        userGroupMap.put("manager", list);

        list = new ArrayList<>();
        list.add("kermit");
        userGroupMap.put("management", list);

        list = new ArrayList<>();
        list.add("kermit");
        userGroupMap.put("accountancy", list);

        list = new ArrayList<>();
        list.add("kermit");
        userGroupMap.put("engineering", list);

        list = new ArrayList<>();
        list.add("kermit");
        userGroupMap.put("sales", list);

        list = new ArrayList<>();
        list.add("fozzie");
        userGroupMap.put("user", list);

        list = new ArrayList<>();
        list.add("fozzie");
        userGroupMap.put("accountancy", list);

        list = new ArrayList<>();
        list.add("gonzo");
        userGroupMap.put("manager", list);

        list = new ArrayList<>();
        list.add("gonzo");
        userGroupMap.put("management", list);

        list = new ArrayList<>();
        list.add("gonzo");
        userGroupMap.put("accountancy", list);

        list = new ArrayList<>();
        list.add("gonzo");
        userGroupMap.put("sales", list);
    }

    public static List<String> getMembers(String groupId) {
        List<String> result = userGroupMap.get(groupId);
        return result == null ? new ArrayList<String>() : result;
    }
}
