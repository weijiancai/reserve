package cn.itcast.jpa.bean;

import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.UUID;

/**
 * @author weijiancai
 * @version 1.0.0
 */
public class OneToManyTest {
    @Test public void save() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("itcast_jpa");
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();

        Order order = new Order();
        order.setOrderId(UUID.randomUUID().toString().replace("-", ""));
        order.setAmount(100f);

        OrderItem item1 = new OrderItem();
        item1.setProductName("足球");
        item1.setSellPrice(50f);
        OrderItem item2 = new OrderItem();
        item2.setProductName("篮球");
        item2.setSellPrice(32f);

        order.addItem(item1);
        order.addItem(item2);

        em.persist(order);
        em.getTransaction().commit();
        em.close();
        factory.close();
    }
}
