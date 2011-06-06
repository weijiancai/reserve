package com.bjsxt.oa.manager;

import com.bjsxt.oa.model.Organization;
import junit.framework.TestCase;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Iterator;
import java.util.List;

public class OrgManagerTest extends TestCase {

	private static BeanFactory factory = new ClassPathXmlApplicationContext("classpath*:applicationContext-*.xml");
	
	public void testAddOrg() {
		OrgManager om = (OrgManager)factory.getBean("orgManager");
		
//		Organization org = new Organization();
//		org.setName("测试机构");
//		org.setDescription("描述");
//		om.addOrg(org, 0);
		
		for(int i=0; i<5; i++){
			Organization org = new Organization();
			org.setName("父机构"+i);
			om.addOrg(org, 0);
			
			for(int j=0; j<10; j++){
				Organization c = new Organization();
				c.setName("["+org.getName()+"]下面的子机构"+j);
				om.addOrg(c, org.getId());
			}
		}
		
		
	}

	public void testDelOrg() {
		fail("Not yet implemented");
	}

	public void testUpdateOrg() {
		fail("Not yet implemented");
	}

	public void testFindOrg() {
		OrgManager om = (OrgManager)factory.getBean("orgManager");
		
		Organization org = om.findOrg(5);
		
		System.out.println(org.getName());
		
	}

	public void testSearchOrgs() {
		OrgManager om = (OrgManager)factory.getBean("orgManager");
		
		List orgs = om.searchOrgs(0);
		for (Iterator iterator = orgs.iterator(); iterator.hasNext();) {
			Organization org = (Organization) iterator.next();
			System.out.println(org.getName());
		}
		
	}

}
