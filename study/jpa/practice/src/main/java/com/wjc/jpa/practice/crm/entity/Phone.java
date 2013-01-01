package com.wjc.jpa.practice.crm.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 电话实体：表示为联系人的电话，其属性包含电话号码、号码的类型（家庭、工作、手机等）。<br/>
 * 联系人实体与电话实体是单向的一对多的关系。一个联系人可以有多个电话号码。
 *
 * @author weijiancai
 * @version 0.0.1
 */
@Entity
@Table(name = "crm_phone")
public class Phone implements Serializable {
    /** 电话ID */
    private Integer id;
    /** 电话号码 */
    private String number;
    /** 电话类型，枚举类型 */
    private PhoneType type;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "number")
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    public PhoneType getType() {
        return type;
    }

    public void setType(PhoneType type) {
        this.type = type;
    }
}
