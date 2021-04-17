package com.proj.actions;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import com.proj.dao.PostDAO;
import com.proj.post.Post;
import com.proj.user.User;
import com.proj.util.SessionUtils;

@Named
@SessionScoped
public class CreatePost implements Serializable
{

	private static final long serialVersionUID = 782115604435416963L;

	/* TODO: Replace string with user object */

	private int author;
	private String title;
	private String content;
	private long time;

	private String msg;

	public String getMsg()
	{
		return msg;
	}

	public void setMsg(String msg)
	{
		this.msg = msg;
	}

	public int getAuthor() {
		return author;
	}

	public void setAuthor(int author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	// Validate login
	public String validateCreationRequest()
	{
		if(this.title.isEmpty()) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Incorrect Username and Password", "Please enter a correct Username and Password"));
			return "createPost";
		}
		else if(this.content.isEmpty()) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Incorrect Username and Password", "Please enter a correct Username and Password"));
			return "createPost";
		}
		else
		{
			HttpSession session = SessionUtils.getSession();
			User user;
			//if((user = SessionUtils.getUser())!=null) {
			//int author = user.getId();
			int author = 0;

			Post post = new Post(author, title, content, System.currentTimeMillis());

			PostDAO.insert(post);
			return "createPost";
			//			}			
			//			else {
			//				return "login";
			//			}
		}
	}

}
