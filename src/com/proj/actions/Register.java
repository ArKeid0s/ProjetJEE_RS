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
public class Register implements Serializable
{

	private static final long serialVersionUID = 5015478462737602340L;

	private int id;
	private String username;
	private String firstname;
	private String lastname;
	private String email;
	private String pwd;

	private UserDao userDao;

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

	User userRegistered = new User();
	
	// Validate login
	public void proceedRegisterRequest()
	{
		userDao = new DatabaseUserDao();
		userRegistered.setId(0);
		userRegistered.setUsername(username);
		userRegistered.setFirstname(firstname);
		userRegistered.setLastname(lastname);
		userRegistered.setEmail(email);
		userRegistered.setPwd(pwd);
		
		
		HttpSession session = SessionUtils.getSession();
		userDao.insert(userRegistered);
		
		userRegistered = userDao.findByUsernamePwd(username, pwd);
		session.setAttribute("user", userRegistered);
		
	}
	
	public String validateRegisterRequest() {
		userDao = new DatabaseUserDao();
		
		for (User userTaken : userDao.fetchAll())
		{
			if (userTaken.getUsername().equals(userRegistered.getUsername()) || userTaken.getEmail().equals(userRegistered.getEmail()))
			{
				/* Similar user already exists then reload the form */
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Username and/or Email already taken",
						"Please enter a different Username and/or Email"));
				userDao.remove(userRegistered);
				HttpSession session = SessionUtils.getSession();
				session.invalidate();
				return "login";
			}
			else
			{				
				return "success";

			}
		}
		return "login";
	}
	

}
