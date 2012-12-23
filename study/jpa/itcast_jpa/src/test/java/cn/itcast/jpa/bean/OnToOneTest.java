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

        // 当IDCard的Person注解optional = false时，此时person对象不能为空，这个是不必要设置的，因为这个在关系维护端进行维护的
        /*Person person = new Person("张三");
        IDCard card = new IDCard("1342342423");
        card.setPerson(person);
        person.setIdCard(card);
        em.persist(person);*/

        // 当IDCard的Person注解optional = true时
        Person person = new Person("张三");
        person.setIdCard(new IDCard("1342342423"));
        em.persist(person);

        em.getTransaction().commit();
        em.close();
        factory.close();
    }
}
