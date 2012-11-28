package com.wjc.activiti.demo.bean;

import java.io.Serializable;

/**
 * @author weijiancai
 * @version 0.0.1
 */
public class Order implements Serializable {
    private static int count = 1;

    private int id;
    private String name;
    private int days;
    private String desc;

    public Order(String name, int days, String desc) {
        this.id = count++;
        this.name = name;
        this.days = days;
        this.desc = desc;
    }

    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        Order.count = count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
