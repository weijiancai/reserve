package cn.itcast.jpa.bean;

import cn.itcast.jpa.bean.Person;
import com.fly.dbtest.DBTestCase;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import java.util.List;

import static org.junit.Assert.assertThat;

/**
 * @author weijiancai
 * @version 1.0.0
 */
public class PersonTest extends DBTestCase {
    @Test public void testSave() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("itcast_jpa");
        // --> sessionFactory --> session --> begin事务
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        Person person = new Person("传智播客");
        em.persist(person);
        em.getTransaction().commit();

        printMetaData();
        /*hasTable("person");
        hasTableCount("person", 1);
        hasRecord("person", person);
        hasColumn("person", "name");
        Object size = getColumnInfo("person", "name", ColumnInfo.COLUMN_SIZE);
        assertThat(Integer.parseInt(size.toString()), equalTo(10));
        Object nullable = getColumnInfo("person", "name", ColumnInfo.IS_NULLABLE);
        assertThat(Boolean.getBoolean(nullable.toString()), equalTo(false));
        */
        System.out.println(person);
        em.close();
        factory.close();
    }

    @Test public void testFind() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("itcast_jpa");
        // --> sessionFactory --> session --> begin事务
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        Person person = new Person("传智播客");
        em.persist(person);
        person = em.find(Person.class, 1);
        System.out.println(person);
        em.getTransaction().commit();
        em.close();
        factory.close();
    }

    @Test public void testFind2() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("itcast_jpa");
        // --> sessionFactory --> session --> begin事务
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        Person person = new Person("传智播客");
        em.persist(person);
        person = em.getReference(Person.class, 1);
        System.out.println(person);
        em.getTransaction().commit();
        em.close();
        factory.close();
    }

    @Test public void testUpdate() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("itcast_jpa");
        // --> sessionFactory --> session --> begin事务
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        Person person = new Person("传智播客");
        em.persist(person);
        person = em.find(Person.class, 1);  // 托管状态
        System.out.println(person);
        person.setName("老张");
        System.out.println(person);
        em.getTransaction().commit();
        em.close();
        factory.close();
    }

    @Test public void testUpdate2() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("itcast_jpa");
        // --> sessionFactory --> session --> begin事务
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        Person person = new Person("传智播客");
        em.persist(person);
        person = em.find(Person.class, 1);
        System.out.println(person);
        em.clear();  // 把实体管理器中的所有实体变成游离状态
        em.merge(person);
        person.setName("老李");
        System.out.println(person);

        em.getTransaction().commit();
        em.close();
        factory.close();
    }

    @Test public void testDelete() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("itcast_jpa");
        // --> sessionFactory --> session --> begin事务
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        Person person = new Person("传智播客");
        em.persist(person);
        person = em.find(Person.class, 1);
        em.remove(person);

        em.getTransaction().commit();
        em.close();
        factory.close();
    }

    @SuppressWarnings("unchecked")
    @Test public void testQuery() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("itcast_jpa");
        EntityManager em = factory.createEntityManager();
        Query query = em.createQuery("SELECT o FROM Person o WHERE o.id=?1");
        query.setParameter(1, 1);
        List<Person> personList = query.getResultList();
        System.out.println(personList.size());
        em.close();
        factory.close();
    }

    @Test public void deleteQuery() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("itcast_jpa");
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("DELETE FROM Person o WHERE o.id=?1");
        query.setParameter(1, 1);
        query.executeUpdate();
        em.getTransaction().commit();
        em.close();
        factory.close();
    }

    @Test public void queryUpdate() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("itcast_jpa");
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("UPDATE Person o SET o.name=:name WHERE o.id=:id");
        query.setParameter("name", "xxx");
        query.setParameter("id", 1);
        query.executeUpdate();
        em.getTransaction().commit();
        em.close();
        factory.close();
    }

    @Test public void printMetaInfo() {
        printMetaData();
    }

    /**
     * JPA 实体状态
     * 1. new
     * 2. managered
     * 3. 游离
     * 4. 删除
     */
}
