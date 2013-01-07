package com.wjc.jpa.practice.crm.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 产品实体：表示为摸一个可以购买的产品，其属性包括产品名称、价格和序列号等。<br/>
 * 订单明细与产品之间是单向的多对一的关系。通过订单明细可以获得所属产品信息。
 *
 * @author weijiancai
 * @version 0.0.1
 */
@Entity
@Table(name = "crm_product")
public class Product implements Serializable {
    /** 产品ID */
    private Integer id;
    /** 产品名称 */
    private String name;
    /** 产品价格 */
    private Double price;
    /** 产品序列号 */
    private String serialNumber;

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

    @Column(name = "price")
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Column(name = "serial_number")
    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }
}
