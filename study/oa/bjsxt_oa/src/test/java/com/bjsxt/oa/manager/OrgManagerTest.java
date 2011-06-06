package com.bjsxt.oa.manager;

import com.bjsxt.oa.model.Organization;
import org.springframework.test.AbstractTransactionalSpringContextTests;

public class OrgManagerTest extends AbstractTransactionalSpringContextTests {

	private OrgManager orgManager;
	
	@Override
	protected String[] getConfigLocations() {
		return new String[]{"classpath*:applicationContext-*.xml"};
	}

	public void testAddOrg() {
		
//		Organization org = new Organization();
//		org.setName("测试机构");
//		org.setDescription("描述");
//		om.addOrg(org, 0);
		
		for(int i=0; i<5; i++){
			Organization org = new Organization();
			org.setName("父机构"+i);
			orgManager.addOrg(org, 0);
			
			for(int j=0; j<10; j++){
				Organization c = new Organization();
				c.setName("["+org.getName()+"]下面的子机构"+j);
				orgManager.addOrg(c, org.getId());
			}
		}
		
		//使得父类能够提交事务
		setComplete();
	}

	public void testDelOrg() {
		fail("Not yet implemented");
	}

	public void testUpdateOrg() {
		fail("Not yet implemented");
	}

	public void testFindOrg() {
		
		Organization org = orgManager.findOrg(5);
		
		System.out.println(org.getName());
		
	}

	public void testSearchOrgs() {
		
//		List orgs = orgManager.searchOrgs(0);
//		for (Iterator iterator = orgs.iterator(); iterator.hasNext();) {
//			Organization org = (Organization) iterator.next();
//			System.out.println(org.getName());
//		}
		
	}

	public void setOrgManager(OrgManager orgManager) {
		this.orgManager = orgManager;
	}

}
