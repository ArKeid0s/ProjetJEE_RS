package com.proj.actions;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
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

	private boolean success = false;
	
	private UserDao userDao;

	private String error = "";

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
	public String getError()
	{
		return error;
	}

	public void setError(String error)
	{
		this.error = error;
	}

	User userRegistered = new User();
	HttpSession session = SessionUtils.getSession();

	// Proceed login verifications
	public void proceedRegisterRequest()
	{
		if (session.getAttribute("user") == null)
		{
			userDao = new DatabaseUserDao();
			
			userDao = new DatabaseUserDao();
			userRegistered.setId(0);
			userRegistered.setUsername(username);
			userRegistered.setFirstname(firstname);
			userRegistered.setLastname(lastname);
			userRegistered.setEmail(email);
			userRegistered.setPwd(pwd);

			for (User userTaken : userDao.fetchAll())
			{
				if (userTaken.getUsername().equals(userRegistered.getUsername()) || userTaken.getEmail().equals(userRegistered.getEmail()))
				{
					success = false;
					error = "Username and/or Email already taken";
					return;
				}
			}
			
			success = true;
			userDao.insert(userRegistered);
			
			userRegistered = userDao.findByUsernamePwd(username, pwd);
			session.setAttribute("user", userRegistered);
		}
		success=true;

	}

	/* Validate by sending string to register.xhtml */
	public String validateRegisterRequest()
	{
		if(success) return "success";
		else return "fail";
	}

}
