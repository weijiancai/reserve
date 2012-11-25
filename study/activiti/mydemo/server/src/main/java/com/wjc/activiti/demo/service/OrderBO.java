package com.wjc.activiti.demo.service;

import com.wjc.activiti.demo.bean.Order;

import java.util.HashMap;
import java.util.Map;

/**
 * @author weijiancai
 * @version 0.0.1
 */
public class OrderBO {
    private static Map<Integer, Order> cache = new HashMap<Integer, Order>();

    public static void saveOrder(Order order) {
        cache.put(order.getId(), order);
    }

    public static Order getOrderById(int id) {
        return cache.get(id);
    }
}
