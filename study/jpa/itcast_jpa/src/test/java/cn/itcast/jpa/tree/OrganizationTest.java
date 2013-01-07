package cn.itcast.jpa.tree;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * @author weijiancai
 * @version 0.0.1
 */
public class OrganizationTest {
    EntityManagerFactory emf = null;

    @Before
    public void before() {
        //根据在persistence.xml中配置的persistence-unit name 创建EntityManagerFactory
        emf = Persistence.createEntityManagerFactory("itcast_jpa");
    }

    /**
     * 保存父组织,级联保存子组织
     */
    @Test
    public void testAddParentOrg() {
        //父组织
        Organization chinaOrg = new Organization();
        chinaOrg.setName("中国");
        chinaOrg.setCode("CHINA");

        //子组织
        Organization gdOrg = new Organization();
        gdOrg.setName("广东");
        gdOrg.setCode("GD");

        //子组织
        Organization gxOrg = new Organization();
        gxOrg.setName("广西");
        gxOrg.setCode("GX");

        Set<Organization> children = new HashSet<Organization>();
        children.add(gdOrg);
        children.add(gxOrg);

        //添加子组织
        chinaOrg.setChildren(children);

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(chinaOrg);
        em.getTransaction().commit();
        em.close();
    }

    /**
     * 保存子组织
     */
    @Test
    public void testAddChildOrg() {
        Organization gzOrg = new Organization();
        gzOrg.setName("广州");
        gzOrg.setCode("GZ");


        EntityManager em = emf.createEntityManager();
        //找出广州所属的父组织广东
        Organization parent = em.find(Organization.class, 3L);
        //设置广州的父组织
        gzOrg.setParent(parent);

        em.getTransaction().begin();
        //保存广州
        em.persist(gzOrg);
        em.getTransaction().commit();
        em.close();
    }

    /**
     * 根据子组织查询父组织
     */
    @Test
    public void testQueryParentByChild() {
        EntityManager em = emf.createEntityManager();
        //找出广州
        Organization gzOrg = em.find(Organization.class, 4L);
        //找出父组织
        Organization parent = gzOrg.getParent();
        System.out.println("父组织名称："+parent.getName());
        em.close();
    }

    /**
     * 根据父组织查询子组织
     */
    @Test
    public void testQueryChildrenByParent() {
        EntityManager em = emf.createEntityManager();
        //找出广东
        Organization gdOrg = em.find(Organization.class, 3L);
        //找出子组织
        Set<Organization> children = gdOrg.getChildren();
        Iterator<Organization> it = children.iterator();
        while(it.hasNext()) {
            Organization  child = it.next();
            System.out.println("子组织名称："+child.getName());
        }
        em.close();
    }


    /**
     * 关闭EntityManagerFactory
     */
    @After
    public void after() {
        if(null != emf) {
            emf.close();
        }
    }
}
