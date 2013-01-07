package cn.itcast.jpa.domain;

import cn.itcast.jpa.JpaUtil;
import cn.itcast.jpa.domain.*;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author weijiancai
 * @date 2011-05-29 10:26
 */
public class BaseTest {
    public static void main(String[] args) {
        User user = new User();
        user.setName("张三");
        user.setBirthday(new Date());

        addUser(user);
        query(user.getId());
    }



    static void addUser(User user) {
        EntityManager em = null;
        EntityTransaction tx = null;

        try {
            em = JpaUtil.getEntityManager();
            tx = em.getTransaction();
            tx.begin();
            em.persist(user);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Test
    public void addDepart() {
        EntityManager em = null;
        EntityTransaction tx = null;

        try {
            em = JpaUtil.getEntityManager();
            tx = em.getTransaction();
            tx.begin();

            Department depart = new Department();
            depart.setName("depart name");

            Employee emp1 = new Employee();
            emp1.setDepartment(depart);
            emp1.setName("emp name1");

            Skiller emp2 = new Skiller();
            emp2.setDepartment(depart);
            emp2.setName("emp name2");
            emp2.setSkill("skill");

            Sales emp3 = new Sales();
            emp3.setDepartment(depart);
            emp3.setName("emp name3");
            emp3.setSell(100);

            Set<Employee> emps = new HashSet<Employee>();
            emps.add(emp1);
            emps.add(emp2);
            emps.add(emp3);
            depart.setEmployees(emps);

            em.persist(depart);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    static void query(String id) {
        EntityManager em = JpaUtil.getEntityManager();
        User user = em.find(User.class, id);
        String jpaQl = "select user from User user";
        Query query = em.createQuery(jpaQl);
        System.out.println(user.getName());
        em.close();
    }
}