package cn.itcast.jpa.bean;

import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * 多对多测试
 *
 * @author weijiancai
 * @version 0.0.1
 */
public class ManyToManyTest {
    @Test
    public void testSave() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("itcast_jpa");
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();

        Student student = new Student("小张");
        Teacher teacher = new Teacher("李勇老师");
        em.persist(student);
        em.persist(teacher);
        em.persist(new Teacher("张孝祥老师"));

        em.getTransaction().commit();
        em.close();
        factory.close();
    }

    // 建立学生和老师的关系
    @Test
    public void buildTS() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("itcast_jpa");
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();

        Student student = em.find(Student.class, 1);
        Teacher teacher = em.getReference(Teacher.class, 1);
        student.addTeacher(teacher);

        em.getTransaction().commit();
        em.close();
        factory.close();
    }

    // 解除学生和老师的关系
    @Test
    public void deleteTS() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("itcast_jpa");
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();

        Student student = em.find(Student.class, 1);
        Teacher teacher = em.getReference(Teacher.class, 1);
        student.removeTeacher(teacher);

        em.getTransaction().commit();
        em.close();
        factory.close();
    }

    // 删除老师
    @Test
    public void deleteTeacher() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("itcast_jpa");
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();

        // 先解除学生和老师的关系，才能删除老师
        Student student = em.find(Student.class, 1);
        Teacher teacher = em.getReference(Teacher.class, 1);
        student.removeTeacher(teacher);
        // 删除老师
        em.remove(teacher);

        em.getTransaction().commit();
        em.close();
        factory.close();
    }

    // 删除学生
    @Test
    public void deleteStudent() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("itcast_jpa");
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();

        // 删除学生
        Student student = em.getReference(Student.class, 1);
        em.remove(student);

        em.getTransaction().commit();
        em.close();
        factory.close();
    }
}
