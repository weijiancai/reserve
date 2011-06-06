package com.bjsxt.oa.manager.impl;

import com.bjsxt.oa.PagerModel;
import com.bjsxt.oa.manager.OrgManager;
import com.bjsxt.oa.manager.SystemException;
import com.bjsxt.oa.model.Organization;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

public class OrgManagerImpl extends HibernateDaoSupport implements OrgManager {
	
	public void addOrg(Organization org, int parentId) {
		if(parentId != 0){
			org.setParent(findOrg(parentId));
		}
		getHibernateTemplate().save(org);
		
		//自动生成机构编号
		org.setSn(
			(org.getParent() == null ? "" : org.getParent().getSn() + "_")
			+ org.getId()
		);
		
		getHibernateTemplate().update(org);
	}

	public void delOrg(int orgId) {
		Organization org = findOrg(orgId);
		
		//判断子机构列表是否为空
		if(org.getChildren().size() > 0){
			//throw new RuntimeException("存在子机构信息，不允许删除");
//			SystemException se = new SystemException("");
//			se.setKey();
//			se.setValues(..);
			throw new SystemException("errors.org.hassuborg",new Object[]{org.getName(),org.getChildren().size()},"存在子机构信息，不允许删除");
		}
		
		//判断人员是否非空
		String hql = "select count(*) from Person p where p.org.id = ?";
		Long personSize = (Long)getSession().createQuery(hql).setParameter(0, orgId).uniqueResult();
		if(personSize > 0){
			throw new RuntimeException("机构下面有人员信息，不允许删除");
		}
		
		getHibernateTemplate().delete(org);
	}

	public Organization findOrg(int orgId) {
		return (Organization)getHibernateTemplate().load(Organization.class, orgId);
	}

	public PagerModel searchOrgs(int parentId,int offset,int pagesize) {
		
		//查询总记录数
		String selectCountHql = "select count(*) from Organization o where o.parent is null";
		if(parentId != 0){
			selectCountHql = "select count(*) from Organization o where o.parent.id = "+parentId;
		}
		int total = ((Long)getSession().createQuery(selectCountHql).uniqueResult()).intValue();
		
		
		//查询当前页的数据
		String hql = "select o from Organization o where o.parent is null";
		if(parentId != 0){
			hql = "select o from Organization o where o.parent.id = "+parentId;
		}
		List datas = getSession().createQuery(hql)
						.setFirstResult(offset)
						.setMaxResults(pagesize)
						.list();
		 
		PagerModel pm = new PagerModel();
		pm.setDatas(datas);
		pm.setTotal(total);
		
		return pm;
	}

	public void updateOrg(Organization org, int parentId) {
		if(parentId != 0){
			org.setParent(findOrg(parentId));
		}
		getHibernateTemplate().update(org);
		
	}

}
