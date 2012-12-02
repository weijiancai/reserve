package com.wjc.activiti.demo.bean;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 键值对 对象
 *
 * @author weijiancai
 * @since 0.0.1
 */
@XmlRootElement
public class Paris {
    private String key;
    private Object value;

    public Paris() {
    }

    public Paris(String key, Object value) {
        this.value = value;
        this.key = key;
    }

    @XmlAttribute
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
