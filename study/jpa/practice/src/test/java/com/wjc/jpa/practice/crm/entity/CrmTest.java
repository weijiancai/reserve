package com.wjc.jpa.practice.crm.entity;

import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author weijiancai
 * @version 0.0.1
 */
public class CrmTest {
    @Test
    public void test() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("itcast_jpa");
        EntityManager em = factory.createEntityManager();
        em.close();
        factory.close();
    }
}
