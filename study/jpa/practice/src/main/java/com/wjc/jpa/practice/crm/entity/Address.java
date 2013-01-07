package com.wjc.jpa.practice.crm.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 地址实体：表示为地址信息，其属性有邮政编码、详细地址和备用的详细地址等。<br/>
 * 一个客户与一个地址实体一一对应，他们之间是单向的一对一关系。一个客户可以获得其对应的地址实体。
 *
 * @author weijiancai
 * @version 0.0.1
 */
@Entity
@Table(name = "crm_address")
public class Address implements Serializable {
    /** 客户地址 */
    private Integer id;
    /** 邮政编码 */
    private String zip;
    /** 详细地址 */
    private String line1;
    /** 备用详细地址 */
    private String line2;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "zip")
    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    @Column(name = "line1")
    public String getLine1() {
        return line1;
    }

    public void setLine1(String line1) {
        this.line1 = line1;
    }

    @Column(name = "line2")
    public String getLine2() {
        return line2;
    }

    public void setLine2(String line2) {
        this.line2 = line2;
    }
}
