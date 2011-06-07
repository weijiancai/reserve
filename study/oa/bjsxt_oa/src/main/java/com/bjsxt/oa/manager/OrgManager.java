package com.bjsxt.oa.manager;

import com.bjsxt.oa.PagerModel;
import com.bjsxt.oa.model.Organization;

public interface OrgManager {
	
	/**
	 * 添加机构信息，如果parentId为0，则添加顶级机构
	 * 需要自动生成机构编号
	 * @param org
	 * @param parentId
	 */
	public void addOrg(Organization org,int parentId);
	
	/**
	 * 删除机构信息
	 * 如果机构下面有子机构或人员信息，则不允许删除
	 * @param orgId
	 */
	public void delOrg(int orgId);
	
	/**
	 * 更新特定机构的信息
	 * @param org
	 * @param parentId
	 */
	public void updateOrg(Organization org,int parentId);
	
	/**
	 * 查询特定机构的信息
	 * @param orgId
	 * @return
	 */
	public Organization findOrg(int orgId);
	
	/**
	 * 根据父机构ID查询子机构列表
	 * 如果parentId为0，则查询顶级机构列表
	 * @param parentId
	 * @return
	 */
	public PagerModel searchOrgs(int parentId);
}
