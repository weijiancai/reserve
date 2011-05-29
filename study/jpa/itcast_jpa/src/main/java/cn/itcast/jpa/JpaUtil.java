package cn.itcast.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author weijiancai
 * @date 2011-05-29 10:23
 */
public class JpaUtil {
    private static EntityManagerFactory emf;

    static {
        emf = Persistence.createEntityManagerFactory("persistUnitName");
    }

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}
