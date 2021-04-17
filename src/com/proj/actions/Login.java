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
import com.proj.util.SessionUtils;

@Named
@SessionScoped
public class Login implements Serializable
{

	private static final long serialVersionUID = 782115604435416963L;

	private int id;
	private String username;
	private String firstname;
	private String lastname;
	private String email;
	private String pwd;
	
	private boolean loggedIn = false;

	private UserDao userDao;
	private User userConnected = null;

	private String msg;

	/* ID Getter Setter */
	public int getId()
	{
		return id;
	}
	
	public void setId(int id)
	{
		this.id = id;
	}
	
	/* USERNAME Getter Setter */
	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	/* FIRSTNAME Getter Setter */
	public String getFirstname()
	{
		return firstname;
	}

	public void setFirstname(String firstname)
	{
		this.firstname = firstname;
	}

	/* LASTNAME Getter Setter */
	public String getLastname()
	{
		return lastname;
	}

	public void setLastname(String lastname)
	{
		this.lastname = lastname;
	}

	/* EMAIL Getter Setter */
	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	/* PASSWORD Getter Setter */
	public String getPwd()
	{
		return pwd;
	}

	public void setPwd(String pwd)
	{
		this.pwd = pwd;
	}

	/* MESSAGE Getter Setter */
	public String getMsg()
	{
		return msg;
	}

	public void setMsg(String msg)
	{
		this.msg = msg;
	}
	
	public String validateLoginRequest() {
		userDao = new DatabaseUserDao();
		userConnected = userDao.findByUsernamePwd(username, pwd);
		
		if(userConnected != null && loggedIn == false) {
			loggedIn = true;
			return "success";
		}
		else if(loggedIn) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
					"You are already logged in", "You can see your user details in the home page"));
			return "login";
		}
		else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Incorrect Username and Password", "Please enter a correct Username and Password"));
			return "login";
		}
	}
	
	public void proceedLoginRequest()
	{
		userDao = new DatabaseUserDao();
		userConnected = userDao.findByUsernamePwd(username, pwd);
		HttpSession session = SessionUtils.getSession();
		if (userConnected != null && loggedIn == false && session.getAttribute("user") == null)
		{
			//Set login variables to access them
			setId(userConnected.getId());
			setUsername(userConnected.getUsername());
			setFirstname(userConnected.getFirstname());
			setLastname(userConnected.getLastname());
			setEmail(userConnected.getEmail());
			setPwd(userConnected.getPwd());
			
			session.setAttribute("user", userConnected);
		}
	}
	

	public String logout()
	{
		HttpSession session = SessionUtils.getSession();
		session.invalidate();
		return "login";
	}

}
