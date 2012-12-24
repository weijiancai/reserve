package cn.itcast.jpa.domain;

import cn.itcast.jpa.JpaUtil;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.Date;

/**
 * @author weijiancai
 * @version 0.0.1
 */
public class UserTest {
    @Test
    public void addUser() {
        EntityManager em;
        EntityTransaction tx;

        try {
            em = JpaUtil.getEntityManager();
            tx = em.getTransaction();
            tx.begin();

            User user = new User();
            user.setBirthday(new Date());
            user.setName("name");
            em.persist(user);

            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
