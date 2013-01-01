package com.wjc.jpa.practice.crm.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 头像实体：表示为联系人的头像照片，其属性有图像图片的宽和高及其保存图片的字节数据。<br/>
 * 联系人实体和头像实体是双向的一对一的关系。通过联系人可以获得其头像实体，通过头像实体也可以确定是那个联系人。
 *
 * @author weijiancai
 * @version 0.0.1
 */
@Entity
@Table(name = "crm_portrait")
public class Portrait implements Serializable {
    /** 头像ID */
    private Integer id;
    /** 图片的宽 */
    private Integer width;
    /** 图片的高 */
    private Integer height;
    /** 图片字节数据 */
    private byte[] image;
    /** 联系人 */
    private Contact contact;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "width")
    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    @Column(name = "height")
    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    @Column(name = "image")
    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @OneToOne(mappedBy = "portrait")
    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }
}
