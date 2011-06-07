package com.bjsxt.oa.manager.impl;

import com.bjsxt.oa.PagerModel;
import com.bjsxt.oa.manager.SystemException;
import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

public abstract class AbstractManager extends HibernateDaoSupport {
	
	public PagerModel searchPaginated(String hql,int offset,int pagesize){
		return searchPaginated(hql, null, offset, pagesize);
	}
	
	public PagerModel searchPaginated(String hql,Object value,int offset,int pagesize){
		return searchPaginated(hql, new Object[]{value}, offset, pagesize);
	}
	
	public PagerModel searchPaginated(String hql,Object[] values,int offset,int pagesize){
		//����ܼ�¼��
		String countHql = getCountQuery(hql);
		Query query = getSession().createQuery(countHql);
		if(values != null && values.length > 0){
			for(int i=0; i<values.length; i++){
				query.setParameter(i, values[i]);
			}
		}
		int total = ((Long)query.uniqueResult()).intValue();
		
		//��õ�ǰҳ������
		query = getSession().createQuery(hql);
		if(values != null && values.length > 0){
			for(int i=0; i<values.length; i++){
				query.setParameter(i, values[i]);
			}
		}
		query.setFirstResult(offset);
		query.setMaxResults(pagesize);
		List datas = query.list();
		
		PagerModel pm = new PagerModel();
		pm.setDatas(datas);
		pm.setTotal(total);
		
		return pm;
	}
	
	/**
	 * ����HQL��䣬��ò�ѯ�ܼ�¼����HQL���
	 * �磺
	 * select o from Organization o where o.parent is null
	 * ����ת�����õ�
	 * select count(*) from Organization o where o.parent is null
	 * @param hql
	 * @return
	 */
	private String getCountQuery(String hql){
		int index = hql.indexOf("from");
		if(index != -1){
			return "select count(*) " + hql.substring(index);
		}
		throw new SystemException("��Ч��HQL��ѯ��䡾"+hql+"��");
	}
}
