package com.proj.actions;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import com.proj.dao.LoginDAO;
import com.proj.util.SessionUtils;

@Named
@SessionScoped
public class Login implements Serializable
{

	private static final long serialVersionUID = 782115604435416963L;

	/* TODO: Replace string with user object */

	private String username;
	private String pwd;
	private String firstname;
	private String lastname;
	private String email;

	private String msg;

	public String getMsg()
	{
		return msg;
	}

	public void setMsg(String msg)
	{
		this.msg = msg;
	}

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

	public String getFirstname()
	{
		return firstname;
	}

	public void setFirstname(String firstname)
	{
		this.firstname = firstname;
	}

	public String getLastname()
	{
		return lastname;
	}

	public void setLastname(String lastname)
	{
		this.lastname = lastname;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	// Validate login
	public String validateLoginRequest()
	{
		boolean valid = LoginDAO.validate(username, pwd);
		if (valid)
		{
			HttpSession session = SessionUtils.getSession();
			session.setAttribute("username", username);
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
