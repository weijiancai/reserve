package cn.itcast.jpa.bean;

import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author weijiancai
 * @version 1.0.0
 */
public class OnToOneTest {
    @Test public void testSave() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("itcast_jpa");
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();

        Person person = new Person("张三");
        person.setIdCard(new IDCard("1342342423"));
        em.persist(person);

        em.getTransaction().commit();
        em.close();
        factory.close();
    }
}
