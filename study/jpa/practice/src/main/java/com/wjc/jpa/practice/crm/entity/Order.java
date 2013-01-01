package com.wjc.jpa.practice.crm.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 订单实体：表示为所购买产品的订单，其属性包含订单的创建时间、订单明细等。<br/>
 * 客户实体与订单实体是双向的一对多关系。一个客户可以有多个订单，但一个订单只属于一个客户。<br/>
 * 订单实体与订单明细实体是单向的一对多的关系
 * @author weijiancai
 * @version 0.0.1
 */
@Entity
@Table(name = "crm_order")
public class Order implements Serializable {
    /** 订单ID */
    private Integer id;
    /** 订单名称 */
    private String name;
    /** 订单创建时间 */
    private Date createTime;
    /** 订单所属的客户 */
    private Customer customer;
    /** 订单所包含的明细条目 */
    private List<OrderItem> orderItems = new ArrayList<OrderItem>();

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

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @ManyToOne
    @JoinColumn(name = "customer_id")
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name = "order_id")
    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
