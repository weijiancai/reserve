package com.bjsxt.oa.manager;

import com.bjsxt.oa.PagerModel;
import com.bjsxt.oa.model.Organization;

public interface OrgManager {
	
	/**
	 * ��ӻ�����Ϣ�����parentIdΪ0������Ӷ�������
	 * ��Ҫ�Զ����ɻ������
	 * @param org
	 * @param parentId
	 */
	public void addOrg(Organization org,int parentId);
	
	/**
	 * ɾ��������Ϣ
	 * ��������������ӻ�������Ա��Ϣ��������ɾ��
	 * @param orgId
	 */
	public void delOrg(int orgId);
	
	/**
	 * �����ض���������Ϣ
	 * @param org
	 * @param parentId
	 */
	public void updateOrg(Organization org,int parentId);
	
	/**
	 * ��ѯ�ض���������Ϣ
	 * @param orgId
	 * @return
	 */
	public Organization findOrg(int orgId);
	
	/**
	 * ���ݸ�����ID��ѯ�ӻ����б�
	 * ���parentIdΪ0�����ѯ���������б�
	 * @param parentId
	 * @param offset 
	 * @param pagesize
	 * @return
	 */
	public PagerModel searchOrgs(int parentId,int offset,int pagesize);
}
