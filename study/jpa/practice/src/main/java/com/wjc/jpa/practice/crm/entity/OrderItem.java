package com.wjc.jpa.practice.crm.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 订单明细实体：表示为订单中所购产品的条目，其属性包含产品和数量等。<br/>
 * 订单与订单明细是单向的一对多的关系。一个订单可以包含多个订单明细，通过指定的订单可以查看所属订单明细的条目。<br/>
 * 订单明细与产品是单向的多对一的关系。
 *
 * @author weijiancai
 * @version 0.0.1
 */
@Entity
@Table(name = "crm_order_item")
public class OrderItem implements Serializable {
    /** 订单明细实体 */
    private Integer id;
    /** 订单条目的数量 */
    private Integer quantity;
    /** 所订购的产品 */
    private Product product;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "quantity")
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @ManyToOne
    @JoinColumn(name = "product_id")
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
