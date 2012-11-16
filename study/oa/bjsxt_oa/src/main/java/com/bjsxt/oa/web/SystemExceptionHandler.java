package com.bjsxt.oa.web;

import com.bjsxt.oa.manager.SystemException;
import org.apache.struts.action.*;
import org.apache.struts.config.ExceptionConfig;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SystemExceptionHandler extends ExceptionHandler {

	@Override
	public ActionForward execute(Exception ex, ExceptionConfig ae,
			ActionMapping mapping, ActionForm formInstance,
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException {
		//
        ActionForward forward = null;
        ActionMessage error = null;

        // Build the forward from the exception mapping if it exists
        // or from the form input
        if (ae.getPath() != null) {
            forward = new ActionForward(ae.getPath());
        } else {
            forward = mapping.getInputForward();
        }
        
        //¥¶¿ÌSystemException“Ï≥£
        if(ex instanceof SystemException){
        	SystemException se = (SystemException)ex;
        	String key = se.getKey();
        	if(key == null){
        		error = new ActionMessage(ae.getKey(),se.getMessage());
        	}else{
        		if(se.getValues() != null){
        			error = new ActionMessage(key,se.getValues());
        		}else{
        			error = new ActionMessage(key);
        		}
        	}
        	
        	this.storeException(request, key, error, forward, ae.getScope());
        	
        	return forward;
        }
		
		return super.execute(ex, ae, mapping, formInstance, request, response);
	}

}
