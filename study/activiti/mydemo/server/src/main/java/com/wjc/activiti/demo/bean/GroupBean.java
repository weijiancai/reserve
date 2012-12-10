package com.wjc.activiti.demo.bean;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @author weijiancai
 * @version 0.0.1
 */
@XmlRootElement
public class GroupBean {
    private String id;
    private String name;
    private String type;

    private List<GroupBean> children;

    @XmlAttribute
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @XmlAttribute
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlAttribute
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<GroupBean> getChildren() {
        return children;
    }

    public void setChildren(List<GroupBean> children) {
        this.children = children;
    }
}
