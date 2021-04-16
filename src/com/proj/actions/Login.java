package com.proj.actions;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import com.proj.dao.DatabaseUserDao;
import com.proj.dao.UserDao;
import com.proj.user.User;
import com.proj.util.DbConnection;
import com.proj.util.SessionUtils;

@Named
@SessionScoped
public class Login implements Serializable
{

	private static final long serialVersionUID = 782115604435416963L;

	private String username;
	private String pwd;

	private UserDao userDao;
	private User userConnected = null;

	private String msg;

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getPwd()
	{
		return pwd;
	}

	public void setPwd(String pwd)
	{
		this.pwd = pwd;
	}

	public String getMsg()
	{
		return msg;
	}

	public void setMsg(String msg)
	{
		this.msg = msg;
	}

	
	// Validate login
	public String validateLoginRequest()
	{
		userDao = new DatabaseUserDao();
		userConnected = userDao.findByUsernamePwd(username, pwd);
		if (userConnected != null)
		{
			HttpSession session = SessionUtils.getSession();
			
			session.setAttribute("user", userConnected);
			return "success";
		}
		else
		{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Incorrect Username and Password", "Please enter a correct Username and Password"));
			return "login";
		}
	}

	public String logout()
	{
		HttpSession session = SessionUtils.getSession();
		session.invalidate();
		return "login";
	}

}
