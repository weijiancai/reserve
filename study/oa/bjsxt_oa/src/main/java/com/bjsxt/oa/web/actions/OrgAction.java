package com.bjsxt.oa.web.actions;

import com.bjsxt.oa.PagerModel;
import com.bjsxt.oa.manager.OrgManager;
import com.bjsxt.oa.model.Organization;
import com.bjsxt.oa.web.forms.OrgActionForm;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OrgAction extends DispatchAction {

	private OrgManager orgManager;
	
	/**
	 * ����������
	 */
	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//
		
		OrgActionForm oaf = (OrgActionForm)form;
		int parentId = oaf.getParentId();

		PagerModel pm = orgManager.searchOrgs(parentId);
		request.setAttribute("pm", pm);
		
		int ppid = 0;
		if(parentId != 0){
			Organization parent = orgManager.findOrg(parentId);
			if(parent.getParent() != null){
				ppid = parent.getParent().getId();
			}
		}
		request.setAttribute("ppid", ppid);
		
		return mapping.findForward("index");
	}
	
	/**
	 * ����ӽ���
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward addInput(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		
		return mapping.findForward("add_input");
	}

	//��ӻ�����Ϣ
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		OrgActionForm oaf = (OrgActionForm)form;
		
		Organization org = new Organization();
		
		BeanUtils.copyProperties(org, oaf);
		
		orgManager.addOrg(org, oaf.getParentId());
		
		return mapping.findForward("pub_add_success");
	}
	
	public ActionForward del(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		OrgActionForm oaf = (OrgActionForm)form;
		int id = oaf.getId();

//		try{
			orgManager.delOrg(id);
//		}catch(Exception e){
			
//			ActionMessages msgs  = new ActionMessages();
//			
//			ActionMessage msg = new ActionMessage()
//			
//			msgs.add("m", msg);
			
			//���������ͨ��Ϣ
//			request.setAttribute(Globals.MESSAGES_KEY, msgs);
//			saveMessages(request, msgs);
			
			//������Ǵ�����Ϣ
//			request.setAttribute(Globals.ERROR_KEY, msgs);
//			saveErrors(request, msgs);
//			
//			return mapping.findForward("org_exception");
//		}
		return mapping.findForward("pub_del_success");
	}	
	
	public ActionForward updateInput(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			return mapping.findForward("update_input");
	}
	
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			return mapping.findForward("pub_update_success");
	}

	public void setOrgManager(OrgManager orgManager) {
		this.orgManager = orgManager;
	}
}
