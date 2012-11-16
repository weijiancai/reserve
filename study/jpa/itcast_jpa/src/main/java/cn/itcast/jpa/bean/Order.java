package cn.itcast.jpa.bean;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * 1对多：多的一方为关系维护端，关系维护端负责外键记录的更新，关系被维护端是没有权力更新外键记录的。
 * @author weijiancai
 * @version 1.0.0
 */
@Entity
@Table(name = "orders")
public class Order {
    private String orderId;
    private Float amount = 0f;
    private Set<OrderItem> items = new HashSet<OrderItem>();

    @Id @Column(length = 32)
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @Column(nullable = false)
    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    @OneToMany(cascade = {CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, mappedBy = "order")
    public Set<OrderItem> getItems() {
        return items;
    }

    public void setItems(Set<OrderItem> items) {
        this.items = items;
    }

    public void addItem(OrderItem item) {
        item.setOrder(this);
        items.add(item);
    }
}
