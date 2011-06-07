package com.bjsxt.oa.manager.impl;

import com.bjsxt.oa.PagerModel;
import com.bjsxt.oa.manager.OrgManager;
import com.bjsxt.oa.manager.SystemException;
import com.bjsxt.oa.model.Organization;

public class OrgManagerImpl extends AbstractManager implements OrgManager {
	
	public void addOrg(Organization org, int parentId) {
		if(parentId != 0){
			org.setParent(findOrg(parentId));
		}
		getHibernateTemplate().save(org);
		
		//�Զ����ɻ������
		org.setSn(
			(org.getParent() == null ? "" : org.getParent().getSn() + "_")
			+ org.getId()
		);
		
		getHibernateTemplate().update(org);
	}

	public void delOrg(int orgId) {
		Organization org = findOrg(orgId);
		
		//�ж��ӻ����б��Ƿ�Ϊ��
		if(org.getChildren().size() > 0){
			//throw new RuntimeException("�����ӻ�����Ϣ��������ɾ��");
//			SystemException se = new SystemException("");
//			se.setKey();
//			se.setValues(..);
			throw new SystemException("errors.org.hassuborg",new Object[]{org.getName(),org.getChildren().size()},"�����ӻ�����Ϣ��������ɾ��");
		}
		
		//�ж���Ա�Ƿ�ǿ�
		String hql = "select count(*) from Person p where p.org.id = ?";
		Long personSize = (Long)getSession().createQuery(hql).setParameter(0, orgId).uniqueResult();
		if(personSize > 0){
			throw new RuntimeException("������������Ա��Ϣ��������ɾ��");
		}
		
		getHibernateTemplate().delete(org);
	}

	public Organization findOrg(int orgId) {
		return (Organization)getHibernateTemplate().load(Organization.class, orgId);
	}

	public PagerModel searchOrgs(int parentId) {
		
		String hql = "select o from Organization o where o.parent is null";
		if(parentId != 0){
			hql = "select o from Organization o where o.parent.id = "+parentId;
		}

		return searchPaginated(hql);
	}

	public void updateOrg(Organization org, int parentId) {
		if(parentId != 0){
			org.setParent(findOrg(parentId));
		}
		getHibernateTemplate().update(org);
		
	}

}
