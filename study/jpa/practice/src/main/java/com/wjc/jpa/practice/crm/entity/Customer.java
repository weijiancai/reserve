package com.wjc.jpa.practice.crm.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

/**
 * 客户实体：表示为某一个公司或组织机构，其属性有公司名称，总资产、创建日期等<br/>
 * 客户实体与地址实体是单向的一对一的关系，与联系人是双向的多对多关系，与订单式双向的一对多关系。
 *
 * @author weijiancai
 * @version 0.0.1
 */
@Entity
@Table(name = "crm_customer")
public class Customer implements Serializable {
    /** 客户ID */
    private Integer id;
    /** 客户名称 */
    private String name;
    /** 客户总资产 */
    private Double asset;
    /** 客户创建日期 */
    private Date createDate;
    /** 客户属性的地址 */
    private Address address;
    /** 客户所属的联系人 */
    private List contacts = new ArrayList();
    /** 客户所属的订单 */
    private Set<Order> orders = new HashSet<Order>();

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

    @Column(name = "asset")
    public Double getAsset() {
        return asset;
    }

    public void setAsset(Double asset) {
        this.asset = asset;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "create_date")
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "address_id")
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @ManyToOne(targetEntity = Contact.class, cascade = {CascadeType.ALL})
    @JoinTable(name = "crm_customer_contact", joinColumns = {@JoinColumn(name = "customer_id")},
        inverseJoinColumns = {@JoinColumn(name = "contact_id")})
    @OrderBy("name ASC")
    public List getContacts() {
        return contacts;
    }

    public void setContacts(List contacts) {
        this.contacts = contacts;
    }

    @OneToMany(mappedBy = "customer")
    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }
}
