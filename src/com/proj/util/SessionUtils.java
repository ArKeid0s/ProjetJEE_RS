package com.proj.util;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.proj.user.User;

public class SessionUtils
{

	public static HttpSession getSession()
	{
		return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
	}

	public static HttpServletRequest getRequest()
	{
		return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	}
	
	/* Parameters attributes getters */
	public static User getUser()
	{
		HttpSession session = getSession();
		if(session!=null && session.getAttribute("user")!=null) {
			return (User) session.getAttribute("user");
		}
		else return null;
	}

}
