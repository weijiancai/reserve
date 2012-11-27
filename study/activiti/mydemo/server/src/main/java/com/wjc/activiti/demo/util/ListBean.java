package com.wjc.activiti.demo.util;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @author weijiancai
 * @since 0.0.1
 */
@XmlRootElement
public class ListBean<T> {
    private List<T> list;

    public ListBean() {}

    public ListBean(List<T> list) {
        this.list = list;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
