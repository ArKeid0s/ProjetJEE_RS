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
	public static String getUserName()
	{
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		return session.getAttribute("username").toString();
	}

	public static String getUserId()
	{
		HttpSession session = getSession();
		if (session != null) return (String) session.getAttribute("userid");
		else return null;
	}
	
	/* Parameters attributes getters */
	public static User getUser()
	{
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		return (User) session.getAttribute("user");
	}

}
