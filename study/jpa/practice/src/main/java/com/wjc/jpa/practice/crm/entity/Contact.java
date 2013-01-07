package com.wjc.jpa.practice.crm.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 联系人实体：表示为可联系的人，其属性有姓名、昵称等。<br/>
 * 联系人与客户实体是双向的多对多的关系。一个客户可以包含多个联系人，一个联系人也可以分别所属不同的客户。<br/>
 * 联系人与头像实体是双向的一对一关系，与电话实体是单向的一对多的关系。
 *
 * @author weijiancai
 * @version 0.0.1
 */
@Entity
@Table(name = "crm_contact")
public class Contact implements Serializable {
    /** 联系人ID */
    private Integer id;
    /** 联系人姓名 */
    private String name;
    /** 联系人昵称 */
    private String nickname;
    /** 联系人头像 */
    private Portrait portrait;
    /** 联系人所属客户 */
    private List<Customer> customers = new ArrayList<Customer>();
    /** 联系人的电话 */
    private List<Phone> phones = new ArrayList<Phone>();

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "nickname")
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @OneToOne
    @JoinColumn(name = "portrait_id")
    public Portrait getPortrait() {
        return portrait;
    }

    public void setPortrait(Portrait portrait) {
        this.portrait = portrait;
    }

    @ManyToMany(cascade = {CascadeType.ALL}, mappedBy = "contacts")
    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name = "contact_id")
    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }
}
