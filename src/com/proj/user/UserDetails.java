package com.proj.user;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import com.proj.util.SessionUtils;

@Named
@SessionScoped
public class UserDetails implements Serializable
{

	private static final long serialVersionUID = 6504269673339318221L;

	private int id;
	private String username;
	private String firstname;
	private String lastname;
	private String email;
	private String pwd;
	
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
	
	/* PASSWORD Getter Setter*/
	public String getPwd()
	{
		return pwd;
	}
	
	public void setPwd(String pwd)
	{
		this.pwd = pwd;
	}
	
	public void updateUserDetails() {
		HttpSession session = SessionUtils.getSession();
		User userConnected = (User) session.getAttribute("user");
		
		if(userConnected != null) {
			setId(userConnected.getId());
			setUsername(userConnected.getUsername());
			setFirstname(userConnected.getFirstname());
			setLastname(userConnected.getLastname());
			setEmail(userConnected.getEmail());
			setPwd(userConnected.getPwd());			
		}
		else {
			System.out.println("userConnected vide");
		}
	}
	
	public void clearUserDetails() {
		setId(0);
		setUsername(null);
		setFirstname(null);
		setLastname(null);
		setEmail(null);
		setPwd(null);
	}
	
}
