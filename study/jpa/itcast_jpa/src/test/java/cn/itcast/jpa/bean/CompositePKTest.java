package cn.itcast.jpa.bean;

import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * 联合主键测试
 *
 * @author weijiancai
 * @version 0.0.1
 */
public class CompositePKTest {
    @Test
    public void testSave() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("itcast_jpa");
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();

        em.persist(new AirLine("PEK", "SHA", "北京飞上海"));

        em.getTransaction().commit();
        em.close();
        factory.close();
    }
}
